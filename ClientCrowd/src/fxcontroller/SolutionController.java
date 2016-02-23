package fxcontroller;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.naming.NamingException;

import crowdclient.CrowdClient;
import tn.esprit.pidev.massconnection.entities.Challenge;
import tn.esprit.pidev.massconnection.entities.Solution;
import delegates.ChallengeDelegate;
import delegates.SolutionDelegate;

/**
 * FXML Controller class
 * 
 * @author ASUSTEK
 */
public class SolutionController implements Initializable, ControlledScreen {

	ScreensController screensController;
	@FXML
	private TableView<Solution> table;
	@FXML
	private Button AnalayseSolution;
	@FXML
	private Button deleteSolution;
	@FXML
	private Button backSolution;
	@FXML
	private TextArea descriptionValue;
	@FXML
	private TextField text;

	private ObservableList<Solution> data;
	private List<Solution> listt;
	public static int idsolution;

	private static TableColumn colid;
	private static TableColumn colpath;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		System.out.println("dfghjkl"+ChallengeController.idChallenge);
		listt = SolutionDelegate
				.retrieveSolutionByChallenge(ChallengeController.idChallenge);
		// System.out.println(SolutionDelegate.retrieveSolutionByChallenge(2));
		data = FXCollections.observableArrayList(listt);
		// list1=ChallengeDelegate.retrieveOneChallenge();

		colid = new TableColumn("Id");

		colid.setMinWidth(100);
		colid.setCellValueFactory(new PropertyValueFactory<Solution, String>(
				"id"));
		// final TableColumn colprojet = new TableColumn("Idprojet");
		// colid.setMinWidth(100);
		// colid.setCellValueFactory(
		// new PropertyValueFactory<ParticipationPK,String>("idChallenge"));
		//
		colpath = new TableColumn("Path");
		colpath.setMinWidth(100);
		colpath.setCellValueFactory(new PropertyValueFactory<Solution, String>(
				"path"));

		//
		table.setItems(data);
		table.getColumns().addAll(colid, colpath);

		table.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {
				System.out.println();
				idsolution = Integer.parseInt(String.valueOf(colid
						.getCellData(table.getSelectionModel()
								.getSelectedIndex())));
				text.setText(String.valueOf(idsolution));
				// int selectedIndex =
				// (int)colprojet.getCellData(table.getSelectionModel().getSelectedIndex());
				// Challenge ch
				// =ChallengeDelegate.findAllChallenge((selectedIndex));
				//
				// descriptionValue.setText(String.valueOf(ch.getDetails().getDescription()));
				// descriptionValue.setText(String.valueOf(ch.getDetails().getTitle()));

				// System.out.println(ManageChallengeController.idChallenge);
				Challenge ch = ChallengeDelegate
						.retrieveOneChallenge(ChallengeController.idChallenge);
				descriptionValue.setText(((ch.getDetails().getDescription())));

			}

		});
	}

	@FXML
	private void analayse(ActionEvent event) {
		CrowdClient.screen.loadScreen("AnalyseSolution",
				"/view/AnalyseSolution.fxml");

		screensController.setScreen("AnalyseSolution");

	}

	@FXML
	private void deletedSolution(ActionEvent event) throws NamingException {

		int selectedIndex = (int) colid.getCellData(table.getSelectionModel()
				.getSelectedIndex());
		System.out.println(selectedIndex);
		if (selectedIndex > 0) {

			Solution ch1 = SolutionDelegate.retrieveOneSolution(selectedIndex);
			SolutionDelegate.deleteSolution(ch1);
		} else {

			System.out.println("failed");
		}

		System.out.println(selectedIndex);

	}

	@FXML
	private void back(ActionEvent event) {
		screensController.setScreen("ManageChallenge");
	}

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
	private void moveToAccount(ActionEvent event) {
		CrowdClient.screen.loadScreen("Auccount", "/view/Auccount.fxml");
		screensController.setScreen("Auccount");
		
	}

}
