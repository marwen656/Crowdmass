/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crowdclient;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import fxcontroller.ChallengeController;
import fxcontroller.ScreensController;

/**
 * 
 * @author Amine
 */
public class CrowdClient extends Application {
	public static ScreensController screen =new ScreensController();
	@Override
	public void start(Stage stage) throws Exception {
		
		screen.loadScreen("Authenticate", "/view/Authenticate.fxml");
	
	
		screen.setScreen("Authenticate");
		
		
		Group root = new Group();
		root.getChildren().addAll(screen);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setWidth(1010);
		stage.setHeight(670);
		stage.setScene(scene);
		// stage.centerOnScreen();
		stage.setResizable(false);
		// stage.fullScreenProperty();
		stage.show();
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
