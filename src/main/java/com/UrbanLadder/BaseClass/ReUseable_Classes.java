package com.UrbanLadder.BaseClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ReUseable_Classes 
{
	static Logger log = LogManager.getLogger(ReUseable_Classes.class.getName());
	static WebDriver driver;
	static ExtentReports extent;

	//Function to implement Extent Reports
	public static ExtentReports Report()
	{
		String path = System.getProperty("user.dir")+"\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Elite Innovators Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","EliteInnovators");
		return extent;
	}
	
	//Function to Pass the Assertion for Test Cases and display related message
	public static void reportPass(String reportString) {
		ExtentTest test= extent.createTest("Assertion Passed #");
		test.log(Status.PASS, reportString);
	}
	
	//Function to Fail the Assertion for Test Cases and display related message
	public static void reportFail(String reportString) {
		ExtentTest test= extent.createTest("Assertion Failed #");
		test.log(Status.FAIL, reportString);
		Assert.fail(reportString);
	}
	
	//Function to implement Screenshot
	public static ExtentTest screenShot(WebDriver driver) throws Exception
	{
		Thread.sleep(1000);
		ExtentTest test= extent.createTest("Take ScreenShot");
		log.debug("Take Screenshot");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ScrObj = (TakesScreenshot) driver;
		File CaptureImg = ScrObj.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(CaptureImg, new File(System.getProperty("user.dir")+"\\Screenshots\\"+timeStamp+"_SS.png"));
		log.info("Screenshot Taken Successfully");
		return test;
	}

	
}