/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fxcontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.paint.Color;
import crowdclient.CrowdClient;
import delegates.CrowdDelegate;
import delegates.ParticipationDelegate;
import delegates.ProjectDelegate;

/**
 * FXML Controller class
 * 
 * @author Amine
 */
public class StatisticController implements Initializable, ControlledScreen {
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
	Map<String, Long> test;
	@FXML
	private PieChart piechart;
	@FXML
	private PieChart piechart2;
	@FXML
	private Tab onglet1;
	@FXML
    private BarChart<String, Integer> barchart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> challengeName = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		test=CrowdDelegate.GetStaticsTypeOfCrowd();
		List l = new ArrayList<PieChart>();
		 for (Map.Entry<String, Long> entry : test.entrySet()) {
		    String string = entry.getKey();
		    Long long1 = entry.getValue();
		    l.add(new PieChart.Data(string, long1));
		    System.out.println(string+"   "+long1);}
		 final Label caption = new Label("% Type of Crowd");
	        caption.setTextFill(Color.DARKORANGE);
	        caption.setStyle("-fx-font: 24 arial;");
	        ObservableList<PieChart.Data> pieChartData
	                = FXCollections.observableArrayList(l);
	        piechart.setData(pieChartData);
	        piechart.setTitle("% Type of Crowd");
	        piechart.setLabelsVisible(true);
	        //chart 2
	        Map<String, Long> test1 = ProjectDelegate.statisticsTypesOfFundingModel();
			List l1 = new ArrayList<PieChart>();
			 for (Map.Entry<String, Long> entry : test1.entrySet()) {
			    String string = entry.getKey();
			    Long long1 = entry.getValue();
			    l1.add(new PieChart.Data(string, long1));
			    System.out.println(string+"   "+long1);}
			 final Label caption2 = new Label("% Type of Crowd");
		        caption2.setTextFill(Color.DARKORANGE);
		        caption2.setStyle("-fx-font: 24 arial;");
		        ObservableList<PieChart.Data> pieChartData2
		                = FXCollections.observableArrayList(l1);
		        piechart2.setData(pieChartData2);
		        piechart2.setTitle("Type of project");
		        piechart2.setLabelsVisible(true);
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
}
