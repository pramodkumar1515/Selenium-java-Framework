package com.exclusively.web.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.exclusively.web.common.AppConstants;


public class GroupRunnerListner extends CommonUtility  implements IMethodInterceptor {
	
	//static or dynamic data flag
	private  Properties sourceDataStatusAccounts = new Properties();

	//static data 
	private  Properties sourceStaticDataAccounts = new Properties();
	

	/**
 	 * Reads the Bills n Reload page source data
 	 * to get the dynamic/static data
 	 */
     private void readSourceDataFiles(){
    	String statusFileName = CommonUtility.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
    											   .concat(AppConstants.EXECUTE_STATUS_TESTCASE);
    	sourceDataStatusAccounts = loadFileData(statusFileName);
    	
    	String dataFileName = CommonUtility.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
												 .concat(AppConstants.EXECUTE_TESTCASE);
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
    		 //log.info("Success : Static Source data '"+ inputData + "' -->"+returnData );
    		// System.out.println("Success : Static Source data '"+ inputData + "' -->"+returnData );
    	 }else{
    		 returnData= "Fail :" + inputData + " Not found in Status File" ;
    		 //log.error("Fail :" + inputData + " Not found in Status File");
    	 }
    	 
    	 return returnData;
     }
     /**
      * read the static source data 
      * @param inputData
      * @return
      */
     private String findSourceData(String inputData){
    	 String returnData = "No Function found";
    	 String status = sourceDataStatusAccounts.getProperty(inputData);
    	 if(status.equalsIgnoreCase(AppConstants.YES)){
    		 returnData = sourceStaticDataAccounts.getProperty(inputData);
    	 }
    	 return returnData;
     }

	//@Override
	public List intercept(java.util.List<IMethodInstance> methods,	ITestContext context) {
		
		List result = new ArrayList();
		
		for (IMethodInstance m : methods) {
			Test test = m.getMethod().getMethod().getAnnotation(Test.class);
			Set groups = new HashSet();
			for (String group : test.groups()) {
				groups.add(group);
			}
			
			if (groups.contains("Sanity")) {
				result.add(m);
			} else {
				String testMethod = m.getMethod().getMethod().getName();
				System.out.println(testMethod
						+ " not a sanity test Case");
			}
		}
		return result;
	}
}