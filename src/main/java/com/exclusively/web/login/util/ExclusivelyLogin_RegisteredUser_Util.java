package com.exclusively.web.login.util;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import com.exclusively.web.common.AppConstants;

import com.exclusively.web.common.CommonUtility;


public class ExclusivelyLogin_RegisteredUser_Util extends CommonUtility {
	
	protected  Logger log = Logger.getLogger(ExclusivelyLogin_RegisteredUser_Util.class);
	
	//static or dynamic data flag
	private  Properties sourceDataStatusAccounts = new Properties();

	//static data 
	private  Properties sourceStaticDataAccounts = new Properties();
	
	private String  usernameText = new String();

	protected  void setUp() throws InterruptedException{
		// try{
		loadSetUpConfig();
		setUpDriver();
		readSourceDataFiles();
		signInExclusively_prod(
						 CommonUtility.prop.getProperty(AppConstants.EXCLUSIVELY_PROD_USERNAME),
						 CommonUtility.prop.getProperty(AppConstants.EXCLUSIVELY_PROD_PASSWORD));
		}

	

	 /* Description: Testing the Account No
	 * Parameters: None
	 * Return: None
	 */            
    protected void fetchUserName() throws InterruptedException{
   	 
   	 waitgetForPageLoad(60).until(ExpectedConditions.invisibilityOfElementLocated((By.id("email"))));
   	 WebElement username=tryToGetElementByXPath(".//*[@id='js_loginlink']", 5);
        usernameText=username.getText();
      
        
        
        log.info("Success : To get the Username: "+usernameText);
        System.out.println("UserName : "+usernameText );              
    }
            
    /* Description: Log Out Functionality
     * Parameters: None
     * Return: None
     */            
        protected void LogOut() throws InterruptedException{    	 
       	 //Thread.sleep(1000);
       	 Actions move = new Actions(getDriver());
       	 WebElement username=getDriver().findElement(By.xpath(".//*[@id='js_loginlink']" ));
       	 move.moveToElement(username).click().build().perform();
       	//move.moveToElement(username).moveToElement(getDriver().findElement(By.xpath(".//a[@title='Logout']"))).click().build().perform();
       	 //System.out.println("mouse over on button on the button");
       	waitgetForPageLoad(10).until(ExpectedConditions.visibilityOfElementLocated((By.xpath(".//a[@title='Logout']"))));
       	getDriver().findElement(By.xpath(".//a[@title='Logout']")).click();                                                                          
	        log.info("Success : Logout functionality" );         
        }
	        
        
        public String getresult(String Query)
        {
        	return executeQuery(Query);
        }
	        
    /* Description: Testing the Account No
     * Parameters: None
     * Return: None
     */            
        protected void userregistration() throws InterruptedException{
       	 
        WebDriverWait wait = new WebDriverWait(getDriver(),30);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_loginlink")));
        getDriver().findElement(By.id("js_loginlink")).click();
   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("join_email")));
   		
   		//getDriver().findElement(By.id("joinExc")).click();
   		getDriver().findElement(By.id("join_firstname")).clear();
   		getDriver().findElement(By.id("join_firstname")).sendKeys("Pramod");
   		getDriver().findElement(By.id("join_lastname")).clear();
   		getDriver().findElement(By.id("join_lastname")).sendKeys("Kumar");
   		//System.out.print(driver.findElement(By.cssSelector("#email")).isEnabled());
   		//WebElement register = getDriver().findElement(By.id("register"));
   		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar.getInstance().getTime());
   		System.out.println("email id : "+"P"+timeStamp+"@gmail.com");
   		log.info("email id : "+"P"+timeStamp+"@gmail.com");
   		getDriver().findElement(By.id("join_email")).sendKeys("P"+timeStamp+"@gmail.com");
   		getDriver().findElement(By.id("join_password")).clear();
   		getDriver().findElement(By.id("join_password")).sendKeys("India12345");
   		getDriver().findElement(By.xpath(".//*[@id='joinButton']")).click();
   		Thread.sleep(1000);
   		String username = getDriver().findElement(By.id("js_loginlink")).getText();
   		System.out.println(username);
       	 
        }
	
	/**
 	 * Reads the Bills n Reload page source data
 	 * to get the dynamic/static data
 	 */
     private void readSourceDataFiles(){
    	String statusFileName = CommonUtility.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
    											   .concat(AppConstants.EXCLUSIVELY_WEB_DATASTATUSFILE_LOCATION);
    	sourceDataStatusAccounts = loadFileData(statusFileName);
    	
    	String dataFileName = CommonUtility.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
												 .concat(AppConstants.EXCLUSIVELY_WEB_STATICDATAFILE_LOCATION);
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
     
     
	 /* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            
	protected void clearCart() throws InterruptedException 
	{	getDriver().navigate().to("http://www.exclusively.com/checkout/cart/index/");
		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='col-delete a-center last']/a[1]")));
	    List <WebElement> noOfItems = getDriver().findElements(By.xpath(".//*[@class='col-delete a-center last']/a[1]"));
		for (WebElement item : noOfItems) {	
			item.click();
			waitgetForPageLoad(30).until(ExpectedConditions.alertIsPresent());
	            getDriver().switchTo().alert().accept();;
			Thread.sleep(4000);	
		}
	}

//getter/Setter
           
	public String getUserNameText() {
		return usernameText;
	}

     
}
	