

package com.exclusively.web.sanity;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.exclusively.web.common.AppConstants;



public class Gofynd extends Gofynd_Sanity_Scenarios {

	@BeforeClass
	public void initDriver() throws Exception {
		//setup the driver
		setUp();
	}

	@Test(priority=1,enabled=true)
	//Verify Registered User Login
	public void placeOrderRegisterUserCheckOutFlow() throws InterruptedException{
		SelectCategory();
	    	
	}
	

	
	@AfterClass
	public void terminateBrowser(){
		shutDown();
	}

}

