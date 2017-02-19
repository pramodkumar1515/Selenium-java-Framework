package com.exclusively.web.sanity;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.exclusively.web.common.AppConstants;
import com.exclusively.web.login.util.ExclusivelyLogin_RegisteredUser_Util;


public class Exclusively_Sanity_Scenarios extends Exclusively_SanityScenarios_Util {

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
	
	@Test(priority=1,enabled=false)
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

	@Test(priority=1,enabled=true)
	//Verify Registered User Login
	public void placeOrderRegisterUserbyurl() throws InterruptedException{
		SelectProductbyurl(getSourceData(AppConstants.PRODUCT_URL));
		addToCart();
		registerdUserCheckOutFlow();
		SelectPaymentMethod(getSourceData(AppConstants.PAYMENT_METHOD));
		//getCODPaymentMethodText();
		placePartPayment();
	    Assert.assertEquals(getPayutext(), "www.exclusively.com");
		clearCart();
		LogOut();
	}
	
	@Test(priority=2,enabled=true)       
	//Verify guest user checkOut Flow
	public void guestUserCheckOutflow() throws InterruptedException{
		System.out.println(getSourceData(AppConstants.PRODUCT_URL));
		SelectProductbyurl(getSourceData(AppConstants.PRODUCT_URL));
		addToCart();
		selectRegisterCheckOutOption("guest");
		fillShippingDetailsForRegisteredUser("guest");
		SelectPaymentMethod(getSourceData(AppConstants.PAYMENT_METHOD));
		placePartPayment();
	    Assert.assertEquals(getPayutext(), "www.exclusively.com");
		clearCart();
	}
	
	@Test(priority=2,enabled=false)       
	//Verify Register user CheckOut Flow
	public void registerUserCheckOutflow() throws InterruptedException{
		System.out.println(getSourceData(AppConstants.PRODUCT_URL));
		SelectProductbyurl(getSourceData(AppConstants.PRODUCT_URL));
		addToCart();
		selectRegisterCheckOutOption("register");
		fillShippingDetailsForRegisteredUser("register");
		SelectPaymentMethod(getSourceData(AppConstants.PAYMENT_METHOD));
		placePartPayment();
	    Assert.assertEquals(getPayutext(), "www.exclusively.com");
		clearCart();
	}
	
	@AfterClass
	public void terminateBrowser(){
		//shutDown();
	}

}

