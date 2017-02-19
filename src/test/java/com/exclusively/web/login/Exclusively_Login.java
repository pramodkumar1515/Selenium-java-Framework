package com.exclusively.web.login;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.exclusively.web.common.AppConstants;
import com.exclusively.web.login.util.ExclusivelyLogin_RegisteredUser_Util;


public class Exclusively_Login extends ExclusivelyLogin_RegisteredUser_Util {

	@BeforeClass
	public void initDriver() throws Exception {
		//setup the driver
		setUp();
	}

	@Test(priority=1,enabled=false)
	//Verify Registered User Login
	public void RegisteredUserLogin() throws InterruptedException{
		fetchUserName();

		//LogOut();
	    Assert.assertEquals(getUserNameText(), getSourceData(AppConstants.USERNAME_TEXT));
	    	
	}
	@Test(priority=2,enabled=false)       
	//Verify User Registration
	public void UserRegistration() throws InterruptedException{
		userregistration();
		fetchUserName();

		LogOut();
		Assert.assertEquals(getUserNameText(), getSourceData(AppConstants.NEW_USERNAME_TEXT));
	}
	
	@Test(priority=2,enabled=true)       
	//Verify User Registration
	public void dbtestcase() throws InterruptedException{	
	Assert.assertEquals("3899", getresult("select *  from exclusively_po where  po_no='VTIPL/AUTO/V200005160/TTRWAWNSPTLK1CW05L' and vendor='Tarun Tahiliani' order by created_at desc limit 10;"));
	}
	
	
	@AfterClass
	public void terminateBrowser(){
		shutDown();
	}

}

