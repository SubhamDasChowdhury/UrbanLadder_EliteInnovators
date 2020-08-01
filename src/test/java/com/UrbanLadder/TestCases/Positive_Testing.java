package com.UrbanLadder.TestCases;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.UrbanLadder.BaseClass.Useable_Classes;
import com.UrbanLadder.Utilities.PageObjectModel;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.UrbanLadder.BaseClass.ReUseable_Classes;

public class Positive_Testing 
{
	static ReUseable_Classes obj;
	static WebDriver driver = Useable_Classes.drv();
	static ExtentReports extents = Useable_Classes.extentpass();
	static Logger log = LogManager.getLogger(Positive_Testing.class.getName());
	
	/***************************Test Scenario 1***************************/
	/*****************************Bookshelves****************************/
	@Test(priority=1) //smoke ,regression 
	public void Homepage() throws Exception 
	{
		log.info("*****Bookshelves*****");
		//Invoking driver
		Useable_Classes.getDriver();
		
		//Get the URL form Properties File and send it to driver
		driver = Useable_Classes.homepage();
		String expectedTitle = "Furniture Online: Buy Home Wooden Furniture Online In India At Best Price - Urban Ladder - Urban Ladder";
		
		//Verify the Home Page Title
		try {
		Assert.assertEquals(driver.getTitle(), expectedTitle);
		ReUseable_Classes.reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			ReUseable_Classes.reportFail(e.getMessage());
		}
	}
	
	@Test(priority=2) //smoke,regression
	public void ImplicitWait() throws Exception
	{
		//Calling Implicit Wait function required to handle Login PopUp
		Useable_Classes obj=new Useable_Classes();
		obj.webDriverWaitForPopup(driver);
		
		//Verify if SignUp Popup is Displayed
		try {
			Assert.assertTrue(driver.findElement(PageObjectModel.Assrt_PopUpDisplay).isDisplayed());
			ReUseable_Classes.reportPass("Sign Up PopUp Displayed");
		} catch (Exception e) {
			ReUseable_Classes.reportFail("SignUp PopUp Not Displayed");
		}		
	}
	
	@Test(priority=3) //smoke,regression
	public void PopUpClose()
	{
		//Verify if PopUp Close Button is Available
		try {
			Assert.assertTrue(driver.findElement(PageObjectModel.ClosePopup).isDisplayed());
			ReUseable_Classes.reportPass("PopUp Close Button Available");
		} catch (Exception e) {
			ReUseable_Classes.reportFail("PopUp Close button Not Available");
		}
		//Close PopUp
		Useable_Classes.popUp();
	}
	
	@Test(priority=4) //smoke,regression
	public void Search_Bookshelves() throws Exception
	{
		//Search Bookshelves by reading data from Excel file
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Useable_Classes.search_bookshelves();
		
		//Verify if searched data is 'Bookshelves' 
		String expected = "'Bookshelves'";
		try {
			Assert.assertEquals(driver.findElement(PageObjectModel.Assrt_BookshelvesSearch).getText(), expected);
			ReUseable_Classes.reportPass(expected+" Searched Successfully");
		} catch (Exception e) {
			ReUseable_Classes.reportFail(e.getMessage());
		}
		
	}
	
	@Test(priority=5) //smoke,regression
	public void SelectCategoryForBookshelves() throws Exception 
	{
		//Select Bookshelves Category
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Useable_Classes.selectcategory_bookshelves();
		
		Thread.sleep(2000);
		String expected = "Bookshelves";
		
		//Verify if Selected Category is Bookshelves
		try {
			Assert.assertEquals(driver.findElement(PageObjectModel.Assrt_BookshelvesCategory).getText(), expected);
			ReUseable_Classes.reportPass(expected+" Category Clicked Successfully");
		} catch (Exception e) {
			ReUseable_Classes.reportFail(e.getMessage());
		}
	}
	
	@Test(priority=6) //smoke,regression
	public void Select_Bookshelf_Price() throws Exception 
	{
		//Select Bookshelves Price Range
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Useable_Classes.select_price_for_bookshelves();
		Thread.sleep(2000);
		
		//Verify if Price Range Selected
		try {
			Assert.assertTrue(driver.findElement(PageObjectModel.Assrt_BookshelvesPrice).isDisplayed());
			ReUseable_Classes.reportPass("Bookshelves Price Range Selected Successfully");
		} catch (Exception e) {
			ReUseable_Classes.reportFail(e.getMessage());
		}
		Thread.sleep(2000);
	}
	
	@Test(priority=7) //smoke,regression
	public void HoverOverStorageType() throws Exception
	{
		//Mouse Hover to Storage Type Option
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Useable_Classes.actions();
		Thread.sleep(1000);
		
		//Verify if Mouse Hovered to Storage Type
		try {
			Assert.assertTrue(driver.findElement(PageObjectModel.Assrt_BookshelvesHoverStorageType).isDisplayed());
			ReUseable_Classes.reportPass("Hovered Over Storage Type Successfully");
		} catch (Exception e) {
			ReUseable_Classes.reportFail(e.getMessage());
		}
		
	}
	
	@Test(priority=8) //smoke,regression
	public void SelectOpenStorage() throws Exception
	{
		//Select Open Type Storage Checkbox
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Useable_Classes.selectOpenStorage();
		Thread.sleep(1000);
		
		//Verify if Storage Type - 'Open' is Selected
		try {
			Assert.assertTrue(driver.findElement(PageObjectModel.OpenStorage).isSelected());
			ReUseable_Classes.reportPass("OpenStorage Selected Successfully");
		} catch (Exception e) {
			ReUseable_Classes.reportFail(e.getMessage());
		}
		
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
	}
	
	@Test(priority=9) //smoke,regression
	public void AssertionCheckForBookshelves() throws Exception {
		//Handling Assertions
		//Check if Exclude Out of Stock Option is selected or not 
		try {
			Assert.assertFalse(driver.findElement(PageObjectModel.Exclude_OutOfStock).isSelected());
			ReUseable_Classes.reportPass("Exclude Out of Stock Not Selected");
		} catch (Exception e) {
			ReUseable_Classes.reportFail("Exclude Out of Stock Selected");
		}
		
		//Check if Sort By - Recommended is enabled or not
		try {
			Assert.assertTrue(driver.findElement(PageObjectModel.SortBy_Recommended).isEnabled());
			ReUseable_Classes.reportPass("Sort By - Recommended Selected");
		} catch (Exception e) {
			ReUseable_Classes.reportFail("Sort By - Recommended Not Selected");
		}
		
		ExtentTest test = extents.createTest("Assertion Check for Bookshelves #10");
		test.log(Status.INFO, "Assertion check for Bookshelves Passed");
	}
	
	@Test(priority=10) //regression
	public void Bookshelves_ScreenShot() throws Exception
	{
		//Take Screenshot of the Study Chairs displayed on the Web page
		ExtentTest test1 = ReUseable_Classes.screenShot(driver);
		test1.log(Status.INFO, "Bookshelf Details Screen Shot taken Successfully");
	}
	
	@Test(priority=11) //regression
	public void WriteExcelBookshelves() throws Exception
	{
		//Write the Bookshelf details displayed on Web page to Excel File
		Useable_Classes.writeExcel_Bookshelves();
	}
	
	/***************************Test Scenario 2***************************/
	/****************************Study Chairs****************************/
	@Test(priority=12) //smoke,regression
	public void Search_StudyChairs() throws Exception
	{
		log.info("*****Study Chairs*****");
		//Search Study Chairs by reading data from Excel file
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Useable_Classes.search_studychairs();
		Thread.sleep(2000);
		
		//Verify if searched data is 'Study Chairs' 
				String expected = "'Study Chairs'";
				try {
					Assert.assertEquals(driver.findElement(PageObjectModel.Assrt_StudyChairSearch).getText(), expected);
					ReUseable_Classes.reportPass(expected+" Searched Successfully");
				} catch (Exception e) {
					ReUseable_Classes.reportFail(e.getMessage());
				}
	}
	
	@Test(priority=13) //smoke,regression
	public void SelectCategoryForStudyChairs() throws Exception 
	{
		//Selecting Study Chairs option from Category Type
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Useable_Classes.selectcategory_studychairs();
		
		Thread.sleep(3000);
		String expected = "Study Chair";
		
		//Verify if Selected Category is Study Chairs
		try {
			Assert.assertEquals(driver.findElement(PageObjectModel.Assrt_StudyChairCategory).getText(), expected);
			ReUseable_Classes.reportPass(expected + " Category Clicked Successfully");
		} catch (Exception e) {
			ReUseable_Classes.reportFail(e.getMessage());
		}
	}
	
	@Test(priority=14) //smoke,regression
	public void AssertionCheckForStudyChairs() throws Exception {
		//Handling Assertions
		//Check if Exclude Out of Stock Option is selected or not 
		try {
			Assert.assertFalse(driver.findElement(PageObjectModel.Exclude_OutOfStock).isSelected());
			ReUseable_Classes.reportPass("Exclude Out of Stock Not Selected");
		} catch (Exception e) {
			ReUseable_Classes.reportFail("Exclude Out of Stock Selected");
		}
		
		//Check if Sort By - Recommended is enabled or not
		try {
			Assert.assertTrue(driver.findElement(PageObjectModel.SortBy_Recommended).isEnabled());
			ReUseable_Classes.reportPass("Sort By - Recommended Selected");
		} catch (Exception e) {
			ReUseable_Classes.reportFail("Sort By - Recommended Not Selected");
		}	
		
		ExtentTest test = extents.createTest("Assertion Check for Study Chairs #14");
		test.log(Status.INFO, "Assertion check for Study Chairs Passed");
	}
	
	@Test(priority=15) //regression
	public void Study_Chairs_ScreenShot() throws Exception
	{
		//Take Screenshot of the Study Chairs displayed on the Web page
		ExtentTest test1 = ReUseable_Classes.screenShot(driver);
		test1.log(Status.INFO, "Study Chair Details Screen Shot taken Successfully");
	}
	
	@Test(priority=16) //regression
	public void WriteExcelChairs() throws Exception
	{
		//Write the Study Chair details displayed on Web page to Excel File
		Useable_Classes.writeExcel_Chairs();
		Useable_Classes.closeBrowser();
	}
	
	/***************************Test Scenario 3***************************/
	/*******************************Tables*******************************/
	@Test(priority=17) //smoke,regression
	public void Search_Table() throws Exception 
	{
		log.info("*****Tables*****");
		//Invoking driver
		Useable_Classes.getDriver();
		
		//Get the URL form Properties File and send it to driver
		driver = Useable_Classes.homepage();
		String expectedTitle = "Furniture Online: Buy Home Wooden Furniture Online In India At Best Price - Urban Ladder - Urban Ladder";
		
		//Verify the Home Page Title
		try {
		Assert.assertEquals(driver.getTitle(), expectedTitle);
		ReUseable_Classes.reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			ReUseable_Classes.reportFail(e.getMessage());
		}

		//Calling Implicit Wait function required to handle Login PopUp
		Useable_Classes obj=new Useable_Classes();
		obj.webDriverWaitForPopup(driver);
		
		//Close PopUp
		Useable_Classes.popUp();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Search Tables by reading data from Excel file
		Useable_Classes.search_tables();			
	}
	
	@Test(priority=18) //regression
	public void Select_Table() throws Exception 
	{
		//Select the first displayed Table after adding the filters 
		//Perform Window Handle
		//Add Item to Cart
		Useable_Classes.select_table();

	}		
}

