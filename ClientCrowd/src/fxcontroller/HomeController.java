/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fxcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import crowdclient.CrowdClient;
import delegates.ChallengeDelegate;
import delegates.ProjectDelegate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 * 
 * @author Amine
 */
public class HomeController implements Initializable, ControlledScreen {
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
	Hyperlink nbrChallenge;
	@FXML
	Hyperlink nbrProject;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		nbrProject.setText(ProjectDelegate.countProjectToValidate().toString());
		nbrChallenge.setText(ChallengeDelegate.countChallengeToValidate()+"");
		
	}

	@Override
	public void setScreenParent(ScreensController scrennController) {
		screensController = scrennController;
	}
	@FXML
	private void moveToAccount(ActionEvent event) {
		CrowdClient.screen.loadScreen("Auccount", "/view/Auccount.fxml");
		screensController.setScreen("Auccount");
		
	}
	@FXML
	private void moveToProjectToValidate(ActionEvent event) {
		CrowdClient.screen.loadScreen("ProjectToValidate", "/view/ProjectToValidate.fxml");
		screensController.setScreen("ProjectToValidate");
	}
	@FXML
	private void moveToChallengeToValidate(ActionEvent event) {
		CrowdClient.screen.loadScreen("ChallengeToValidate", "/view/ChallengeToValidate.fxml");
		screensController.setScreen("ChallengeToValidate");
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
}
