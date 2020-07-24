package com.UrbanLadder.BaseClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.UrbanLadder.Config.PropertiesFile;
import com.UrbanLadder.Utilities.Excelconfig;
import com.UrbanLadder.Utilities.PageObjectModel;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Useable_Classes extends ReUseable_Classes {
	static Logger log = LogManager.getLogger(Useable_Classes.class.getName());
	static WebDriver driver;
	public static String browser;
	public static String chromeDriverLocation;
	public static String firefoxDriverLocation;
	public static String InternetExplorerDriverLocation;
	public static String EdgeDriverLocation;
	public static String url;
	static ExtentReports extents = ReUseable_Classes.Report();
	public static Excelconfig obj = new Excelconfig();
	static String s[] = new String[10];

	//Browser Selection from Prop.Properties File
	//Invoking the selected browser
	public static void getDriver() throws Exception 
	{
		log.debug("Invoking Browser");
		ExtentTest test = extents.createTest("Driver Invoke #1");
		log.debug("Reading Properties File");
		PropertiesFile.readPropertiesFile();
		log.info("Properties File read Successfully");
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
			driver = new ChromeDriver();
			log.info("Chrome Driver Invoked");
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", firefoxDriverLocation);
			driver = new FirefoxDriver();
			log.info("Firefox Driver Invoked");
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", EdgeDriverLocation);
			driver = new EdgeDriver();
			log.info("Edge Driver Invoked");
		} else {
			System.out.println("!!!!!!!!!!!!!!!!!!Browser Not Supported!!!!!!!!!!!!!!!!!!");
			log.fatal("Incorrect Option selected....None of the Drivers are Invoked");
		}
		log.debug("Maximizing Window");
		driver.manage().window().maximize();
		log.info("Window Maximized");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		test.log(Status.INFO, "Driver Invoked Successfully...");
		log.info("Browser Invoked Successfully");
	}

	//Getting URL info from Prop.Properties File
	//Hitting the URL
	public static WebDriver homepage() 
	{
		ExtentTest test = extents.createTest("Homepage Open #2");
		PropertiesFile.readPropertiesFile();

		log.debug("Hitting URL");
		driver.get(url);
		log.info("URL Opened");
		test.log(Status.INFO, "Homepage Opened Successfully...");
		log.info("Homepage Opened Successfully");
		return driver;
	}

	//Waiting for Sign In PopUp to appear
	public void webDriverWaitForPopup(WebDriver driver) throws Exception {
		ExtentTest test = extents.createTest("Wait Until Popup Appears #3");
		Useable_Classes.driver = driver;
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 20);
		log.debug("Waiting for Popup");
		wait.until(ExpectedConditions.elementToBeClickable(PageObjectModel.ClosePopup));
		log.info("PopUp appeared, WebDriverWait applied successfully");
		test.log(Status.INFO, "WebDriverWait Applied Successfully");
	}

	//Closing the Sign In PopUp
	public static void popUp() {
		ExtentTest test = extents.createTest("Pop Up Close #4");
		log.debug("Clicking Popup");
		driver.findElement(PageObjectModel.ClosePopup).click();
		log.info("PopUp Closed");
		test.log(Status.INFO, "PopUpClose Successful");
	}

	//Retrieving data from Read Excel File
	//Search for Bookshelves
	public static void search_bookshelves() throws Exception {
		ExtentTest test = extents.createTest("Bookshelves Search #5");
		log.debug("SearchBox Locate");
		WebElement wb = driver.findElement(PageObjectModel.searchBox);
		log.info("Searchbox Located Successfully");
		log.debug("Read Excel Data for Bookshelves");
		s = obj.readExcel(extents);
		log.info("Bookshelves Keyword read from Excel Successfully");
		test.log(Status.INFO, "Read from excel - Bookshelves, Done Successfully");
		wb.sendKeys(s[0]);
		log.debug("Click search button");
		wb.sendKeys(Keys.ENTER);
		log.info("Search Button Clicked");
		test.log(Status.INFO, "Bookshelves search Successful");
	}

	//Select Bookshelves Category
	public static void selectcategory_bookshelves() throws Exception {
		Thread.sleep(2000);
		ExtentTest test = extents.createTest("Category Selection for Bookshelves #6");
		log.debug("Select Category For Bookshelves");
		PropertiesFile.readPropertiesFile();
		if (browser.equalsIgnoreCase("Firefox")) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			WebElement HoverSelectCategory=driver.findElement(PageObjectModel.SelectCategory);
			js.executeScript("arguments[0].scrollIntoView(true);", HoverSelectCategory);
			WebElement ClickBookshelves=driver.findElement(PageObjectModel.BookshelvesCategoryClick);
			js.executeScript("arguments[0].click();",ClickBookshelves);
		} else {
			driver.findElement(PageObjectModel.SelectCategory).click();
			driver.findElement(PageObjectModel.BookshelvesCategoryClick).click();
		}
		log.info("Category Selection for Bookshelves Done Successfully");
		test.log(Status.INFO, "Bookshelves Category Selection Successful");
	}

	//Select Price Range for Bookshelves
	public static void select_price_for_bookshelves() {
		ExtentTest test = extents.createTest("Price Selection for Bookshelves #7");
		log.debug("Price Selection for Bookshelves");
		PropertiesFile.readPropertiesFile();
		if (browser.equalsIgnoreCase("Firefox")) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			WebElement PriceTabHover=driver.findElement(PageObjectModel.Pricetabclick);
			js.executeScript("arguments[0].scrollIntoView(true);", PriceTabHover);
			WebElement Selectbookshelfprice=driver.findElement(PageObjectModel.Selectbookshelfprice);
			js.executeScript("arguments[0].click();",Selectbookshelfprice);
		} else {
			driver.findElement(PageObjectModel.Pricetabclick).click();
			driver.findElement(PageObjectModel.Selectbookshelfprice).click();
		}
		log.info("Price Selected Successfully");
		test.log(Status.INFO, "Price Selected Successfully");
	}

	//Hover Over Storage Type For Bookshelves
	public static void actions() {
		ExtentTest test = extents.createTest("Hover Over Storage Type #8");
		Actions ac = new Actions(driver);
		log.debug("Hover Over Storage Type For Bookshelves");
		WebElement m1 = driver.findElement(PageObjectModel.HoverStorageType);
		ac.moveToElement(m1).build().perform();
		log.info("Hovered Over Storage Type Successfully");
		test.log(Status.INFO, "Hovered Over Storage Type Successfully");
	}

	//Select Open Storage for Bookshelves
	public static void selectOpenStorage() throws Exception {
		ExtentTest test = extents.createTest("Select Open Storage #9");
		log.debug("Select Open Storage for Bookshelves");
		driver.findElement(PageObjectModel.OpenStorage).click();
		log.info("OpenStorage Selected Successfully");
		test.log(Status.INFO, "OpenStorage Selected Successfully");
	}

	//Retrieving data from Read Excel File
	//Search for Study Chairs
	public static void search_studychairs() throws Exception {
		log.info("*****Study Chairs*****");
		ExtentTest test = extents.createTest("Study Chairs Search #12");
		log.debug("SearchBox Locate");
		WebElement wb3 = driver.findElement(PageObjectModel.searchBox);
		log.info("SearchBox Located Successfully");
		log.debug("Clear the contents of search box");
		wb3.sendKeys(Keys.CONTROL + "a");
		wb3.sendKeys(Keys.DELETE);
		log.info("Contents of Search Box cleared Successfully");
		log.debug("Read Excel Data for Study Chairs");
		s = obj.readExcel(extents);
		log.info("Study Chair Keyword read from Excel Successful");
		wb3.sendKeys(s[1]);
		log.debug("Hit Enter to start search for Study Chairs");
		wb3.sendKeys(Keys.ENTER);
		log.info("Study Chair Searched Successfully");
		test.log(Status.INFO, "Read from excel - Study Chairs, Done Successfully");
		test.log(Status.INFO, "Study Chairs search Successful");
	}

	//Category Selection for Study Chairs
	public static void selectcategory_studychairs() throws Exception {
		ExtentTest test = extents.createTest("Category Selection for Study Chairs #13");
		log.debug("Category Selection for Study Chairs");
		Thread.sleep(2000);
		PropertiesFile.readPropertiesFile();
		if (browser.equalsIgnoreCase("Firefox")) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			WebElement HoverSelectcategory=driver.findElement(PageObjectModel.SelectCategory);
			js.executeScript("arguments[0].scrollIntoView(true);", HoverSelectcategory);
			WebElement StudyChairsCategoryClick=driver.findElement(PageObjectModel.StudyChairsCategoryClick);
			js.executeScript("arguments[0].click();",StudyChairsCategoryClick);
		} else {
			driver.findElement(PageObjectModel.SelectCategory).click();
			driver.findElement(PageObjectModel.StudyChairsCategoryClick).click();
		}
		log.info("Study Chairs Category Selection Successful");
		test.log(Status.INFO, "Study Chairs Category Selection Successful");
	}

	//Retrieve Bookshelves Details from WebPage and write to Excel File 
	public static void writeExcel_Bookshelves() throws Exception {
		ExtentTest test = extents.createTest("Read Bookshelves Details from WebPage #11");
		String Bookshelves_Name[] = new String[3];
		String Bookshelves_Price[] = new String[3];
		String Bookshelves_EMI[] = new String[3];
		int items = 3;
		String sheetname = "Bookshelves";
		int sheetno = 0;
		log.debug("Read Bookshelves Details from WebPage");
		Bookshelves_Name[0] = driver.findElement(PageObjectModel.BookshelfName1).getText();
		Bookshelves_Price[0] = driver.findElement(PageObjectModel.BookshelfPrice1).getText();
		Bookshelves_EMI[0] = driver.findElement(PageObjectModel.BookshelfEMI1).getText();
		Bookshelves_Name[1] = driver.findElement(PageObjectModel.BookshelfName2).getText();
		Bookshelves_Price[1] = driver.findElement(PageObjectModel.BookshelfPrice2).getText();
		Bookshelves_EMI[1] = driver.findElement(PageObjectModel.BookshelfEMI2).getText();
		Bookshelves_Name[2] = driver.findElement(PageObjectModel.BookshelfName3).getText();
		Bookshelves_Price[2] = driver.findElement(PageObjectModel.BookshelfPrice3).getText();
		Bookshelves_EMI[2] = driver.findElement(PageObjectModel.BookshelfEMI3).getText();
		log.info("Bookshelves Details read from WebPage Done Successfully");
		test.log(Status.INFO, "Read Bookshelves details from webpage done Successfully");
		test.log(Status.INFO, "Bookshelves details written to Excel Successfully");
		log.debug("Write Bookshelves Details from WebPage to Excel File");
		obj.positive_testing_writeExcel(Bookshelves_Name, Bookshelves_Price, Bookshelves_EMI, items, sheetname, sheetno, extents);
		log.info("Bookshelves Details from WebPage written to Excel Successfully");
		log.info("**************************************************************************");

	}

	//Retrieve Study Chairs Details from WebPage and write to Excel File 
	public static void writeExcel_Chairs() throws Exception {
		ExtentTest test = extents.createTest("Read Study Chair Details from WebPage #15");
		String Chair_Name[] = new String[3];
		String Chair_Price[] = new String[3];
		String Chair_EMI[] = new String[3];
		int items = 3;
		String sheetname = "Chairs";
		int sheetno = 1;
		log.debug("Read Study Chair Details from WebPage");
		Chair_Name[0] = driver.findElement(PageObjectModel.ChairName1).getText();
		Chair_Price[0] = driver.findElement(PageObjectModel.ChairPrice1).getText();
		Chair_EMI[0] = driver.findElement(PageObjectModel.ChairEMI1).getText();
		Chair_Name[1] = driver.findElement(PageObjectModel.ChairName2).getText();
		Chair_Price[1] = driver.findElement(PageObjectModel.ChairPrice2).getText();
		Chair_EMI[1] = driver.findElement(PageObjectModel.ChairEMI2).getText();
		Chair_Name[2] = driver.findElement(PageObjectModel.ChairName3).getText();
		Chair_Price[2] = driver.findElement(PageObjectModel.ChairPrice3).getText();
		Chair_EMI[2] = driver.findElement(PageObjectModel.ChairEMI3).getText();
		log.info("Study Chair Details read from WebPage Done Successfully");
		test.log(Status.INFO, "Read Study Chair details from webpage done Successfully");
		test.log(Status.INFO, "Study Chair details written to Excel Successfully");
		log.debug("Write Study Chair Details from WebPage to Excel File");
		obj.positive_testing_writeExcel(Chair_Name, Chair_Price, Chair_EMI, items, sheetname, sheetno, extents);
		log.info("Study Chair Details from WebPage written to Excel Successfully");
	}

	//Retrieve Table Details from WebPage and write to Excel File
	public static void writeExcel_Tables() throws Exception {
		ExtentTest test = extents.createTest("Read Bedside Table Details from WebPage");
		String Table_Name[] = new String[3];
		String Table_Price[] = new String[3];
		String Table_EMI[] = new String[3];
		int items = 3;
		String sheetname = "Tables";
		int sheetno = 2;
		log.debug("Read Bedside Table Details from WebPage");
		Table_Name[0] = driver.findElement(PageObjectModel.TableName).getText();
		Table_Price[0] = driver.findElement(PageObjectModel.TablePrice).getText();
		Table_EMI[0] = driver.findElement(PageObjectModel.TableEMI).getText();
		Table_Name[2] = "Bedside Table Added to Cart Successfully";
		log.info("Bedside Table Details read from WebPage Done Successfully");
		test.log(Status.INFO, "Read Bedside Table details from webpage done Successfully");
		test.log(Status.INFO, "Bedside Table details written to Excel Successfully");
		log.debug("Write Bedside Table Details from WebPage to Excel File");
		obj.positive_testing_writeExcel(Table_Name, Table_Price, Table_EMI, items, sheetname, sheetno, extents);
		log.info("Bedside Table Details from WebPage written to Excel Successfully");
	}

	//Table Search function
	public static void search_tables() throws Exception {
		ExtentTest test = extents.createTest("Tables Test Case #5");
		log.debug("SearchBox Locate");
		WebElement wb = driver.findElement(PageObjectModel.searchBox);
		log.info("SearchBox Located Successfully");
		log.debug("Read Excel Data for Table");
		s = obj.readExcel(extents);
		log.info("Tables Keyword read from Excel Successful");
		wb.sendKeys(s[2]);
		log.debug("Hit Enter to start search for Tables");
		wb.sendKeys(Keys.ENTER);
		log.info("Tables Searched Successfully");
		test.log(Status.INFO, "Read from excel - Tables, Done Successfully");

		test.log(Status.INFO, "Hover Over Category");
		log.debug("Hover Over Category Type For Tables");
		Actions action = new Actions(driver);
		WebElement ele = driver.findElement(PageObjectModel.Tablescategory);
		action.moveToElement(ele).build().perform();
		log.info("Hovered Over Category Type Successfully");
		test.log(Status.INFO, "Hovered Over Category done Successfully");

		test.log(Status.INFO, "Select Bedside Tables Category");
		log.debug("Select Bedside Tables Category for Tables");
		driver.findElement(PageObjectModel.Tablescategoryclick).click();
		log.info("Bedside Tables Category Selected Sucessfully");
		test.log(Status.INFO, "Bedside Tables Category Selected Sucessfully");
		
		Thread.sleep(2000);

		test.log(Status.INFO, "Select Sort By Option");
		log.debug("Select Sort By Option for Bedside Tables");
		Thread.sleep(2000);
		PropertiesFile.readPropertiesFile();
		if (browser.equalsIgnoreCase("Firefox")) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			WebElement HoverSelectCategory=driver.findElement(PageObjectModel.SelectSort);
			js.executeScript("arguments[0].scrollIntoView(true);", HoverSelectCategory);
			WebElement ClickBookshelves=driver.findElement(PageObjectModel.TableSortClick);
			js.executeScript("arguments[0].click();",ClickBookshelves);
		} else {
			driver.findElement(PageObjectModel.SelectSort).click();
			Thread.sleep(1000);
			driver.findElement(PageObjectModel.TableSortClick).click();
		}
		log.info("Sort By Option - High To Low Selected Successfully for Bedside Tables");
		test.log(Status.INFO, "Sort By Option - High To Low Selected Successfully");
		Thread.sleep(2000);
		
		log.debug("Refresh Web Page");
		driver.navigate().refresh();
		log.info("Refresh Web Page Successful");
		Thread.sleep(3000);
	}

	//Selection of First Table displayed on the WebPage
	public static void select_table() throws Exception {
		ExtentTest test = extents.createTest("Select Table #6");
		test.log(Status.INFO, "Select first Table that is displayed");
		log.debug("Select first Bedside Table that is displayed");
		driver.findElement(PageObjectModel.Tablesclick).click();
		log.info("First Displayed Bedside Table Selected Successfully");
		test.log(Status.INFO, "First displayed Bedside Table selected successfully");

		test.log(Status.INFO, "Window Handle");
		log.debug("Window Handle");
		Set<String> Pages = driver.getWindowHandles();
		Object WebPages[] = Pages.toArray();
		driver.switchTo().window(WebPages[1].toString());
		log.info("Window Handle Done Successfully");
		test.log(Status.INFO, "Window Handle Done Successfully");

		writeExcel_Tables();
		Thread.sleep(2000);

		test.log(Status.INFO, "Click on Add to Cart");
		log.debug("Click on Add to Cart");
		driver.findElement(PageObjectModel.AddtoCart).click();
		log.info("Add to Cart Clicked Successfully");
		test.log(Status.INFO, "Add to Cart Clicked Successfully");

		log.debug("Take Screenshot of Bedside Table");
		ExtentTest test1 = ReUseable_Classes.screenShot(driver);
		log.info("Bedside Table Screenshot Taken Successfully");
		test1.log(Status.INFO, "Bedside Table details Screen Shot taken Successfully");

		closeBrowser();

		test.log(Status.INFO, "Bedside Table added to Cart Successfully");
	}

	//Negative Testing for Invalid Email using SignUp PopUp
	public static void negative_testing_SignupPopup() throws Exception 
	{
		ExtentTest test = extents.createTest("Negative Testing SignUp PopUp #1");
			Thread.sleep(2000);
			test.log(Status.INFO, "Enter the Email ID Detail");
			log.debug("Enter the Email ID Detail");
			Thread.sleep(1000);
			WebElement ele = driver.findElement(PageObjectModel.Email);
			s = obj.readExcel(extents);
			ele.sendKeys(s[3]);
			test.log(Status.INFO, "Email ID Detail Entered Successfully");
			log.info("Email ID Detail Entered Successfully");
			test.log(Status.INFO, "Read from excel - Email ID, Done Successfully");
			
			test.log(Status.INFO, "Click on Sign Up Button");
			log.debug("Click on Sign Up Button");
			driver.findElement(PageObjectModel.EmailSignUpBtn).click();
			test.log(Status.INFO, "Sign Up Button Clicked Successfully");
			log.info("Sign Up Button Clicked Successfully");
			
			Thread.sleep(2000);
			log.debug("Take Screenshot For Invalid Email ID");
			ExtentTest test1 = ReUseable_Classes.screenShot(driver);
			log.info("Invalid Email ID, Screen Shot taken Successfully");
			test1.log(Status.INFO, "Invalid Email ID, Screen Shot taken Successfully");

			String data[] = new String[2];
			data[0] = "Negative Testing for Invalid Email";
			test.log(Status.INFO, "Retrieve error message for Invalid Email");
			log.debug("Retrieve error message for Invalid Email");
			data[1] = driver.findElement(PageObjectModel.InvalidEmailError).getText();
			log.info("Error message for Invalid Email Retrieved Successfully");
			test.log(Status.INFO, "Error message for Invalid Email Retrieved Successfully");
			
			String sheetname = "Invalid Email";
			int sheetno = 0;
			int items = 1;
			
			test.log(Status.INFO, "Write Error message for Invalid Email ID from WebPage to Excel File");
			log.debug("Write Error message for Invalid Email ID from WebPage to Excel File");
			
			obj.negative_testing_writeExcel(data, sheetname, sheetno, items, extents);
			
			log.info("Error message for Invalid Email ID from WebPage written to Excel File Successfully");
			test.log(Status.INFO, "Error message for Invalid Email ID from WebPage written to Excel File Successfully");
			
			Thread.sleep(1000);
			
			try {
				Assert.assertTrue(driver.findElement(PageObjectModel.Assrt_NegativePopUPCheck).isDisplayed());
				ReUseable_Classes.reportPass("Negative Testing SignUp PopUp #1 FAILED");
			} catch (Exception e) {
				ReUseable_Classes.reportFail("Negative Testing SignUp PopUp #1 PASSED, "+data[1]);
			}

	}

	//Negative Testing for Invalid Phone Number using Track Orders
	public static void negative_testing_TrackOrders() throws Exception 
	{
		ExtentTest test = extents.createTest("Negative Testing Track Orders #2");
			test.log(Status.INFO, "Click on Track Order");
			log.debug("Click on Track Order");
			driver.findElement(PageObjectModel.TrackOrder).click();
			test.log(Status.INFO, "Track Order Clicked Successfully");
			log.info("Track Order Clicked Successfully");
			
			test.log(Status.INFO, "Enter the Order No Detail");
			log.debug("Enter the Order No Detail");
			Thread.sleep(1000);
			WebElement orderno = driver.findElement(PageObjectModel.OrderNo);
			s = obj.readExcel(extents);
			orderno.sendKeys(s[4]);
			test.log(Status.INFO, "Order No Detail Entered Successfully");
			log.info("Order No Detail Entered Successfully");
			test.log(Status.INFO, "Read from excel - Order No, Done Successfully");
			
			test.log(Status.INFO, "Enter the Phone No Detail");
			log.debug("Enter the Phone No Detail");
			Thread.sleep(1000);
			WebElement phoneno = driver.findElement(PageObjectModel.PhoneNo);
			phoneno.sendKeys(s[5]);
			test.log(Status.INFO, "Phone No Detail Entered Successfully");
			log.info("Phone No Detail Entered Successfully");
			
			test.log(Status.INFO, "Read from excel - Phone No, Done Successfully");
			
			log.debug("Click on Submit Button");
			driver.findElement(PageObjectModel.SubmitBtnTrackOrder).click();
			log.info("Submit Button Clicked Successfully");
			
			Thread.sleep(2000);
			log.debug("Take Screenshot For Invalid Phone No");
			ExtentTest test1 = ReUseable_Classes.screenShot(driver);
			log.info("Invalid Phone No, Screen Shot taken Successfully");
			test1.log(Status.INFO, "Invalid Phone No, Screen Shot taken Successfully");
			
			String data[] = new String[2];
			data[0] = "Negative Testing for Invalid Phone No";
			test.log(Status.INFO, "Retrieve error message for Invalid Phone No");
			log.debug("Retrieve error message for Invalid Phone No");
			data[1] = driver.findElement(PageObjectModel.InvalidPhoneNoError).getAttribute("title");
			log.info("Error message for Invalid Phone No Retrieved Successfully");
			test.log(Status.INFO, "Error message for Invalid Phone No Retrieved Successfully");
			
			String sheetname = "Invalid Phone No";
			int sheetno = 1;
			int items = 1;
			
			test.log(Status.INFO, "Write Error message for Invalid Phone No from WebPage to Excel File");
			log.debug("Write Error message for Invalid Phone No from WebPage to Excel File");
			
			obj.negative_testing_writeExcel(data, sheetname, sheetno, items, extents);
			
			log.info("Error message for Invalid Phone No from WebPage written to Excel File Successfully");
			test.log(Status.INFO, "Error message for Invalid Phone No from WebPage written to Excel File Successfully");
			
			Thread.sleep(1000);
			
			try {
				Assert.assertTrue(driver.findElement(PageObjectModel.Assrt_NegativeTrackOrdersCheck).isDisplayed());
				ReUseable_Classes.reportPass("Negative Testing Track Orders #2 FAILED");
			} catch (Exception e) {
				ReUseable_Classes.reportFail("Negative Testing Track Orders #2 PASSED, "+data[1]);
			}
		
	}

	//Close Browser Function
	public static void closeBrowser() {
		ExtentTest test = extents.createTest("Close Browser #");
		log.debug("Close Browser");
		driver.quit();
		log.info("Browser Closed Successfully");
		log.info("**************************************************************************");
		test.log(Status.INFO, "Browser Closed Successfully");
		extents.flush();
	}

	//Extent Reports, object pass function
	public static ExtentReports extentpass() {
		return extents;
	}
	
	//WebDriver Object, Return Function
	public static WebDriver drv() {
		return driver;
	}
}
