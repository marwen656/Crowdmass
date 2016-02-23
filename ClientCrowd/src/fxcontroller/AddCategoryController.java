package fxcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import crowdclient.CrowdClient;
import delegates.CategoryDelegate;
import tn.esprit.pidev.massconnection.entities.Category;
import tn.esprit.pidev.massconnection.servicesinterface.CategoryCrudRemote;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddCategoryController implements Initializable, ControlledScreen {
	ScreensController screensController;
	@FXML
	private TextField title;

	public void initialize(URL url, ResourceBundle rb) {

		title.setText("");

	}

	@FXML
	private void handleOk() throws NamingException {

		if (title.getText().equals("")) {
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
					.children(new Text("No Category Title writed...!"), OK)
					.alignment(Pos.CENTER).padding(new Insets(5)).build()));
			dialogStage.show();

		} else {
			System.out.println(title.getText()+"teeeeeeeeest");
			Category categoryy = new Category(title.getText().toString());
			//category.setTitle(title.getText());
			CategoryDelegate.newCat(categoryy);
			screensController.setScreen("Category");
		}

	}

	@FXML
	private void handleCancel() throws NamingException {
		screensController.setScreen("Category");

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


}
