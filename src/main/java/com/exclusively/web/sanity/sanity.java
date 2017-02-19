package com.exclusively.web.sanity;

import java.io.UnsupportedEncodingException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.exclusively.web.common.AppConstants;
import com.exclusively.web.common.emailReport;
import com.exclusively.web.login.util.ExclusivelyLogin_RegisteredUser_Util;


public class sanity extends Exclusively_SanityScenarios_Util {

	@BeforeClass
	public void initDriver() throws Exception {
		//setup the driver
		setUp();
	}

	@Test(priority=1,enabled=false)
	//Verify Registered User Login
	public void placeOrderRegisterUserCheckOutFlow() throws InterruptedException{
		SelectCategory(getSourceData(AppConstants.SANITY_CATEGORY));
		SelectProduct(getSourceData(AppConstants.PRODUCT_SELECTION_NO));
		addToCart();
		registerdUserCheckOutFlow();
		SelectPaymentMethod(getSourceData(AppConstants.PAYMENT_METHOD));
		getCODPaymentMethodText();
		placepayuWalletOrder();
	    Assert.assertEquals(getPaymentType(), getSourceData(AppConstants.PAYMENT_TYPE_TEXT));
	    	
	}
	
	@Test(priority=1,enabled=true)
	//Verify Registered User Login
	public void placeOrderRegisterUserCheckOutFlowpayuWallet() throws InterruptedException{
		SelectCategory(getSourceData(AppConstants.SANITY_CATEGORY));
		SelectProduct(getSourceData(AppConstants.PRODUCT_SELECTION_NO));
		addToCart();
		registerdUserCheckOutFlow();
		SelectPaymentMethod(getSourceData(AppConstants.PAYMENT_METHOD));
		//getCODPaymentMethodText();
		placepayuWalletOrder();
	    Assert.assertEquals(getPayutext(), "Back to www.exclusively.com");
	    	
	}
	@Test(priority=2,enabled=false)       
	//Verify User Registration
	public void UserRegistration() throws InterruptedException{
		
		//Assert.assertEquals(getUserNameText(), getSourceData(AppConstants.NEW_USERNAME_TEXT));
	}
	
	@AfterClass
	public void terminateBrowser(){
		//shutDown();
	}
	


}

