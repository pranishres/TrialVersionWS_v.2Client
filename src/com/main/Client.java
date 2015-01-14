package com.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Client extends Application{

	private ApplicationController appController;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Loader loader = new Loader();
//		stage.setScene(createScene(loader.loadScreen("Application.fxml")));
		stage.setScene(loader.loadScreen("../fxml/Application.fxml"));
		
		/*getting the fxml loader and then running the necessary methods from the controller
		 * */
		appController=loader.getFXMLLoader().getController();
		appController.setStage(stage);
		appController.setLoader(loader);
		
		appController.checkForTrialVerison();
		appController.runOnBackground();
		stage.centerOnScreen();
		stage.show();
		
//		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Application.fxml"));
//		Parent root = fxmlloader.load();
		
//		appController = fxmlloader.getController();
//		Loader loader = new Loader();
//		appController.setLoader(loader);
//		loader.setStage(stage);	
//		appController.checkForTrialVerison();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
/*			try {
				Thread.sleep(10000);
				Loader loader = new Loader("client.fxml");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Cannot start the thread : " + e.getMessage());
			} catch (IOEhttp://marketplace.eclipse.org/marketplace-client-intro?mpc_install=27025xception e) {
				// TODO Auto-generated catch block
				System.out.println("Cannot load the fxml : " + e.getMessage());
				e.printStackTrace();
				
			}*/
	}

/*	private Scene createScene(Pane mainPane){
		Scene scene = new Scene(mainPane);
		return scene;		
	}*/
	
	public static void main(String[] args) {
		launch(args);
	}

/*	FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("root.fxml"));
	AnchorPane login = (AnchorPane) loader.load();
	Scene scene = new Scene(login); */
}
