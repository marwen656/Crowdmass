/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fxcontroller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.pidev.massconnection.entities.Crowd;
import tn.esprit.pidev.massconnection.entities.Details;
import tn.esprit.pidev.massconnection.entities.Project;
import tn.esprit.pidev.massconnection.servicesinterface.ProjectCrudRemote;

import com.thoughtworks.xstream.XStream;

import delegates.CrowdDelegate;
import delegates.ProjectDelegate;


public class ProjectToValidateController implements Initializable, ControlledScreen {
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

	private static final Charset CHARSET = Charset.forName("UTF-8");
	@FXML
	private TableView<Project> table;
	@FXML
	private Label Label0;
	@FXML
	private Label Label1;
	@FXML
	private Label Label2;
	@FXML
	private Label Label3;
	@FXML
	private Label Label4;
	@FXML
	private Label labelStatus;
	private static TableColumn idcol;

	private ArrayList<Project> list;
	private static ObservableList<Project> data;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		ArrayList<Project> list = (ArrayList<Project>) ProjectDelegate
				.retrieveProjectToValidate();
		data = FXCollections.observableArrayList(list);
		table.setEditable(true);

		idcol = new TableColumn("Id");
		idcol.setMinWidth(200);
		idcol.setCellValueFactory(new PropertyValueFactory<Project, String>(
				"id"));
	
		final TableColumn investmentAmountcol = new TableColumn("Amount");
		investmentAmountcol.setMinWidth(200);
		investmentAmountcol
				.setCellValueFactory(new PropertyValueFactory<Project, String>(
						"investmentAmount"));
		
		table.setItems(data);
		table.getColumns().addAll(idcol,investmentAmountcol);

		table.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {
				int id = (int) idcol.getCellData(table.getSelectionModel()
						.getSelectedIndex());
		
				Project cp = ProjectDelegate.retrieveOneProject(id);
				ProjectDelegate.seeProject(id);
				Label1.setText(String.valueOf(cp.getDetails().getDeadline()));
				Label2.setText(cp.getDetails().getDescription());
				Label3.setText(cp.getDetails().getTitle());
				Label4.setText(String.valueOf(cp.getCreationDate()));
				 if(cp.isValidationStatus()==true){
					labelStatus.setText("Validated");
				}else if(cp.isValidationStatus()==false){
					labelStatus.setText("Not Validated");
				}

			}

		});

	}

	@Override
	public void setScreenParent(ScreensController scrennController) {
		screensController = scrennController;
	}

	@FXML
	private void moveToAccount(ActionEvent event) {
		screensController.setScreen("Auccount");
	}

	@FXML
	private void moveToHome(ActionEvent event) {
		screensController.setScreen("Home");
	}

	@FXML
	private void moveToChallenge(ActionEvent event) {
		screensController.setScreen("Challenge");
	}

	@FXML
	private void moveToCategory(ActionEvent event) {
		screensController.setScreen("Category");
	}

	@FXML
	private void moveToStatistic(ActionEvent event) {
		screensController.setScreen("Statistic");
	}

	@FXML
	private void handleDelete() throws NamingException {
		int selectedIndex;
		if (Label1.getText() == "") {
			selectedIndex = 0;
		} else {
			selectedIndex = (int) idcol.getCellData(table.getSelectionModel()
					.getSelectedIndex());
		}
		if (selectedIndex > 0) {

			ProjectDelegate.deleteProjectById(selectedIndex);

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
					.children(new Text("No Project Selected"), OK)
					.alignment(Pos.CENTER).padding(new Insets(5)).build()));
			dialogStage.show();
		}
	}


	@FXML
	private void handleRefrech() throws NamingException {

		ArrayList<Project> list = (ArrayList<Project>) ProjectDelegate.retrieveProjectToValidate();
		data = FXCollections.observableArrayList(list);
		/*table.setItems(data);
		Label0.setText("");
		Label1.setText("");
		Label2.setText("");
		Label3.setText("");
		Label4.setText("");*/
	}
	@FXML
	private void blockProject() throws NamingException {

		int id = (int) idcol.getCellData(table.getSelectionModel()
				.getSelectedIndex());
		Project project=ProjectDelegate.retrieveOneProject(id);
		if (project.isValidationStatus()==true){
			ProjectDelegate.unValidateProject(id);
			labelStatus.setText("Not Validated");
		}
		if (project.isValidationStatus()==false){
			ProjectDelegate.validateProject(id);
			labelStatus.setText("Validated");
		}
	}


	


	@FXML
	private void handleExit() {
		System.exit(0);
	}

	@FXML
	private void handleAbout() {
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
				.children(new Text("SMART CROWD 4ERP-BI4"), OK)
				.alignment(Pos.CENTER).padding(new Insets(5)).build()));

		dialogStage.show();

	}

	public static void saveFile(String content, File file) throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(file.toPath(), CHARSET);
		writer.write(content, 0, content.length());
		writer.close();
	}

	public void saveProjectDataToFile(File file) throws NamingException {

		ArrayList<Project> list = (ArrayList<Project>) ProjectDelegate.retrieveProjectToValidate();
		data = FXCollections.observableArrayList(list);
		table.setItems(data);
		XStream xstream = new XStream();
		xstream.alias("Project", Project.class);
		String xml = xstream.toXML(list);
		try {
			saveFile(xml, file);
		} catch (Exception e) { // catches ANY exception
			System.out.println("dfghj");
		}
	}

	@FXML
	private void handleSave() throws Exception {
		File file = new File("D:\\project.xml");
		saveProjectDataToFile(file);
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
				.children(new Text("XML File generate successfully"), OK)
				.alignment(Pos.CENTER).padding(new Insets(5)).build()));
		dialogStage.show();
	}
}
