package com.misc;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class FXOptionPane 
{
	/* Global variables for two types of message boxes which share the common properties*/
	private static String messageStyle = "-fx-font:14px 'System'";  

	public enum Response { NO, YES};
	
	private static Response buttonSelected = Response.NO;
	
	private static ImageView icon = new ImageView();
	
	/***
	 * This function creates a dialog box
	 */
	static class Dialog extends Stage 
	{
	    public Dialog( String title, Stage owner, Scene scene, String iconFile ) 
	    {
	       /* modalitiy is kept so the title wont be shown */
	    	setTitle( title );
	    	initStyle(StageStyle.TRANSPARENT);
	        initModality( Modality.APPLICATION_MODAL );
	        
	        initOwner( owner );
	        setResizable( false );
	        setScene( scene );
	        icon.setImage( new Image( getClass().getResourceAsStream( iconFile ) ) );
	        icon.setFitWidth(35);
	        icon.setFitHeight(35);
	        icon.getStyleClass().add("image");
	        icon.setLayoutX(15.0);
	        icon.setLayoutY(8.0); 	        		
	    }
	    
	    public void showDialog() 
	    {
	        sizeToScene();
	        centerOnScreen();
	    }
	}
	
	/***
	 * Messages
	 */
	static class Message extends Text 
	{
	    public Message( String msg ) 
	    {
	        super( msg );
	        setWrappingWidth( 250 );
	    }
	}
	
	/*
	 * for confirmation dialog box
	 */
	
	public static Response showConfirmDialog( Stage owner, String message, String title ) 
	{

		String confirmBox = "-fx-border-width:1px; -fx-background-color:#F4F4F4; -fx-border-color:#d28460" ;
		String headerStyle = "-fx-background-color:#363636";
		String headerMsgStyle = "-fx-font-size: 18px"; 
		
	
		VBox root = new VBox();
	    root.setPrefHeight(150.0);
		root.setPrefWidth(300.0);
/*		root.getStylesheets().add("/css/dialogs.css");
		root.getStyleClass().add("confirmBox");*/
		root.setStyle(confirmBox);
		
		
	    Scene scene = new Scene( root );
	    final Dialog stage = new Dialog( title, owner, scene, "../images/alert.png" );
	    stage.initStyle(StageStyle.TRANSPARENT);
	    //vb.setPadding( Layout.PADDING );
	    //vb.setSpacing( Layout.SPACING );
	    
	    /* black header of the message */
	    HBox header = new HBox();
	    header.setPrefHeight(30.0);
	  //  header.getStyleClass().add("header");	
	    header.setStyle(headerStyle);
	    Node titleNode = new Message(title);
	    
	    titleNode.setStyle(headerMsgStyle);
	    ((Shape) titleNode).setFill(Color.WHITE);
	    header.setAlignment( Pos.CENTER );
	   header.setPadding(new Insets(5, 5, 5, 100));
	    header.getChildren().add(titleNode);	 
	    
		Node messageNode = new Message(message);
		messageNode.setLayoutX(10.0);
		messageNode.setLayoutY(15.0);
		//messageNode.getStyleClass().add("message");
		messageNode.setStyle(messageStyle);
	    
	    Button yesButton = new Button( "Yes" );
	    yesButton.setStyle("-fx-cursor:hand;");
	    yesButton.setPrefWidth(50.0);
	    String yesButtonStyle = "-fx-background-color:#A3C063";
		yesButton.setStyle(yesButtonStyle);
	    yesButton.setOnAction( new EventHandler<ActionEvent>() 
	    {
	        @Override 
	        public void handle(ActionEvent e ) 
	        {
	            stage.close();
	            buttonSelected = Response.YES;
	        }
	    } );
	    
	    Button noButton = new Button( "No" );
	   // noButton.getStyleClass().add("noButton");
	    noButton.setPrefWidth(50.0);
	    noButton.setStyle("-fx-background-color:#d28460");
	    noButton.setOnAction( new EventHandler<ActionEvent>() 
	    {
	        @Override 
	        public void handle( ActionEvent e ) 
	        {
	        	stage.close();
	            buttonSelected = Response.NO;
	        }
	    } );
	    BorderPane bp = new BorderPane();
	    HBox buttons = new HBox();
	    buttons.setAlignment( Pos.CENTER );
	    buttons.setSpacing(20.0);
	    buttons.getChildren().addAll( yesButton, noButton );
	    bp.setCenter( buttons );
	    HBox msg = new HBox();
	    msg.setPrefHeight(50.0);
	    msg.setSpacing(30.0);
	    msg.setPadding(new Insets(15, 5, 5, 25));
	    msg.getChildren().addAll(icon , messageNode);
	    
	    root.getChildren().addAll( header, msg, buttons );
	    stage.showDialog();
	    stage.showAndWait();
	   return buttonSelected;
	}
	
	public static void showMessage( Stage owner, String message, String title, String type ) 
	{
		String iconType;
		String borderColor="#2D2D2D";
		String font = null;
		
		if(type.toLowerCase().equals("sucess")){
	    	iconType = "../images/sucess.png" ;
	    	font = "#A3C063";
	    }
	    
	    else if (type.toLowerCase().equals("unsucess")){
	    	iconType = "../images/unsucess.png";
	    	font = "#d28460";
	    }
		else{
			iconType="../images/alert.png";
			font="#000000";
		}
	   
		String confirmBox = "-fx-border-width:1px; -fx-background-color:#F4F4F4;-fx-border-color:"+ borderColor;
		String headerStyle = "-fx-background-color:#363636"; //247800 green
		String headerMsgStyle = "-fx-font-size: 18px"; 
		String messagecss = "-fx-font-size:14px 'System'"; 

	
		VBox root = new VBox();
	    root.setPrefHeight(130.0);
		root.setPrefWidth(300.0);
/*		root.getStylesheets().add("/css/dialogs.css");
		root.getStyleClass().add("confirmBox");*/
		root.setStyle(confirmBox);
		
		
	    Scene scene = new Scene( root );

	    	
	    final Dialog stage = new Dialog( title, owner, scene,iconType );
	    stage.initStyle(StageStyle.TRANSPARENT);
	    //vb.setPadding( Layout.PADDING );
	    //vb.setSpacing( Layout.SPACING );
	    
	    /* black header of the message */
	    HBox header = new HBox();
	    header.setPrefHeight(30.0);
	  //  header.getStyleClass().add("header");	
	    header.setStyle(headerStyle);
	    Node titleNode = new Message(title);
	    
	    titleNode.setStyle(headerMsgStyle);
	    ((Shape) titleNode).setFill(Color.WHITE);
	    header.setAlignment( Pos.CENTER );
	    header.setPadding(new Insets(5, 5, 5, 100));
	    header.getChildren().add(titleNode);	 
	    
		Node messageNode = new Message(message);
		messageNode.setLayoutX(10.0);
		messageNode.setLayoutY(15.0);
		//messageNode.getStyleClass().add("message");
		messageNode.setStyle(messagecss);
//		((Shape) messageNode).setFill(Color.web(font));
		
		
	    
	    Button okButton = new Button( "OK" );
	    okButton.setStyle("-fx-cursor:hand;");
	    okButton.setPrefWidth(50.0);
	    String okButtonStyle = "-fx-background-color:" + font+";" + "-fx-text-fill:#ffffff";
		okButton.setStyle(okButtonStyle);
		okButton.requestFocus();
		okButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				stage.close();
				
			}
		});
	    okButton.setOnAction( new EventHandler<ActionEvent>() 
	    {
	        @Override 
	        public void handle(ActionEvent e ) 
	        {
	            stage.close();
	            
	        }
	    } );
	    

	    BorderPane bp = new BorderPane();
	    HBox buttons = new HBox();
	    buttons.setAlignment( Pos.CENTER );
	    buttons.setSpacing(20.0);
	    buttons.getChildren().addAll( okButton );
	    bp.setCenter( buttons );
	    HBox msg = new HBox();
	    msg.setPrefHeight(50.0);
	    msg.setSpacing(30.0);
	    msg.setPadding(new Insets(15, 5, 5, 25));
	    msg.getChildren().addAll(icon,messageNode);
	    
	    
	    root.getChildren().addAll( header, msg, buttons );
	    stage.showDialog();
	    stage.showAndWait();
	   
	}
	
	
/*	
 * 
 * The following code is for message box which disappers automatically
 * 
 * 
 * 
 * 
	*//***
	 * for information dialog box
	 *//*
	
	public static void showMessageDialog( Stage owner, String message, String msgtype, String title, Integer second  ) 
	{
	    //String msgImage = messageImage(msgtype);
	    String color ="#D03436";
	    String msgImage ="/img/blank.png";
	    
		if (msgtype.toLowerCase().equals("unsucess"))
		{
			msgImage = "../images/unsucess.png";
			color="#D03436";
		}
		
		else if (msgtype.toLowerCase().equals("sucess"))
		{
			msgImage = "../images/sucess.png";
			color="#60CC16";
		} else if (msgtype.toLowerCase().equals("alert")){
			msgImage="../images/alert/png";
		}
		
	    String confirmBox1 = "-fx-border-width:1px; -fx-background-color:#F4F4F4; -fx-border-color:" + color;
	    AnchorPane root = new AnchorPane();
		
		root.setPrefHeight(50.0);
		root.setPrefWidth(300.0);
		root.getStylesheets().add("/css/dialogs.css");
		root.getStyleClass().add("confirmBox");
	//	String box = confirmBox + "-fx-border-color:#D03436"; 
		root.setStyle(confirmBox1);
		
		 converting the string message to a node to add it to the pane 
		Node messageNode = new Message(message);
		messageNode.setLayoutX(50.0);
		messageNode.setLayoutY(25.0);
		messageNode.setStyle(messageStyle);
		//messageNode.getStyleClass().add("message");
		
		Pane pane = new Pane();
		pane.getChildren().addAll(messageNode,icon);
		
		Scene scene = new Scene(root);		
		final Dialog stage = new Dialog( title, owner, scene, msgImage);
		stage.initStyle(StageStyle.TRANSPARENT);
		
		
		root.getChildren().add(pane);
		stage.setScene(scene);
		stage.centerOnScreen();
		
		stage.showDialog(); 
		stage.show();
		
		
		if(second !=null)
		{
			setTime(stage, second);
		}
	}
	
	private static String messageImage(String msgtype) {
		// TODO Auto-generated method stub
		
		String msgImage = null;
		if (msgtype.equals("ERROR"))
		{
			msgImage = "/img/error.png";
		}
		
		else if (msgtype.equals("SUCESS"))
		{
			msgImage = "/img/sucess.png";
		}
		 return msgImage;
	}

	*//**
	 * time implement for the dialog box 
	 *//*
	public static void setTime( final Stage stage,Integer second ) 
	{
		Timeline timeline = new Timeline();
 		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(second),
 		    new EventHandler<ActionEvent>() 
 		    {
 		        @Override
 		        public void handle(ActionEvent event) 
 		        {
 		        	stage.close();
 		        }
 		    }));
 		timeline.play();
 		
	}
	*/
	
}