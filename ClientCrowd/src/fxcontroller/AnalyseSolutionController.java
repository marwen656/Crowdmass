package fxcontroller;


	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */



import java.net.URL;
import java.util.ResourceBundle;



import crowdclient.CrowdClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import delegates.SolutionDelegate;
import plagiarismdetect.ConsoleIO;



	
	public class AnalyseSolutionController implements Initializable, ControlledScreen {
		ScreensController screensController;
		@FXML
		private TextArea resultat;
		@FXML
		private Button backsolution;
		
		@FXML
		private Label id;
		
		
		

	    /**
	     * Initializes the controller class.
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	
	   	String [] arg={SolutionDelegate.retrievePath(SolutionController.idsolution)};
			
	
		resultat.setText(String.valueOf(ConsoleIO.run(arg)));
		id.setText((String.valueOf(ChallengeController.idChallenge)));
		 
		
	    	
	        
	    }    

	    @FXML
	    private void backSolution(ActionEvent event) {
		    screensController.setScreen("Challenge");

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
		private void moveToAccount(ActionEvent event) {
			CrowdClient.screen.loadScreen("Auccount", "/view/Auccount.fxml");
			screensController.setScreen("Auccount");
			
		}
	}



