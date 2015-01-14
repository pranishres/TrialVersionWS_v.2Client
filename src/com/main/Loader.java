package com.main;

import java.io.IOException;

import sun.security.jgss.LoginConfigImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Loader {
	Stage stage;
	FXMLLoader loader;
	public Stage getStage(){
		return stage;
	}
	
	public void setStage(Stage stage2){
		this.stage = stage2;
		
	}
	
/*	
	public Loader() throws IOException{
		
	}*/
	
	public Scene loadScreen(String fxml) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(Loader.class.getResource(fxml));
		this.loader= loader;
		Pane login = (Pane) loader.load();
		Scene scene = new Scene(login);
//		getStage().setScene(scene);
//		getStage().show();

		return scene;	
	}
	
	public FXMLLoader getFXMLLoader(){
		return loader;
	}
	
	public void setFXMLLoader(FXMLLoader loader){
		this.loader= loader;
	}
}
