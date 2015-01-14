package com.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.trialversionv1.main.GenerateSerialKey;
import org.trialversionv1.main.GenerateSerialKeyService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentFormController implements Initializable {
	@FXML
	TextField txtFirstName;
	@FXML
	TextField txtMiddleName;
	@FXML
	TextField txtLastName;
	@FXML
	TextField txtEmail;
	@FXML
	TextField txtAddress;
	@FXML
	TextField txtCity;
	@FXML
	ComboBox<String> comboCountry;
	@FXML
	ComboBox<String> comboDuration;
	@FXML
	TextField txtPaymentMethod;
	@FXML
	TextField txtCardNumber;
	@FXML
	TextField txtexpiryDate;
	@FXML
	Text comboDurationErr;
	@FXML
	TextField txtAmount;

	SerialKeyController serialKeyController;
	
	/*
	 * Fied time durations for subscription. saving these valeus in variable is
	 * important for later calculations
	 */
	String oneMonth = "1 month";
	String sixMonth = "6 months";
	String oneYear = "1 year";

	float amount;
	private String expiryDate;
	Date lastLoginDate=null;
	
	private Loader loader;
	private Stage stage; 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("From client controller");
//		checkForTrialVerison();
		addValuesInCombo();
//		checkLastLogin();
	}
	
	public void setLoader(Loader loader){
		this.loader = loader;
	} 
	
	public void setStage(Stage stage){
		this.stage=stage;
	}
	
/*	private String checkForTrialVerison(){
		try{		
		
 * Code which takes text file as stream. It will help to add the text file in jar 
 * 		InputStream  is =getClass().getResourceAsStream("test.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
			
			Calendar calendar = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
    		File file = new File("src/com/main/test.txt");
			FileReader isr = new FileReader(file);
			BufferedReader br = new BufferedReader(isr);
		
		String read = br.readLine();
		System.out.println("Read file is :" +read);
		
		if(read.equals("trial")){
			br.close();
			System.out.println("A trial version application");

			String firstRunDate =dateFormat.format(calendar.getTime());
			calendar.add(calendar.SECOND, 8);
			PrintWriter printWriter = new PrintWriter(file);
			printWriter.print("");
			printWriter.print(firstRunDate);
			printWriter.close();			
		}
		else{	
			if(read.compareTo(dateFormat.format(calendar.getTime()))<0){
			System.out.println("Your trial period is expired");
			try{
//			loader = new Loader("B:\\intern\\projects of eclipse make new folder to create project\\TrialVersionClientServerArchitecture_v1\\TrialVersionWS_v.1Client\\src\\com\\main\\client.fxml");
			
//				Loader loader = new Loader("de.fxml");
				FXMLLoader loader = new FXMLLoader(Loader.class.getResource("de.fxml"));
				AnchorPane login = (AnchorPane) loader.load();
				Scene scene = new Scene(login);
				loader.getStage().setScene(scene);
				loader.loadScreen("client.fxml");
			
			}catch(Exception e){
				System.out.println("Cannot load fxml :" + e.getMessage());
			}
			
//			System.exit(0);
			}
		}
		
		}catch(Exception e){
			System.out.println("Error while reading file : " + e.getMessage());
		}
		
		return null;
	}*/
	
	
	private void addValuesInCombo(){
		/**
		 * adding the country to the combobox
		 */
		comboCountry.getItems().add("Nepal");
		comboCountry.getItems().add("China");
		comboCountry.getItems().add("U.S.A");
		comboCountry.getItems().add("U.K");
		comboCountry.getItems().add("Japan");

		/**
		 * Adding the time duration of serial key
		 */

		comboDuration.getItems().add(oneMonth);
		comboDuration.getItems().add(sixMonth);
		comboDuration.getItems().add(oneYear);
		
		
	
		comboDuration.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String oldValue, String newValue) {
				// TODO Auto-generated method stub
				System.out.println("Combo changed : " +oldValue + ":"+newValue);
				if(newValue.equals(oneMonth)){
					txtAmount.setText("$10");
					amount=10;
				}else if(newValue.equals(sixMonth)){
					amount=50;
					txtAddress.setText("$50");
				}else if(newValue.equals(oneYear)){
					amount=100;
					txtAddress.setText("$100");
				}
			}
   
        });
	}
/*
	private void checkLastLogin(){
		Calendar loginDateTime = Calendar.getInstance();
		Date loginDate=loginDateTime.getTime();
		if(loginDate.compareTo(lastLoginDate)>0){
			System.out.println("You changed the system time. Please reset the time to current time");
			System.exit(0);
		}
	}*/
	
	@FXML
	private void submit() {
		if (comboDuration.getValue().equals("")) {
			comboDurationErr.setVisible(true);
		}

		else {
			int duration;
			comboDurationErr.setVisible(false);
			/**
			 * Date and time calculations for expiry date
			 */
			Calendar calender = Calendar.getInstance();
			Date registrationDate = calender.getTime();  
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			if (comboDuration.getValue().equals(oneMonth)) {
				duration=1;
			} else if (comboDuration.getValue().equals(sixMonth)) {
				duration=6;
			} else if (comboDuration.getValue().equals(oneYear)) {
				duration=12;
			}
			
			System.out.println("Older date time : " + dateFormat.format(calender.getTime()));
			/*Adding months to the calender*/
			/*
			calender.add(calender.MONTH, 2);
			System.out.println("Newer date time : "+ dateFormat.format(calender.getTime()));
			*/
			/**
			 * Converting calendar to XMLGregorian Calendar
			 */
/*			GregorianCalendar c = new GregorianCalendar();
			c.setTime(yourDate);
			XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);*/
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(calender.getTime());
			XMLGregorianCalendar xgc=null;
			try {
				xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error converting the gregorian calendar to xmlgregoriancalendar : " +e.getMessage());
			}
			
			String middleName = null;

			String firstName = txtFirstName.getText();
			middleName = txtMiddleName.getText();
			String lastName = txtLastName.getText();
			String email = txtEmail.getText();
			String address = txtAddress.getText();
			String city = txtCity.getText();
			String country = comboCountry.getValue();
			//String duration = comboDuration.getValue();
			String paymentMethod = txtPaymentMethod.getText();
			String cardNumber = txtCardNumber.getText();
			String cardExpiryDate = txtexpiryDate.getText();

			/**
			 * concatinating all the details. This string will be sent to the
			 * server with which the serial key will be generated
			 */
			String customerDetails = firstName + middleName + lastName + email
					+ address + city + country + paymentMethod + cardNumber
					+ cardExpiryDate;

			System.out.println(firstName + " : " + middleName + " : "
					+ lastName + " : " + email + " : " + address + " : " + city
					+ " : " + country + " : " + paymentMethod + " : "
					+ cardNumber + " : " + cardExpiryDate);

			/**
			 * The web service name
			 */
			GenerateSerialKeyService generateSerialKeyService = new GenerateSerialKeyService();

			/**
			 * The web service class. We hold this web service class from the
			 * webservice object
			 */
			GenerateSerialKey generateSerialKey = generateSerialKeyService
					.getGenerateSerialKeyPort();
			/**
			 * Calls the method of webservice which will return a hashed serial
			 * key
			 */
			Boolean serialKeyReturn = generateSerialKey.generateKey(customerDetails);
			if(serialKeyReturn){
				try {
					stage.setScene(loader.loadScreen("../fxml/SerialKey.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/*getting the fxml loader and then running the necessary methods from the controller
				 * */
				serialKeyController=loader.getFXMLLoader().getController();
				serialKeyController.setStage(stage);
				serialKeyController.setLoader(loader);
			}
			else{
				System.out.println("Problem while getting email");
			}
//			txtSerialKey.setText(serialKey);
			
			
			///The expiry date will be set after the client sends the serial key to the server
/*			String expDate = generateSerialKey.encryptExpDate(xgc, 6, 50);
			System.out.println("Expiry date : " + expDate);
			storeInfo(expDate);   */
		}
	}
	
	private void clientDetails(String expDate){
		MiscUtils miscUtils = new MiscUtils();
		String cpuId=miscUtils.getMotherboardSN();
	}

	/**
	 * Saving the serial key in text file
	 */
	private void storeInfo(String clientInfo){
		 try{
		 File file= new File("src/com/main/test.txt");
		 
		 //if file doesn't exists then create it
		 if(!file.exists()){
				file.createNewFile();
		 	}
		 
		 FileWriter fw = new FileWriter(file.getAbsoluteFile());
		 BufferedWriter bw = new BufferedWriter(fw);
		 bw.write(clientInfo);
		 bw.close();		 
		 }catch(Exception e){
			 System.out.println("Error while creating file");
		 }		 
	 }
	
	/**
	 * Check if the application is a trial version application or not.
	 *If the application is opened for the first time then the first login time is saved in the file and also the 
	 *trial expiry date. Then the payment form is shown
	 * @return
	 */

	@FXML
	private void reset() {
		txtFirstName.setText("");
		txtMiddleName.setText("");
		txtLastName.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		txtCity.setText("");
		comboCountry.setValue("");
		txtPaymentMethod.setText("");
		txtCardNumber.setText("");
		txtexpiryDate.setText("");
	}

	@FXML
	private void cancel() {
		System.exit(0);
	}
}
