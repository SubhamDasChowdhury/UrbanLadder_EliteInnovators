package com.UrbanLadder.Utilities;
import org.openqa.selenium.By;

public class PageObjectModel 
{
	/********************************Positive Testing********************************/
	
	/*****************General Objects*****************/
	public static final By searchBox=(By.id("search"));
	public static final By ClosePopup=(By.xpath("//a[@class='close-reveal-modal hide-mobile']"));
	
	/******************Bookshelf Objects*****************/
	public static final By SelectCategory=(By.xpath("//div[contains(text(),'Category')]"));
	public static final By BookshelvesCategoryClick=(By.id("filters_primary_category_Bookshelves"));
	public static final By StudyChairsCategoryClick=(By.id("filters_primary_category_Study_Chair"));
	public static final By Pricetabclick=(By.xpath("//div[contains(text(),'Price')]"));
	public static final By Selectbookshelfprice=(By.xpath("//input[@id='price_limit_9000-15999']"));
	public static final By HoverStorageType=(By.xpath("//div[contains(text(),'Storage Type')]"));
	public static final By OpenStorage=(By.xpath("//input[@id='filters_storage_type_Open']"));
	public static final By Exclude_OutOfStock=(By.id("filters_availability_In_Stock_Only"));
	public static final By SortBy_Recommended=(By.xpath("//li[@class='selected']"));
	public static final By BookshelfName1=(By.xpath("(//span[@class='name'])[1]"));
	public static final By BookshelfPrice1=(By.xpath("(//div[@class='price-number'])[1]"));
	public static final By BookshelfEMI1=(By.xpath("(//div[@class='price-text']/span)[1]"));
	public static final By BookshelfName2=(By.xpath("(//span[@class='name'])[2]"));
	public static final By BookshelfPrice2=(By.xpath("(//div[@class='price-number'])[2]"));
	public static final By BookshelfEMI2=(By.xpath("(//div[@class='price-text']/span)[2]"));
	public static final By BookshelfName3=(By.xpath("(//span[@class='name'])[3]"));
	public static final By BookshelfPrice3=(By.xpath("(//div[@class='price-number'])[3]"));
	public static final By BookshelfEMI3=(By.xpath("(//div[@class='price-text']/span)[3]"));
	
	/*****************Chair Objects*****************/
	public static final By ChairName1=(By.xpath("(//span[@class='name'])[1]"));
	public static final By ChairPrice1=(By.xpath("(//div[@class='price-number'])[1]"));
	public static final By ChairEMI1=(By.xpath("(//div[@class='price-text']/span)[1]"));
	public static final By ChairName2=(By.xpath("(//span[@class='name'])[2]"));
	public static final By ChairPrice2=(By.xpath("(//div[@class='price-number'])[2]"));
	public static final By ChairEMI2=(By.xpath("(//div[@class='price-text']/span)[2]"));
	public static final By ChairName3=(By.xpath("(//span[@class='name'])[3]"));
	public static final By ChairPrice3=(By.xpath("(//div[@class='price-number'])[3]"));
	public static final By ChairEMI3=(By.xpath("(//div[@class='price-text']/span)[3]"));
	
	/*****************Table Objects*****************/
	public static final By Tablescategory=(By.xpath("(//div[@class='gname'])[1]"));
	public static final By Tablescategoryclick=(By.xpath("//input[@id='filters_primary_category_Bedside_Tables']"));
	
	public static final By SelectSort=(By.xpath("//div[@class='item']//div[@class='gname']"));
	public static final By TableSortClick=(By.xpath("//li[contains(text(),'Price: High to Low')]"));
	
	public static final By Tablesclick=(By.xpath("//li[1]//div[1]//a[1]//img[1]"));
	
	public static final By TableName=(By.xpath("//h1[@class='product-title']"));
	public static final By TablePrice=(By.xpath("//div[@class='price discounted-price']"));
	public static final By TableEMI=(By.xpath("//ul[@class='emi-offers']/li/b"));
	
	public static final By AddtoCart=(By.xpath("//div[@class='add-to-cart text-center']"));
	
	/*****************Assertion Objects*****************/
	public static final By Assrt_PopUpDisplay=(By.xpath("//div[@class=\"vert-container\"]"));
	public static final By Assrt_BookshelvesSearch=(By.xpath("(//span[@class='key'])[1]"));
	public static final By Assrt_BookshelvesCategory=(By.xpath("(//span[@class='text'])[1]"));
	public static final By Assrt_BookshelvesPrice=(By.xpath("//li[@class=\"selrange-filter\"]"));
	public static final By Assrt_BookshelvesHoverStorageType=(By.xpath("(//span[@class='filter-name'])[3]"));
	
	public static final By Assrt_StudyChairSearch=(By.xpath("(//span[@class='key'])[1]"));
	public static final By Assrt_StudyChairCategory=(By.xpath("(//span[@class='text'])[1]"));
	
	/*********************************************************************************/

	/********************************Negative Testing********************************/
	
	/*****************Sign In PopUp Objects*****************/
	public static final By Email=(By.id("spree_user_email"));
	public static final By Password=(By.id("spree_user_password"));
	public static final By EmailSignUpBtn=(By.xpath("//input[@value='Sign Up']"));
	public static final By InvalidEmailError=(By.xpath("(//label[@class='error'])[1]"));

	/*****************Track Order Objects*****************/
	public static final By TrackOrder=(By.xpath("(//a[@class=\"inherit contact-channel\"])[2]"));
	public static final By OrderNo=(By.xpath("//input[@id='ip_379403698']"));
	public static final By PhoneNo=(By.xpath("//input[@id='ip_394711104']"));
	public static final By SubmitBtnTrackOrder=(By.xpath("//span[contains(text(),'Submit')]"));
	public static final By InvalidPhoneNoError=(By.xpath("//input[@title=\"Please enter valid 10 digit mobile number\"]"));
	
	//Assertion Check For Negative Testing
	public static final By Assrt_NegativePopUPCheck=(By.xpath("//h1[@class='hide-mobile account_header']"));
	public static final By Assrt_NegativeTrackOrdersCheck=(By.xpath("//p[@class='_33bg3']"));
	
	/*********************************************************************************/







}
