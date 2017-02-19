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
import com.exclusively.web.common.AppConstants;


public class Exclusively_SanityScenarios_Util extends CommonUtility {
	
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
	protected void SelectCategory(String category) throws InterruptedException 
	{
		// load Home Page
		loadPage(AppConstants.EXCLUSIVELY_HOMEPAGE);
		//Close the Popup
		waitgetForPageLoad(30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root-wrapper']/div/div/div[7]/div[1]")));
		WebElement loginPopupClose =getDriver().findElement(By.xpath(".//*[@id='root-wrapper']/div/div/div[7]/div[1]"));
		loginPopupClose.click();
		///jsHandle().executeScript("window.scrollBy(0,150)", "");
		///Thread.sleep(2000);
		
		//Select the Category
		String categorySelector =("//*[contains(text(),' "+category+" ')]");
		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(categorySelector)));		
		System.out.println(categorySelector);
		getDriver().findElement(By.xpath(categorySelector)).click();
		System.out.println("Category selected done");
		log.info("Category Selected: "+category );
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
	protected void SelectPaymentMethod(String PaymentType) throws InterruptedException 
	{
		String id;
		/*
		switch(PaymentType.toLowerCase()){
		
			case "cod": id="p_method_left_cashondelivery";
						break;
			case "debitcard": id="p_method_left_debitcard_shared";
						break;
			case "netbanking": id="p_method_left_netbanking_shared";
						break;
			case "payuwallet": id ="p_method_left_wallet_shared";
						break;
			case "partpayment": id ="p_method_left_partpayment_shared";
						break;
			default: id = "p_method_left_cashondelivery";
                		break;				
		}
		*/
		
		if (PaymentType.toLowerCase()=="cod")
		{
			id="p_method_left_cashondelivery";
			
		}
		else
		{
			id="p_method_left_debitcard_shared";
		}
		
		//Select Payment Option
		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout-payment-method-load")));
		Actions actions = new Actions(getDriver());	
		WebElement rd= getDriver().findElement(By.id("checkout-payment-method-load"));
		WebElement rdselect= rd.findElement(By.id(id));
		actions.moveToElement(rdselect).click().build().perform();				
	}
	
	
	 /* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            
	protected void getCODPaymentMethodText() throws InterruptedException 
	{
		//get the Payment method type text
		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='payment_form_cashondelivery']/li/div/h2")));
		WebElement paymentMethodText = getDriver().findElement(By.xpath(".//*[@id='payment_form_cashondelivery']/li/div/h2"));
		Thread.sleep(2000);
		paymentType = paymentMethodText.getText();
		
	}
	
	 /* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            
	protected void placepayuWalletOrder() throws InterruptedException 
	{
		Thread.sleep(6000);
		//get the Payment method type text
		waitgetForPageLoad(60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='payment12-buttons-container']/button")));

		WebElement paymentContinueButton = getDriver().findElement(By.xpath(".//*[@id='payment12-buttons-container']/button"));
		Thread.sleep(6000);
		paymentContinueButton.click();
		Thread.sleep(2000);
		waitgetForPageLoad(60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='review-buttons-container']/button")));
		WebElement placeOrder = getDriver().findElement(By.xpath(".//*[@id='review-buttons-container']/button"));
		placeOrder.click();
		waitgetForPageLoad(60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@class='arrw']")));
		WebElement payuBackBtn = getDriver().findElement(By.xpath(".//*[@class='arrw']"));
		WebElement payutextElement =getDriver().findElement(By.xpath(".//*[@class='btn_txt ng-binding']"));
		System.out.println(payutextElement.getText());
		payutext = payutextElement.getText();
		payuBackBtn.click();	
		
		
	}
	
	
	
	 /* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            
	protected void placePartPayment() throws InterruptedException 
	{
		Thread.sleep(6000);
		//get the Payment method type text
		waitgetForPageLoad(60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='payment14-buttons-container']/button")));

		WebElement paymentContinueButton = getDriver().findElement(By.xpath(".//*[@id='payment14-buttons-container']/button"));
		Thread.sleep(6000);
		paymentContinueButton.click();
		Thread.sleep(2000);
		waitgetForPageLoad(60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='review-buttons-container']/button")));
		WebElement placeOrder = getDriver().findElement(By.xpath(".//*[@id='review-buttons-container']/button"));
		placeOrder.click();
		waitgetForPageLoad(60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='credit']/div[2]/span/a")));
		WebElement payuBackBtn = getDriver().findElement(By.xpath(".//*[@id='credit']/div[2]/span/a"));
		WebElement payutextElement =getDriver().findElement(By.xpath(".//*[@id='credit']/div[2]/span/a"));
		System.out.println(payutextElement.getText());
		payutext = payutextElement.getText();
		payuBackBtn.click();	
		
		
	}
	


	/* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            
	protected void registerdUserCheckOutFlow() throws InterruptedException 
	{
		//CheckOut Flow
		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='button btn-proceed-checkout btn-inline btn-checkout']")));
		List <WebElement> placeOrderButtonList = getDriver().findElements(By.xpath(".//*[@class='button btn-proceed-checkout btn-inline btn-checkout']"));
		waitgetForPageLoad(60).until(ExpectedConditions.visibilityOf(placeOrderButtonList.get(0)));
		placeOrderButtonList.get(0).click();
		System.out.println("CheckOut Button  click done");
		
		//Register User Login
		RegisterUserLoginOnCheckOutPage();
		
		// select radio to use existing address
		waitgetForPageLoad(60).until(ExpectedConditions.visibilityOfElementLocated(By.id("btnAddressSave")));
		WebElement saveAddressButton= getDriver().findElement(By.id("btnAddressSave"));
		saveAddressButton.click();
	
	}
	

	 /* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            
	protected void SelectProduct(String number) throws InterruptedException 
	{			
			//Select the Product
			int productIndex =(Integer.parseInt(number)-1);
			List <WebElement> productsName = getDriver().findElements(By.className("product-name"));
			log.info("Product Name: "+productsName.get(productIndex).getText());
			List <WebElement> products = getDriver().findElements(By.className("product-image"));
			products.get(productIndex).click();
			System.out.println("product selected done");
			
			//Select size of Product
			waitgetForPageLoad(60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@class='colorswatch-icon-item ']")));
			WebElement sizeSelector= getDriver().findElement(By.xpath(".//*[@class='colorswatch-icon-item ']"));
			log.info("Size of Product: "+getDriver().findElement(By.xpath(".//*[@class='colorswatch-icon-item ']")).getText());
			log.info("SKU of Product: "+getDriver().findElement(By.xpath(".//*[@class='btq-name-container'][1]")).getText());
			log.info("Price of Product: "+getDriver().findElement(By.xpath(".//*[@class='price']")).getText());
			sizeSelector.click();
			System.out.println("size selection done");			
	}	
	
	 /* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            
	protected boolean checkPopUp() throws InterruptedException
	{  
		try{
			waitgetForPageLoad(30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root-wrapper']/div/div/div[7]/div[1]")));
			WebElement loginPopupClose =getDriver().findElement(By.xpath(".//*[@id='root-wrapper']/div/div/div[7]/div[1]"));
			return true;
			}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	 /* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            
	protected void SelectProductbyurl(String url) throws InterruptedException 
	{
		Thread.sleep(3000);
		getDriver().navigate().to(url);
		//Close the Popup
        if(checkPopUp()){
    		WebElement loginPopupClose =getDriver().findElement(By.xpath(".//*[@id='root-wrapper']/div/div/div[7]/div[1]"));
    		loginPopupClose.click();
        }
		//Select size of Product
		waitgetForPageLoad(60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@class='colorswatch-icon-item ']")));
		WebElement sizeSelector= getDriver().findElement(By.xpath(".//*[@class='colorswatch-icon-item ']"));
		log.info("Size of Product: "+getDriver().findElement(By.xpath(".//*[@class='colorswatch-icon-item ']")).getText());
		log.info("SKU of Product: "+getDriver().findElement(By.xpath(".//*[@class='btq-name-container'][1]")).getText());
		log.info("Price of Product: "+getDriver().findElement(By.xpath(".//*[@class='price']")).getText());
		sizeSelector.click();
		System.out.println("size selection done");
	}
	
	 /* Description: Select the product
	 * Parameters: Category , index of the product
	 * Return: None
	 */            
	protected void clearCart() throws InterruptedException 
	{	getDriver().navigate().to("http://www.exclusively.com/checkout/cart/index/");
		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='col-delete a-center last']/a")));
	    List <WebElement> noOfItems = getDriver().findElements(By.xpath(".//*[@class='col-delete a-center last']/a"));
		for (WebElement item : noOfItems) {
			try{
				
				item.click();
			}
			catch(Exception  e)
			{
				continue;
			}
			
			waitgetForPageLoad(30).until(ExpectedConditions.alertIsPresent());
	            getDriver().switchTo().alert().accept();;
			Thread.sleep(4000);	
		}
	}

	 /* Description: Fill shipping details for registered User
	 * Parameters: 
	 * Return: None
	 */            
	protected void selectRegisterCheckOutOption(String userType) throws InterruptedException 
	{
		//Click on Place Order Button
		
		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='button btn-proceed-checkout btn-inline btn-checkout']")));
		WebElement placeOrderButton = getDriver().findElement(By.xpath(".//*[@class='button btn-proceed-checkout btn-inline btn-checkout']"));
		waitgetForPageLoad(60).until(ExpectedConditions.visibilityOf(placeOrderButton));
		placeOrderButton.click();
		System.out.println("CheckOut Button  click done");
		
		if(userType.toLowerCase()=="register")
		{
		//select Radio Button 'Register
		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='login:register']")));
		WebElement registerRadioBtn = getDriver().findElement(By.xpath(".//*[@id='login:register']"));
		registerRadioBtn.click();
		}
		else
		{
			//select Radio Button 'Register
			waitgetForPageLoad(60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='login:guest']")));
			WebElement registerRadioBtn = getDriver().findElement(By.xpath(".//*[@id='login:guest']"));
			registerRadioBtn.click();
		}
		
		//Click Continue Button
		getDriver().findElement(By.xpath(".//*[@class='button']")).click();
		
	}

	 /* Description: Fill shipping details for registered User
	 * Parameters: 
	 * Return: None
	 */            
	protected void fillShippingDetailsForRegisteredUser(String userType) throws InterruptedException 
	{
		//Shipping  first name
		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='shipping:firstname']")));
		WebElement shippingFirstName = getDriver().findElement(By.xpath(".//*[@id='shipping:firstname']"));		
		shippingFirstName.clear();
		shippingFirstName.sendKeys(getSourceData(AppConstants.SHIPPING_FIRST_NAME));
		//Shipping Last name
		WebElement shippingLastName = getDriver().findElement(By.xpath(".//*[@id='shipping:lastname']"));
		shippingLastName.clear();
		shippingLastName.sendKeys(getSourceData(AppConstants.SHIPPING_LAST_NAME));

		//Shipping Telephone
		WebElement shippingTelephone = getDriver().findElement(By.xpath(".//*[@id='shipping:telephone']"));
		shippingTelephone.clear();
		shippingTelephone.sendKeys(getSourceData(AppConstants.SHIPPING_TELEPHONE));
		if(userType=="register")
		{
		//Shipping Customer Password
		WebElement shippingCustomerPassword = getDriver().findElement(By.xpath(".//*[@id='shipping:customer_password']"));
		shippingCustomerPassword.clear();
		shippingCustomerPassword.sendKeys(getSourceData(AppConstants.SHIPPING_CUSTOMER_PASSWORD));
		//Shipping Confirm Password
		WebElement shippingConfirmPassword = getDriver().findElement(By.xpath(".//*[@id='shipping:confirm_password']"));
		shippingConfirmPassword.clear();
		shippingConfirmPassword.sendKeys(getSourceData(AppConstants.SHIPPING_CONFIRM_PASSWORD));
		
		//generating random email id 
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar.getInstance().getTime());
   		System.out.println("email id : "+"qatest"+timeStamp+"@gmail.com");
   		log.info("email id : "+"P"+timeStamp+"@gmail.com");
   		
		//Shipping email
		WebElement shippingEmail = getDriver().findElement(By.xpath(".//*[@id='shipping:email']"));
		shippingEmail.clear();
		shippingEmail.sendKeys("qatest"+timeStamp+"@gmail.com");
		}
		
		else
		{
			//Shipping email
			WebElement shippingEmail = getDriver().findElement(By.xpath(".//*[@id='shipping:email']"));
			shippingEmail.clear();
			shippingEmail.sendKeys(getSourceData(AppConstants.SHIPPING_EMAIL));
			
		}
		
		//Shipping AddressLine 1
		WebElement shippingAddressLine1 = getDriver().findElement(By.xpath(".//*[@id='shipping:street1']"));
		shippingAddressLine1.clear();
		shippingAddressLine1.sendKeys(getSourceData(AppConstants.SHIPPING_STREET_ADDRESS1));
		//Shipping AddressLine 2
		WebElement shippingAddressLine2 = getDriver().findElement(By.xpath(".//*[@id='shipping:street2']"));
		shippingAddressLine2.clear();
		shippingAddressLine2.sendKeys(getSourceData(AppConstants.SHIPPING_STREET_ADDRESS2));
		//Shipping state
		WebElement shippingRegionId = getDriver().findElement(By.xpath(".//*[@id='shipping:region_id']"));
		Select dropdown = new Select(shippingRegionId);
		dropdown.selectByVisibleText(getSourceData(AppConstants.SHIPPING_REGION_ID));
		//Shipping city
		WebElement shippingCity = getDriver().findElement(By.xpath(".//*[@id='shipping:city']"));
		shippingCity.clear();
		shippingCity.sendKeys(getSourceData(AppConstants.SHIPPING_CITY));
		//Shipping Postcode
		WebElement shippingPostCode = getDriver().findElement(By.xpath(".//*[@id='shipping:postcode']"));
		shippingPostCode.clear();
		shippingPostCode.sendKeys(getSourceData(AppConstants.SHIPPING_POSTCODE));
		//Clicking on the Continue Button
		WebElement shippingContinueButton = getDriver().findElement(By.xpath(".//*[@id='btnAddressSave']"));
		shippingContinueButton.click();
	}
	
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
	