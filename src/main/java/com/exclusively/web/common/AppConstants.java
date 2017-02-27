package com.exclusively.web.common;

public interface AppConstants {
	
/* ---Configurations Constants--- */
	
	public  final String SETUP_CONFIGFILE_LOCATION = "//Users//ex//Selenium-java-Framework//ConfigFiles//common//SetUpConfig.properties";	

	public  final String CONFIG_FILES_LOCATION = "CONFIG_FILES_LOCATION";
	
	
	/* ---Website Constants--- */
	
	public  final String EXCLUSIVELY_HOMEPAGE = "http://www.exclusively.com/";
	
	
	/* ---Registration Constants begins--- */
	
	public  final String EXCLUSIVELY_PROD_USERNAME = "EXCLUSIVELY_PROD_USERNAME";
	public  final String EXCLUSIVELY_PROD_PASSWORD = "EXCLUSIVELY_PROD_PASSWORD";
	public  final String USERNAME_TEXT ="USERNAME_TEXT";
	public  final String NEW_USERNAME_TEXT ="NEW_USERNAME_TEXT";
	
	//Properties file Path Constants
	public  final String EXCLUSIVELY_WEB_DATASTATUSFILE_LOCATION  = "Exclusively/Exclusively_web_datastatus_file_login.properties";
	public  final String EXCLUSIVELY_WEB_STATICDATAFILE_LOCATION =  "Exclusively/Exclusively_web_staticdata_file_login.properties";
	
	/* ---Registration Constants End--- */
	
	/* ---Sanity Constants begins--- */
	
	//Properties file Path Constants
	public  final String EXCLUSIVELY_SANITY_DATASTATUSFILE_LOCATION  = "Sanity/Exclusively_web_datastatus_sanity.properties";
	public  final String EXCLUSIVELY_SANITY_STATICDATAFILE_LOCATION =  "Sanity/Exclusively_web_staticdata_sanity.properties";
		
	public  final String PAYMENT_TYPE_TEXT ="PAYMENT_TYPE_TEXT";
	public  final String SANITY_CATEGORY ="SANITY_CATEGORY";
	public  final String PRODUCT_SELECTION_NO ="PRODUCT_SELECTION_NO";
	public  final String PAYMENT_METHOD ="PAYMENT_METHOD";
	public  final String PRODUCT_URL ="PRODUCT_URL";
	
	//shipping Details
	public  final String SHIPPING_FIRST_NAME="SHIPPING_FIRST_NAME";
	public  final String SHIPPING_LAST_NAME="SHIPPING_LAST_NAME";
	public  final String SHIPPING_EMAIL="SHIPPING_EMAIL";
	public  final String SHIPPING_TELEPHONE="SHIPPING_TELEPHONE";
	public  final String SHIPPING_CUSTOMER_PASSWORD="SHIPPING_CUSTOMER_PASSWORD";
	public  final String SHIPPING_CONFIRM_PASSWORD="SHIPPING_CONFIRM_PASSWORD";
	public  final String SHIPPING_STREET_ADDRESS1="SHIPPING_STREET_ADDRESS1";
	public  final String SHIPPING_STREET_ADDRESS2="SHIPPING_STREET_ADDRESS2";
	public  final String SHIPPING_REGION_ID="SHIPPING_REGION_ID";
	public  final String SHIPPING_CITY="SHIPPING_CITY";
	public  final String SHIPPING_POSTCODE="SHIPPING_POSTCODE";
	
	
	
	/* ---Sanity Constants End--- */
	
	
	

	/* ---General Constants--- */

	public  final String YES =  "Y";

	/* ---Capture Screen Shot for ERROR Cases--- */
	
	public final String ERROR_SCREENSHOT_FILE_PATH = "ERROR_SCREENSHOT_FILE_PATH";
	
	public final String ERROR_SCREENSHOT_FILE_DATEFORMAT = "ERROR_SCREENSHOT_FILE_DATEFORMAT";
	
	public final String ERROR_SCREENSHOT_FILE_EXTN = "ERROR_SCREENSHOT_FILE_EXTN";
	
	public final String DOT = ".";
	
	public final String UNDER_SCORE = "_";
	
	public final String ERROR_SCREENSHOT_FILE_PREFIX = "ERROR_SCREENSHOT_FILE_PREFIX";
	
	public final String NEW_LINE = "\n";
	
	public final String COLON = " : ";
	
	public final String GREATER_SIGN = " > ";
	
	public  final String EXECUTE_TESTCASE =  "common/Execute.properties";
	public  final String EXECUTE_STATUS_TESTCASE= "common/Execute_Status.properties";
	
	public  final String COMMA =  ",";
	
	public final String DOWNLOADS_PATH ="D:/Users/"+System.getProperty("user.name")+"/Downloads";
	
	
}
