/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fxcontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.naming.NamingException;

import tn.esprit.pidev.massconnection.entities.Challenge;
import tn.esprit.pidev.massconnection.entities.Project;
import crowdclient.CrowdClient;
import delegates.ChallengeDelegate;
import delegates.ProjectDelegate;

/**
 * FXML Controller class
 * 
 * @author Amine
 */
public class ChallengeController implements Initializable, ControlledScreen {
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
	Button btnCategory;
	@FXML
	private TableView<Challenge> table;

	@FXML
	private Label title;
	@FXML
	private TextField title1;
	@FXML
	private Label description;
	@FXML
	private Label datecreation;
	@FXML
	private Label deadline;
	@FXML
	private Label Status;
	@FXML
	private Label text;
	@FXML
	private Label l1;
	@FXML
	private Label l2;
	@FXML
	private Label l3;
	@FXML
	private Label l4;
	@FXML
	private Label l5;
	// @FXML
	// private Label lid;

	@FXML
	private Button updateChallenge;
	@FXML
	private Button moreChallenge;
	@FXML
	private Button lockChallenge;
	@FXML
	private Button refresh;

	private ObservableList<Challenge> data;

	private List<Challenge> listt;
	private static TableColumn colid;
	public static int idChallenge;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		listt = ChallengeDelegate.retrieveAllChallenge();
		data = FXCollections.observableArrayList(listt);

		colid = new TableColumn("Id");
		colid.setMinWidth(100);
		colid.setCellValueFactory(new PropertyValueFactory<Challenge, String>(
				"id"));

		final TableColumn colstatus = new TableColumn("Status");
		colstatus.setMinWidth(100);
		colstatus
				.setCellValueFactory(new PropertyValueFactory<Challenge, String>(
						"validationStatus"));

		// //
		table.setItems(data);

		table.getColumns().addAll(colid, colstatus);
		table.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {
				

				idChallenge = Integer.parseInt(colid.getCellData(
						table.getSelectionModel().getSelectedIndex())
						.toString());
				System.out.println(idChallenge);
				text.setText(String.valueOf(idChallenge));
				Status.setText(String.valueOf(colstatus.getCellData(table
						.getSelectionModel().getSelectedIndex())));
				int selectedIndex = (int) colid.getCellData(table
						.getSelectionModel().getSelectedIndex());
				Challenge ch = ChallengeDelegate
						.retrieveOneChallenge(selectedIndex);

				description.setText(String.valueOf(ch.getDetails()
						.getDescription()));
				
				title.setText(String.valueOf(ch.getDetails().getTitle()));

				datecreation.setText(String.valueOf(ch.getCreationDate()));
				deadline.setText(String.valueOf(ch.getDetails().getDeadline()));

			}

		});

	}

	@Override
	public void setScreenParent(ScreensController scrennController) {
		screensController = scrennController;
	}
	@FXML
    private void updateChallenge(){
		int selectedIndex = (table.getSelectionModel().getSelectedIndex()+1);
		boolean status=true;
	Challenge ch=ChallengeDelegate.retrieveOneChallenge(selectedIndex);
	ch.setValidationStatus(status);
	ChallengeDelegate.updateChallenge(ch);
		
		
	}
	  @FXML
	    private void lockChallenge() throws NamingException {
		  int selectedIndex = (table.getSelectionModel().getSelectedIndex()+1);
			boolean status=false;
		Challenge ch=ChallengeDelegate.retrieveOneChallenge(selectedIndex);
		ch.setValidationStatus(status);
		ChallengeDelegate.updateChallenge(ch);
			  
	  }

	  
	    @FXML
	    private void moreChallenge() throws NamingException {
	    	CrowdClient.screen.loadScreen("Solution", "/view/Solution.fxml");
	        
					screensController.setScreen("Solution");
					
				}
	    @FXML
	    private void refresh() throws NamingException {
	    	title1.setText("");
			listt=ChallengeDelegate.retrieveAllChallenge();
			data=  FXCollections.observableArrayList(listt);
			  table.setItems(data);
					screensController.setScreen("Challenge");
					
				}

	@FXML
	private void moveToAccount(ActionEvent event) {
		CrowdClient.screen.loadScreen("Auccount", "/view/Auccount.fxml");
		screensController.setScreen("Auccount");

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
				
				ArrayList<Challenge> list =ChallengeDelegate.searchChallenge(title1.getText());
				 data=  FXCollections.observableArrayList(list);
			 	 table.setItems(data);

		    }

}
