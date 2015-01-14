package com.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class SerialKeyController implements Initializable {
	Stage stage;
	Loader loader;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("From serial key controller ");
	}
	
	public void setStage(Stage stage){
		this.stage=stage;
	}
	
	public void setLoader(Loader loader){
		this.loader=loader;
	}
}
