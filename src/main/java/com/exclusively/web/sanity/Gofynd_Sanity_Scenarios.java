
package com.exclusively.web.sanity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.exclusively.web.common.CommonUtility;
import com.exclusively.web.common.GoFynd_constants;
import com.exclusively.web.common.AppConstants;
import com.exclusively.web.common.AppConstants;


public class Gofynd_Sanity_Scenarios extends CommonUtility {
	
	protected  Logger log = Logger.getLogger(Exclusively_SanityScenarios_Util.class);
	
	//static or dynamic data flag
	private  Properties sourceDataStatusAccounts = new Properties();

	//static data 
	private  Properties sourceStaticDataAccounts = new Properties();
	
	private String  usernameText = new String();
	private String  paymentType = new String();
	private String  payutext = new String();


	protected  void setUp() throws InterruptedException{
		// try{
		loadSetUpConfig();
		setUpDriver();
		readSourceDataFiles();
		/*signInExclusively_prod(
						 CommonUtility.prop.getProperty(AppConstants.EXCLUSIVELY_PROD_USERNAME),
						 CommonUtility.prop.getProperty(AppConstants.EXCLUSIVELY_PROD_PASSWORD));*/
		}

	 /* Description: Select the Category
	 * Parameters: CategoryName
	 * Return: None
	 */            
	protected void SelectCategory() throws InterruptedException 
	{
		// load Home Page
		loadPage(GoFynd_constants.GOFYND_HOMEPAGE);
		//Close the Popup
		//waitgetForPageLoad(30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root-wrapper']/div/div/div[7]/div[1]")));
		//WebElement loginPopupClose =getDriver().findElement(By.xpath(".//*[@id='root-wrapper']/div/div/div[7]/div[1]"));
		//loginPopupClose.click();
		///jsHandle().executeScript("window.scrollBy(0,150)", "");
		///Thread.sleep(2000);
		
		//Select the Category
		
//		String categorySelector =("//*[contains(text(),' "+category+" ')]");
//		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(categorySelector)));		
//		System.out.println(categorySelector);
//		getDriver().findElement(By.xpath(categorySelector)).click();
//		System.out.println("Category selected done");
//		log.info("Category Selected: "+category );
	}
	

	 /* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            
	protected void addToCart() throws InterruptedException 
	{
		// click add to Cart Button
		waitgetForPageLoad(30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='addtocart']")));
		WebElement addToCart = getDriver().findElement(By.xpath(".//*[@id='addtocart']"));
		addToCart.click();
		System.out.println("product is added in the cart Done");
	}
	
	 /* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            
	protected void RegisterUserLoginOnCheckOutPage() throws InterruptedException 
	{
		//Register User Login
		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("login-email")));
		WebElement loginEmail = getDriver().findElement(By.id("login-email"));
		loginEmail.clear();
		loginEmail.sendKeys(CommonUtility.prop.getProperty(AppConstants.EXCLUSIVELY_PROD_USERNAME));
		WebElement loginPassword = getDriver().findElement(By.id("login-password"));
		loginPassword.clear();
		loginPassword.sendKeys(CommonUtility.prop.getProperty(AppConstants.EXCLUSIVELY_PROD_PASSWORD));
		WebElement loginButton = getDriver().findElement(By.xpath(".//*[@class='button loginBtn']"));
		loginButton.click();
	
	}
	
	 /* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            

	/**
 	 * Reads the Bills n Reload page source data
 	 * to get the dynamic/static data
 	 */
     private void readSourceDataFiles(){
    	String statusFileName = CommonUtility.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
    											   .concat(AppConstants.EXCLUSIVELY_SANITY_DATASTATUSFILE_LOCATION);
    	sourceDataStatusAccounts = loadFileData(statusFileName);
    	
    	String dataFileName = CommonUtility.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
												 .concat(AppConstants.EXCLUSIVELY_SANITY_STATICDATAFILE_LOCATION);
    	sourceStaticDataAccounts =  loadFileData(dataFileName);
    	/*System.out.println("Source Data Status :"+ sourceDataStatus);
    	System.out.println("Source Static Data :"+ sourceStaticData);*/
     }
     
     
     /**
      * Get the static source data 
      */
     
     protected String getSourceData(String inputData){
    	 String returnData = null;
    	 
    	 if(sourceDataStatusAccounts.containsKey(inputData)){
    		 returnData = findSourceData(inputData);
    		 log.info("Success : Static Source data '"+ inputData + "' -->"+returnData );
    		// System.out.println("Success : Static Source data '"+ inputData + "' -->"+returnData );
    	 }else{
    		 returnData= "Fail :" + inputData + " Not found in Status File" ;
    		 log.error("Fail :" + inputData + " Not found in Status File");
    	 }
    	 
    	 return returnData;
     }
     
     
     /**
      * read the static source data 
      * @param inputData
      * @return
      */
     private String findSourceData(String inputData){
    	 String returnData = null;
    	 String status = sourceDataStatusAccounts.getProperty(inputData);
    	 if(status.equalsIgnoreCase(AppConstants.YES)){
    		 returnData = sourceStaticDataAccounts.getProperty(inputData);
    	 }else{
    		 //String tempData = sourceStaticDataAccounts.getProperty(inputData);
    		 //int separatorIndex = tempData.trim().indexOf("|");
 
    	 }
    	 return returnData;
     }
     

//getter/Setter
           
	public String getUserNameText() {
		return usernameText;
	}



	protected String getPaymentType() {
		return paymentType;
	}

	 public String getPayutext() {
		return payutext;
	}


     
}
	