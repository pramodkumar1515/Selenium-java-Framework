package com.exclusively.web.common;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.exclusively.web.common.AppConstants;
import com.exclusively.web.common.CommonUtility;



public  class DynamicallyRunTestCases extends CommonUtility implements IAnnotationTransformer   {

	
	//public static ReadExcel re


	//static or dynamic data flag
	private  Properties sourceDataStatusAccounts = new Properties();

	//static data 
	private  Properties sourceStaticDataAccounts = new Properties();

    @SuppressWarnings("rawtypes")

	/**
 	 * Reads the resource file
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
    

	
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor,
            Method testMethod) {
    	
    	//System.out.println("Test Method Name ::" + testMethod.getName());
		loadSetUpConfig();
    	readSourceDataFiles();
    	
    	
       

			try {
				if (getSourceData(testMethod.getName()).trim().toString().equals(testMethod.getName())) {
				    //annotation.setInvocationCount(15);
				    //System.out.println("*********** is found******** ");
				    //String [] group={"Examples"};
				    //annotation.setGroups(group);
				    annotation.setEnabled(true);     
				}
				else
				{
					annotation.setEnabled(false);

					
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		} 
    	


