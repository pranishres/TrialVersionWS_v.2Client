package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.swing.SwingUtilities;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import com.misc.FXOptionPane;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class ApplicationController implements Initializable {
	BackgroundTrialVersionChecker trialVersionCheckThread = new BackgroundTrialVersionChecker();
	PaymentFormController paymentFormController;
	
	private Stage stage;
	private Loader loader;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("From app controller");
		/*
		 * trialVersionCheckThread.setDaemon(true);
		 * trialVersionCheckThread.start();
		 */
		// checkInternetTime();
		// checkInternetTime2();
		// checkForTrialVerison();

	}

	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void checkInternetTime() {
		Calendar calendar = Calendar.getInstance();
		TimeZone fromTimeZone = calendar.getTimeZone();
		TimeZone toTimeZone = TimeZone.getTimeZone("GMT");

		calendar.setTimeZone(fromTimeZone);
		calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
		if (fromTimeZone.inDaylightTime(calendar.getTime())) {
			calendar.add(Calendar.MILLISECOND, calendar.getTimeZone()
					.getDSTSavings() * -1);
		}

		calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
		if (toTimeZone.inDaylightTime(calendar.getTime())) {
			calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
		}
		System.out.print("From checkInternetTime : ");
		System.out.println(calendar.getTime());
	}

	/**
	 * Check whether there is internet connection or not
	 * 
	 * @return
	 */
	public Boolean checkInternetConnection() {
		Boolean returnType = false;
		try {
			/*
			 * check if there is internet connection. To get the date and time
			 * from the internet
			 */

			// InetAddress as = InetAddress.getByName("time-a.nist.gov");
			// as.isReachable(10000)
			/* 1000 milliseconds = 1 second */
			/*
			 * Getting the date and time from the internet using apache library
			 * "commons-net-3.3.jar"
			 */

			URL url = new URL("http://www.google.com");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			if (con.getResponseCode() == 200) { /*
												 * whether the httpconnection
												 * gives any response
												 */
				System.out.println("Connection established");
				returnType = true;
			}
			/*
			 * URLConnection connection = url.openConnection();
			 * 
			 * if (connection.getContentLength() == -1) {
			 * System.out.println("Failed to verify connection"); }
			 */

			else {
				System.out.println("Error establishing connection");
				returnType = false;
			}
		} catch (Exception e) {
			System.out.println("Error while checking inernet connection : "
					+ e.getMessage());
		}
		return returnType;
	}

	/**
	 * Gets the current time from the internet. Converts that time to a calendar
	 * format
	 * 
	 * Note :- To get the time from your time zone, you have to disconnect form the vpn (if connected) 
	 * 
	 * @return Calendar object of the date
	 * @throws IOException
	 */
	public Calendar getInternetTime() {
		Calendar cal = null;
		try {
			System.out.println("getInternetTime()");
			/* server from where we are trying to get the internet time */
			String TIME_SERVER = "time-a.nist.gov";
			NTPUDPClient timeClient = new NTPUDPClient();
			InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
			TimeInfo timeInfo = timeClient.getTime(inetAddress);
			long returnTime = timeInfo.getMessage().getTransmitTimeStamp()
					.getTime();
			Date time = new Date(returnTime);
			System.out
					.println("From checkInternetTime2. Using apache libraries "
							+ time);
			cal = dateToCalendar(time);
		} catch (Exception e) {
			System.out.println("Error while getting internet time : "
					+ e.getMessage());
		}
		return cal;
	}

	/**
	 * Convert date object to calendar object
	 * @param date
	 * @return calendar object of date
	 */
	public Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * check for the trial version and the expiry of the software. If the
	 * software is a trial version software then expiry date is added to it else
	 * it is checked whether the software trial peroid has expired or not
	 * 
	 * @return
	 */
	public String checkForTrialVerison() {
		Calendar calendar;
		try {
			System.out.println("From checkforTrialVersion");
			/*
			 * Code which takes text file as stream. It will help to add the
			 * text file in jar InputStream is File file
			 * =getClass().getResourceAsStream("test.txt"); InputStreamReaderisr
			 * = new InputStreamReader(is); BufferedReader br = new
			 * BufferedReader(isr);
			 */

			/*
			 * check whether there is internet connection or not. If there is
			 * internet connection then that value is taken and stored in
			 * calendar. Else we take the local time of the device
			 */
			if (checkInternetConnection()) {   
				calendar = getInternetTime();      
			} else {
				calendar = Calendar.getInstance();
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			File file = new File("src/com/main/test.txt");
			FileReader isr = new FileReader(file);
			BufferedReader br = new BufferedReader(isr);

			String read = br.readLine();
			System.out.println("Read file is :" + read);

			if (read.equals("trial")) {
				br.close();
				System.out.println("A trial version application");

				/*
				 * Date when the trial version software was first run
				 */
				String firstRunDate = dateFormat.format(calendar.getTime());
				calendar.add(calendar.SECOND, 30);
				/*
				 * Adding the trial period days
				 */
				String trialPeriodExpiry = dateFormat
						.format(calendar.getTime());
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print(""); /* to empty the text file */
				printWriter.print(trialPeriodExpiry);
				printWriter.close();
			} else {
				/*
				 * Comparing the date that is recorded in the text file with the
				 * current date
				 */
				if (read.compareTo(dateFormat.format(calendar.getTime())) < 0) {
					System.out.println("Your trial period is expired");
					
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {
									trialVersionCheckThread.stop();
									FXOptionPane.showMessage(stage, "Your trial period has expired. Please purchase", "Information", "");
									stage.setScene(loader.loadScreen("../fxml/PaymentForm.fxml"));   
									
									/*getting the fxml loader and then running the necessary methods from the controller
									 * */
									paymentFormController=loader.getFXMLLoader().getController();
									paymentFormController.setStage(stage);
									paymentFormController.setLoader(loader);
									
									stage.centerOnScreen();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									System.out.println("Cannot load fxml :"
											+ e.getMessage());
									e.printStackTrace();
								}
							}
						});					
				}
			}

		} catch (Exception e) {
			System.out.println("Error while reading file : " + e.getMessage());
		}

		return null;
	}

	public void runOnBackground() {
		// trialVersionCheckThread.setDaemon(true);
		trialVersionCheckThread.start();
	}

	private class BackgroundTrialVersionChecker extends Thread {
		@Override
		public void run() {
			while (true) {
				System.out.println("Checking trial version");

				/*
				 * SwingUtilities.invokeLater(new Runnable() {
				 * 
				 * @Override public void run() {
				 */
				// TODO Auto-generated method stub
				try {
					Thread.sleep(5000);
					checkForTrialVerison();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out
							.println("Error running the background process : "
									+ e);
					e.printStackTrace();
				}
				/*
				 * Platform.runLater(new Runnable() {
				 * 
				 * @Override public void run() {
				 */

				/*
				 * } });
				 */
				/*
				 * } });
				 */
			}
		}

	}
}
