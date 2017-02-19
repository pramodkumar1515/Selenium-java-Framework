package com.exclusively.web.common;


import java.io.UnsupportedEncodingException;

import org.testng.IExecutionListener;

public class ExecutionListener extends emailReport implements IExecutionListener  {
	private long startTime;

	//@Override
	public void onExecutionStart() {
		startTime = System.currentTimeMillis();
		System.out.println("TestNG is going to start"+ startTime);		
	}

	//@Override
	public void onExecutionFinish() {
		System.out.println("TestNG has finished, took around " + (System.currentTimeMillis() - startTime) + "ms");
		try {
			sendEmail();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}