package com.UrbanLadder.TestCases;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.UrbanLadder.BaseClass.Useable_Classes;
import com.aventstack.extentreports.ExtentReports;

public class Negative_Testing {
	
	public static WebDriver driver;
	static String s[]=new String[10];
	static ExtentReports extents = Useable_Classes.extentpass();
	static Logger log = LogManager.getLogger(Negative_Testing.class.getName());
	
	/***************************Test Scenario 4***************************/
	/*************************Negative Testing #1************************/
	
	@BeforeMethod //smoke
	public void openbrowser() throws Exception {
		// Invoking driver
		Useable_Classes.getDriver();

		// Hitting URL
		driver = Useable_Classes.homepage();
	}
	
	@Test(priority=1) //smoke,regression
	public void Negative_Testing_SignUpPopup() throws Exception
	{
		//Negative Testing For SignUp Popup
		log.info("*****Sign In PopUp*****");
		
		//Waiting for Sign In PopUp to appear
		Useable_Classes obj=new Useable_Classes();
		obj.webDriverWaitForPopup(driver);
			
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Useable_Classes.negative_testing_SignupPopup();
		
	}
	
	
	/***************************Test Scenario 5***************************/
	/*************************Negative Testing #2************************/
	@Test(priority=2)
	public void Negative_Testing__TrackOrders() throws Exception 
	{
		//Negative Testing For Track Orders
		log.info("*****Track Orders*****");
		
		//Waiting for Sign In PopUp to appear
		Useable_Classes obj=new Useable_Classes();
		obj.webDriverWaitForPopup(driver);
		
		//Close Sign In Pop Up 
		Useable_Classes.popUp();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Useable_Classes.negative_testing_TrackOrders();
		
	}
	
	@AfterMethod
	public void closebrowser() {
		Useable_Classes.closeBrowser();
	}
	
}

