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

import com.thoughtworks.xstream.XStream;

import crowdclient.CrowdClient;
import delegates.CategoryDelegate;
import delegates.ProjectDelegate;
import tn.esprit.pidev.massconnection.entities.Category;
import tn.esprit.pidev.massconnection.servicesinterface.CategoryCrudRemote;


public class CategoryController implements Initializable, ControlledScreen {
	ScreensController screensController;
	public static TableColumn idcol ;
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
	// private CategoryManageController mainApp;
	private static final Charset CHARSET = Charset.forName("UTF-8");
	@FXML
	public static TableView<Category> table;
	@FXML
	private Label firstLabel;
	@FXML
	public static Label laslLabel;
	@FXML
	private Label nbrProject;

	private ArrayList<Category> list;
	private static ObservableList<Category> data;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

					ArrayList<Category> list = (ArrayList<Category>)CategoryDelegate.retrieveAllCategories();
					data = FXCollections.observableArrayList(list);
					table.setEditable(true);
		
					 idcol = new TableColumn("Id");
					idcol.setMinWidth(100);
					idcol.setCellValueFactory(new PropertyValueFactory<Category, String>(
							"id"));
		
					final TableColumn titlecol = new TableColumn("Title");
					titlecol.setMinWidth(100);
					titlecol.setCellValueFactory(new PropertyValueFactory<Category, String>(
							"title"));
					table.setItems(data);
					table.getColumns().addAll(idcol, titlecol);
					table.setOnMouseClicked(new EventHandler<Event>() {
						@Override
						public void handle(Event e) {
		
							firstLabel.setText(String.valueOf(idcol.getCellData(table
									.getSelectionModel().getSelectedIndex())));
							laslLabel.setText(String.valueOf(titlecol.getCellData(table
									.getSelectionModel().getSelectedIndex())));
							int selectedIndex = Integer.parseInt(firstLabel.getText());
							nbrProject.setText(ProjectDelegate.countByCategory(selectedIndex)+"");
						}
		
					});
	}

	@FXML
	private void handleNew() throws NamingException {
		CrowdClient.screen.loadScreen("AddCategory", "/view/AddCategory.fxml");
		screensController.setScreen("AddCategory");
	}
	@FXML
	private void handleEdit() throws NamingException {
		CrowdClient.screen.loadScreen("EditCategory", "/view/EditCategory.fxml");
		screensController.setScreen("EditCategory");
	}

	@FXML
	private void handleRefrech() throws NamingException {

		ArrayList<Category> list = (ArrayList<Category>)CategoryDelegate.retrieveAllCategories();
		data = FXCollections.observableArrayList(list);
		table.setItems(data);

	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}

	
	public static void saveFile(String content, File file) throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(file.toPath(), CHARSET);
		writer.write(content, 0, content.length());
		writer.close();
	}

	public void saveCategoryDataToFile(File file) throws NamingException {

		ArrayList<Category> list = (ArrayList<Category>)CategoryDelegate.retrieveAllCategories();
		data = FXCollections.observableArrayList(list);
		XStream xstream = new XStream();
		xstream.alias("category", Category.class);
		String xml = xstream.toXML(list);
		try {
			saveFile(xml, file);
		} catch (Exception e) { // catches ANY exception
			System.out.println(e.getMessage());
		}
	}

	@FXML
	private void handleSave() throws Exception {
		File file = new File("D:\\category.xml");
		saveCategoryDataToFile(file);
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

	@Override
	public void setScreenParent(ScreensController scrennController) {
		screensController = scrennController;
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
	private void moveToHome(ActionEvent event) {
		CrowdClient.screen.loadScreen("Home", "/view/Home.fxml");
		screensController.setScreen("Home");
	}
	@FXML
	private void moveToAccount(ActionEvent event) {
		CrowdClient.screen.loadScreen("Auccount", "/view/Auccount.fxml");
		screensController.setScreen("Auccount");
		
	}

	@FXML
	private void handleDelete() throws NamingException {
		int selectedIndex;
		if (firstLabel.getText() == "") {
			selectedIndex = 0;
		} else {
			selectedIndex = Integer.parseInt(firstLabel.getText());
		}
		if (selectedIndex > 0) {

			CategoryDelegate.deleteCategoryById(selectedIndex);
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
					.children(new Text("No Category Selected"), OK)
					.alignment(Pos.CENTER).padding(new Insets(5)).build()));
			dialogStage.show();
		}
	}
}
