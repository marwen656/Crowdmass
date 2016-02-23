/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fxcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import controller.CryptController;
import crowdclient.CrowdClient;
import delegates.AdminitratorDelegate;
import tn.esprit.pidev.massconnection.entities.Administrator;
import tn.esprit.pidev.massconnection.servicesinterface.AdministratorCrudRemote;
import util.ServiceLocator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class 
 * 
 * @author Amine
 */
public class AuthenticateController implements Initializable, ControlledScreen {
	ScreensController screensController;
	@FXML
	private TextField userNameField;
	@FXML
	private TextField passwordField;
	@FXML
	private Label errorLabel;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		errorLabel.setText("");
	}

	@Override
	public void setScreenParent(ScreensController scrennController) {
		screensController = scrennController;
	}

	@FXML
	private void processLogin(ActionEvent event) {
		
		Administrator administrator=new Administrator();
		
		 administrator.setUserName(userNameField.getText());
		 administrator.setPassword(CryptController.MD5Crypt(passwordField.getText().toCharArray()));
		 boolean b= AdminitratorDelegate.authenticate(administrator);
			if (b) {
				CrowdClient.screen.loadScreen("Home", "/view/Home.fxml");
				screensController.setScreen("Home");
				
			} else {
				errorLabel.setText("Username or password incorrect");

			}
	}
}
