/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fxcontroller;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import crowdclient.CrowdClient;
import tn.esprit.pidev.massconnection.entities.Crowd;
import tn.esprit.pidev.massconnection.servicesinterface.CrowdCrudRemote;
import delegates.CrowdDelegate;

/**
 * FXML Controller class
 * 
 * @author Amine
 */
public class AccountController implements Initializable, ControlledScreen {
	ScreensController screensController;
	@FXML
	Button btnStat;
	@FXML
	Button btnAccount;
	@FXML
	Button btnChallenge;
	@FXML
	Button btnProject;
	@FXML
	Button btnHome;
	@FXML
	Button btnCategory;
	private static final Charset CHARSET = Charset.forName("UTF-8");
	@FXML
	private TableView<Crowd> table;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Label label4;
	@FXML
	private TextField title1;
	@FXML
	private Label label5;
	private static TableColumn idcol;
	private ArrayList<Crowd> list;
	private static ObservableList<Crowd> data;


	@Override
	public void initialize(URL url, ResourceBundle rb) {

		ArrayList<Crowd> list = (ArrayList<Crowd>) CrowdDelegate
				.retrieveAllCrowds();
		data = FXCollections.observableArrayList(list);
		table.setEditable(true);

		idcol = new TableColumn("Id");
		idcol.setMinWidth(40);
		idcol.setCellValueFactory(new PropertyValueFactory<Crowd, String>("id"));

		final TableColumn date_of_birthcol = new TableColumn("date of birth");
		date_of_birthcol.setMinWidth(135);
		date_of_birthcol
				.setCellValueFactory(new PropertyValueFactory<Crowd, String>(
						"date_of_birth"));

		final TableColumn firstNamecol = new TableColumn("firstName");
		firstNamecol.setMinWidth(115);
		firstNamecol
				.setCellValueFactory(new PropertyValueFactory<Crowd, String>(
						"firstName"));

		final TableColumn lastNamecol = new TableColumn("lastName");
		lastNamecol.setMinWidth(110);
		lastNamecol
				.setCellValueFactory(new PropertyValueFactory<Crowd, String>(
						"lastName"));

		table.setItems(data);
		table.getColumns().addAll(idcol, date_of_birthcol, firstNamecol,
				lastNamecol);
		table.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {
				int id = (int) idcol.getCellData(table.getSelectionModel()
						.getSelectedIndex());
				Crowd cr = CrowdDelegate.retrieveOneCrowd(id);
				if (cr.getIsCreator()) {
					label1.setText("YES");
				} else {
					label1.setText("NO");
				}
				if (cr.getIsInvestor()) {
					label2.setText("YES");
				} else {
					label2.setText("NO");
				}
				if (cr.getIsChallengeCreator()) {
					label3.setText("YES");
				}

				else {
					label3.setText("No");
				}
				if (cr.getIsChallengeSolver()) {
					label4.setText("YES");
				}

				else {
					label4.setText("No");
				}
				if (cr.getStatus() == 0) {
					label5.setText("BLOCKED");
				}

				else {
					label5.setText("ACTIF");
				}

			
			}

		});
	}

	@FXML
	private void handledelete() throws NamingException {

		int selectedIndex;
		if (label1.getText() == "") {
			selectedIndex = 0;
		} else {
			selectedIndex = (int) idcol.getCellData(table.getSelectionModel()
					.getSelectedIndex());
		}
		if (selectedIndex > 0) {

			// CrowdDelegate.deleteCrowd(CrowdDelegate.retrieveOneCrowd(selectedIndex));+
			CrowdDelegate.removeCrowd(selectedIndex);

		} else {
			final Stage dialogStage = new Stage();
			dialogStage.setHeight(200);
			dialogStage.setWidth(200);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Button OK = new Button("Ok");

			OK.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					dialogStage.close();
				}
			});
			dialogStage.setScene(new Scene(VBoxBuilder.create()
					.children(new Text("No Crowd Selected"), OK)
					.alignment(Pos.CENTER).padding(new Insets(5)).build()));
			dialogStage.show();

			
			data.remove(selectedIndex);
			
			table.getSelectionModel().clearSelection();
		
		}
	}

	@FXML
	private void handlerefrech() throws NamingException {
		title1.setText("");
		ArrayList<Crowd> list = (ArrayList<Crowd>) CrowdDelegate
				.retrieveAllCrowds();
		data = FXCollections.observableArrayList(list);
		table.setItems(data);

	}
	@FXML
	private void blockUnblockCrowd() throws NamingException {

		int id = (int) idcol.getCellData(table.getSelectionModel()
				.getSelectedIndex());
		Crowd cr = CrowdDelegate.retrieveOneCrowd(id);
		if (cr.getStatus()==0){
			CrowdDelegate.unblockCrowd(id);
			label5.setText("ACTIF");
		}
		if(cr.getStatus()==1){
			CrowdDelegate.blockCrowd(id);
			label5.setText("BLOCKED");
		}

	}

	@Override
	public void setScreenParent(ScreensController scrennController) {
		screensController = scrennController;
	}

	@FXML
	private void moveToHome(ActionEvent event) {
		CrowdClient.screen.loadScreen("Home", "/view/Home.fxml");
		screensController.setScreen("Home");
	}

	@FXML
	private void moveToProject(ActionEvent event) {
		CrowdClient.screen.loadScreen("Project", "/view/Project.fxml");
		screensController.setScreen("Project");
	}

	@FXML
	private void moveToChallenge(ActionEvent event) {
		CrowdClient.screen.loadScreen("Challenge", "/view/Challenge.fxml");
		screensController.setScreen("Challenge");
	}

	@FXML
	private void moveToCategory(ActionEvent event) {
		CrowdClient.screen.loadScreen("Category", "/view/Category.fxml");
		screensController.setScreen("Category");
	}
	@FXML
	private void moveToStatistic(ActionEvent event) {
		CrowdClient.screen.loadScreen("Statistic", "/view/Statistic.fxml");
		screensController.setScreen("Statistic");
	}
	
	   @FXML
		private void handleOk() throws NamingException {
			System.out.println(title1.getText());
			ArrayList<Crowd> list =CrowdDelegate.searchAccount(title1.getText());
			 data=  FXCollections.observableArrayList(list);
		 	 table.setItems(data);

	    }

}
