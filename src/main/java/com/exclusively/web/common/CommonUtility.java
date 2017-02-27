package com.exclusively.web.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.exclusively.web.common.jdbcConnectivity;


public class CommonUtility extends jdbcConnectivity {
	
	public static Properties prop = new Properties();
	
	private  Wait<WebDriver> wait = null;
	
	private  WebDriver driver = null;
	
	private  Logger log = Logger.getLogger(CommonUtility.class);
	
	private static File setupConfigFile = null;
	
	private static FileInputStream fileInput = null;
	
	private  String DRIVER_CLASS = null;
	
	private Properties fileData = new Properties();

	

	
	private static WebDriver ssWebSriver= null;
	
	protected  void setUp() throws InterruptedException{
		loadSetUpConfig();
		setUpDriver();
	}
	//Load the SetupConfig.properties File
	protected   void loadSetUpConfig(){
		
		if(setupConfigFile == null || fileInput == null){
			setupConfigFile = new File(AppConstants.SETUP_CONFIGFILE_LOCATION);
			  
			try {
				fileInput = new FileInputStream(setupConfigFile);
				System.out.println("Info : loading SetupConfig File ");
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
				log.error("Fail: loadSetUpConfig(): Config file not found to initialize the Selenium Framework");
				System.exit(0);
			}
		}else{
			System.out.println("Info : SetupConfig File is already loaded");
			    log.info("Info : SetupConfig File is already loaded");
		}
		//Properties prop = new Properties();
		
		//load properties file
		try {
			if(prop.isEmpty()){
				prop.load(fileInput);
				System.out.println("SetUp Data initialized" );
			}
				loadLogger();
			log.info("SetUp Data initialized: " + prop);
			//System.out.println("SetUp Data : " + prop); //TBD comment or removed
		} catch (IOException e) {
			//e.printStackTrace();
			//System.out.println("Fail : Cann't Load the  SetuUp Config file");
			log.error("Fail : Cann't Load the  SetuUp Config file...exiting the program");
			//e.printStackTrace();
			System.exit(0);
		}

	}
	//load the logger
	private  void  loadLogger(){
		Properties logProperties = new Properties();
		
		try
	    {
	      // load our log4j properties / configuration file
		  String logfile = prop.getProperty("LOG_FILE") ;
	      logProperties.load(new FileInputStream(prop.getProperty("LOG_FILE")));
	      PropertyConfigurator.configure(logProperties);
	      log.info("Logging initialized.");
	    }
	    catch(IOException e)
	    {
	      //throw new RuntimeException("Unable to load logging File " + prop.getProperty("LOG_FILE"));
	      log.error("Unable to load logging File " + prop.getProperty("LOG_FILE"));
	    }
	}
	
	  private static boolean isSupportedPlatform() {
		    Platform current = Platform.getCurrent();
		    return Platform.MAC.is(current) || Platform.WINDOWS.is(current);
		  }
	
	
	protected  void setUpDriver(){

			//load the configuration file
			loadSetUpConfig();
			
			log.info("DriverClass : "+DRIVER_CLASS);	
			String DRIVER_NAME=prop.getProperty("DRIVER");
			System.out.println(DRIVER_NAME);
			
//			switch(DRIVER_NAME)
//			{
//			case "CHROME":{
//							DRIVER_CLASS = prop.getProperty("DRIVER_CLASS");
//							String DRIVER_LOC = prop.getProperty("DRIVER_LOCATION");
//							//try{
//							System.setProperty(DRIVER_CLASS, DRIVER_LOC);
//							System.out.println("DRIVER_CLASS : " +DRIVER_CLASS );
//							System.out.println("DRIVER_LOC : " +DRIVER_LOC );
//							driver = new ChromeDriver();
//								} break;
//					
//			case "FIREFOX":{
//							System.out.println("Firefox driver is selected");
//							driver = new FirefoxDriver();		
//								} break;
//			
//			case "SAFARI":{
//							System.out.println("Safari is Selected");
//							driver = new SafariDriver();	
//								}break;
//			default:{
//						System.out.println("Firefox driver is selected");
//						driver = new FirefoxDriver();			
//					}break;
//			}
			
			if( DRIVER_NAME=="CHROME")
			{
				DRIVER_CLASS = prop.getProperty("DRIVER_CLASS");
				String DRIVER_LOC = prop.getProperty("DRIVER_LOCATION");
				//try{
				System.setProperty(DRIVER_CLASS, DRIVER_LOC);
				System.out.println("DRIVER_CLASS : " +DRIVER_CLASS );
				System.out.println("DRIVER_LOC : " +DRIVER_LOC );
				driver = new ChromeDriver();
				
			} else if(DRIVER_NAME=="FIREFOX")
				
			{
				System.out.println("Firefox driver is selected");
				driver = new FirefoxDriver();
				
			}else if (DRIVER_NAME=="SAFARI")
			{
				System.out.println("Safari is Selected");
				driver = new SafariDriver();
			}
			else
			{
				DRIVER_CLASS = prop.getProperty("DRIVER_CLASS");
				String DRIVER_LOC = prop.getProperty("DRIVER_LOCATION");
				//try{
				System.setProperty(DRIVER_CLASS, DRIVER_LOC);
				System.out.println("DRIVER_CLASS : " +DRIVER_CLASS );
				System.out.println("DRIVER_LOC : " +DRIVER_LOC );
				driver = new ChromeDriver();
			}
			
			
			

			
			
			
				// Put a Implicit wait, this means that any search for elements on the page
				waitForPageLoad(30);
				//maximize the browser window
				driver.manage().window().maximize();
				
				//set the driver for Listerner class - captures the ScreenShot
				setSsWebSriver(getDriver());
				
				log.info("Success : Driver Initialized Successfully");
			}
			private void elseif(boolean b) {
		// TODO Auto-generated method stub
		
	}
			/*catch (Exception e) {
				//e.printStackTrace();
				//System.out.println("Fail : Driver Initialization Failed");
				log.error("Fail : Driver to Initialize the browser is Failed...exiting the program"); 
				//e.printStackTrace();
				System.exit(0);
			}*/
	//}
	
	
	/**
	 * Test the Page URL
	 * @param pageURL
	 * @throws Exception 
	 */
	protected  void loadPage(String pageURL) {
		//try{
			getDriver().get(pageURL);
		log.info("Success : Page '"+ prop.getProperty(pageURL) + "' loaded successfully");
		/*}catch(Exception e) {
			//System.out.println("Fail : Load to Page '"+prop.getProperty("PAGE_URL") +"' Failed");
			log.error("Fail : Load to Page '"+prop.getProperty("PAGE_URL") +"' Failed ....exiting the program");
			//e.printStackTrace();
			//System.exit(0);
			throw(e);
		}*/
	}
	
	/**
	 * load the property file
	 * @param String filename
	 * @return property file 
	 * @throws Exception 
	 */
	protected Properties loadFileData(String  fileName) {
		Properties propertyFile = new Properties();
		
		   File sourceDataFile = null;
    	   FileInputStream sourceDataFileInput = null;

    	   if(sourceDataFile == null  ){
    		   sourceDataFile = new File(fileName);
   			try {
   				sourceDataFileInput = new FileInputStream(sourceDataFile);
   				propertyFile.load(sourceDataFileInput);
   				log.info("Success : Source data file  " + propertyFile + " loaded");
   				System.out.println("Success : Source data file  " + propertyFile + " loaded");
   				} catch (Exception e) {
	    				//e.printStackTrace();
   					log.error("Fail : Cann't Load the  Source Property file "+fileName +" ...exiting the program");
	    			shutDown();
   				}
   			} 

   		return propertyFile;
	}
	
	/**
	 * Close the driver
	 */
	protected  void shutDown(){
		try{
		//Thread.sleep(20);
		getDriver().quit();
		//getSsWebSriver().quit(); //quit the listener driver
		//System.clearProperty(DRIVER_CLASS);
		log.info("Info : Browser Driver closed successfully ");
		}catch(Exception e) {
			log.error("Fail : Browser Driver Didn't close properly ....exiting the program");
			//e.printStackTrace();
			System.exit(0);
		}
	}
	
	
	
	protected void signInExclusively_prod(String username, String Password) throws InterruptedException 
	{
		// try{
			loadPage(AppConstants.EXCLUSIVELY_HOMEPAGE);
			//waitForPageLoad(30);
			//getDriver().findElement(By.xpath(".//*[@id='js_loginlink']")).click();
			//Thread.sleep(1000);
			//getDriver().findElement(By.xpath(".//*[@id='js_loginlink']")).click();
			getDriver().findElement(By.id("loginExc")).click();
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_email")));
			getDriver().findElement(By.id("login_email")).clear();
			getDriver().findElement(By.id("login_email")).sendKeys(username);
			getDriver().findElement(By.id("login_password")).clear();
			getDriver().findElement(By.id("login_password")).sendKeys(Password);
			driver.findElement(By.xpath(".//*[@id='loginButton']")).click();			
	}	


	/**
	 * Page wait for specific time
	 * @param long wait time 
	 */
	protected  void waitForPageLoad(long waitTime){
		// try{
			getDriver().manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		/*}catch(Exception e) {
			//System.out.println("Fail : waitForPageLoad()  failed");
			log.error("Fail : waitForPageLoad()  failed");
		}*/
	}
	
	
	protected  Wait<WebDriver> waitgetForPageLoad(long waitTime){
		// try{
			wait = new WebDriverWait(driver, waitTime);
		/*}catch(Exception e) {
			//System.out.println("Fail : waitForPageLoad()  failed");
			log.error("Fail : waitgetForPageLoad()  failed");
		}*/
		return wait;
	}
	
	protected  JavascriptExecutor jsHandle(){
		// try{
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		/*}catch(Exception e) {
			//System.out.println("Fail : waitForPageLoad()  failed");
			log.error("Fail : waitgetForPageLoad()  failed");
		}*/
		return jse;
	}
	
	
	
	/**
	 * Handle input Textfield['Text'] on page by element ID
	 * pass the input data 
	 * @param Wedriver localWebDeriver  
	 * @param String elementID
	 * @param String inputData
	 * @throws Exception 
	 */
	
	protected  void handleClearInputField_ByName(String elementID) {
		 // try{
			
				getDriver().findElement(By.name(elementID)).clear();
				
		/*}catch(Exception e) {
			//System.out.println("Fail : Element ID '" + elementID + " Not Found");
			log.error("Fail : Element ID '" + elementID + " Not Found");
			throw(e);
		}*/
	}
	/**
	 * Handle input Textfield['Text'] on page by element ID
	 * pass the input data 
	 * @param Wedriver localWebDeriver  
	 * @param String elementID
	 * @param String inputData
	 */
	
	protected  void handleInputField_ByID(String elementID, String inputData){
		try{
			getDriver().findElement(By.id(elementID)).sendKeys(inputData);
		}catch(Exception e) {
			//System.out.println("Fail : Element ID '" + elementID + " Not Found");
			log.error("Fail : Element ID '" + elementID + " Not Found");
			//e.printStackTrace();
			//System.exit(0);
		}
	}
	
	/**
	 * Handle input Textfield['Text'] on page by element Name
	 * pass the input data 
	 * @param Wedriver localWebDeriver  
	 * @param String elementID
	 * @param String inputData
	 * @throws Exception 
	 */
	
	protected  void handleInputField_ByName(String elementName, String inputData) {
		// try{
			getDriver().findElement(By.name(elementName)).sendKeys(inputData);
		/*}catch(Exception e) {
			//System.out.println("Fail : Element '" + elementName + " Not Found");
			log.error("Fail : Element '" + elementName + " Not Found");
			throw(e);
			//e.printStackTrace();
			//System.exit(0);
		}*/
	}
	
	/**
	 * Handle the click action on button by button ID
	 * @param Wedriver webDeriver  
	 * @param String elementName
	 * @throws Exception 
	 * 
	 */
	
	protected  void handleButtonClick_ByID(String elementId) {
		// try{
			getDriver().findElement(By.id(elementId)).click();
		/*}catch(Exception e) {
			//System.out.println("Fail : Element ID '" + elementId + "' Click action Failed");
			log.error("Fail : Element ID '" + elementId + "' Click action Failed");
			throw(e);
			//e.printStackTrace();
			//System.exit(0);
		}*/
	}
	
	/**
	 * Handle the click action on button by button Name
	 * @param Wedriver webDeriver  
	 * @param String elementName
	 * 
	 */
	
	protected  void handleButtonClick_ByName(String elementName){
		try{
			getDriver().findElement(By.name(elementName)).click();
		}catch(Exception e) {
			//System.out.println("Fail : Element Name'" + elementName + "' Click action Failed");
			log.error("Fail : Element Name'" + elementName + "' Click action Failed");
		}
	}
	

	/**
	 * Handle hyperlink  on page by link text
	 * pass the input data 
	 * @param Wedriver localWebDeriver  
	 * @param String elementID
	 * @param String inputData
	 * @throws Exception 
	 */
	
	protected  void handleLink_ByText(String linkText) {
		// try{
			getDriver().findElement(By.linkText(linkText)).click();
		/*}
		catch(Exception e) {
			//System.out.println("Fail : Link Element '" + linkText + "' Click action Failed");
			log.error("Fail : Link Element '" + linkText + "' Click action Failed");
			throw(e);
		}*/
	}
	
	/**
	 * Get element on page by xpath  
	 * pass the input data 
	 * @param Wedriver localWebDeriver  
	 * @param String elementID
	 * @param String inputData
	 */
	
	protected  WebElement getWebElement_By_xpath(String xpathText){
		WebElement localWebElement = null;
		try{
			localWebElement = getDriver().findElement(By.xpath(xpathText));
		}catch(Exception e) {
			//System.out.println("Fail : Link Element '" + linkText + "' Click action Failed");
			log.error("Fail : Element Not found for xpath '" + xpathText );
		}
		return localWebElement;
	}

	
	/**
	 * get the webdriver
	 * @return
	 */
	protected  WebDriver getDriver() {
		return driver;
	}
	
	
	/**
	 * Get the Property file data
	 * @return Properties
	 */
	
	protected Properties getFileData() {
		return fileData;
	}

	// method to check an element is displayed or not
	
	/*protected boolean isElementPresent(By by) {
		try {
	        driver.findElement(by);
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}
	
	protected boolean checkIfDisplayed(By by) {
	    if (isElementPresent(by)) {
	    if (driver.findElement(by).isDisplayed()) {
	        return true;
	    } else
	        return false;
	    } else
	        return false;
	}*/
	
	protected WebElement tryToGetElementByXPath(String xpath,int maxtry) {
	 Wait<WebDriver> waittable= new WebDriverWait(getDriver(), 30); 
	 WebElement element=null;
	 int trycount=1;
 	boolean breakit;
        	while(trycount!=maxtry){
                breakit=true;
            try{
            element=waittable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))); 
        }
       catch (Exception e) {
              breakit=false;
              trycount++;
    	   
        } 
            if(breakit==true)
            	break;
     }
        	return element;
	}
	
	protected WebElement tryToClickElementByXPath(String xpath,int maxtry) {
		 Wait<WebDriver> waittable= new WebDriverWait(getDriver(), 30); 
		 WebElement element=null;
		 int trycount=1;
	 	boolean breakit;
	        	while(trycount!=maxtry){
	                breakit=true;
	            try{
	            element=waittable.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))); 
	        }
	       catch (Exception e) {
	              breakit=false;
	              trycount++;
	    	   
	        } 
	            if(breakit==true)
	            	break;
	     }
	        	return element;
		}
	
//new changes 

	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static WebDriver getSsWebSriver() {
		return ssWebSriver;
	}
	public static void setSsWebSriver(WebDriver ssWebSriver) {
		CommonUtility.ssWebSriver = ssWebSriver;
	}
	

	public static void main(String[] args) {}
}
