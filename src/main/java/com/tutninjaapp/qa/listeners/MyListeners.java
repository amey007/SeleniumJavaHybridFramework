package com.tutninjaapp.qa.listeners;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutninjaapp.qa.utils.ExtentReporter;
import com.tutninjaapp.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of the Project Tests started...");
		extentReport = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();	
		extentTest = extentReport.createTest(testName);
		// Report logs
		extentTest.log(Status.INFO, testName + " started executing.");
		
		// Console logs
		System.out.println("----- TC " + testName + " started executing.-----");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Report logs
		extentTest.log(Status.PASS, testName + " got successfully executed.");
		
		// Console logs
		System.out.println("----- TC " + testName + " got successfully executed. -----");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// Code to get the driver from the TC calling the listners
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		// Code to take the screenshot and copy it in Screenshots folder
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, testName);
		
		// Report logs
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL,  testName + " got failed !!");
		
		// Console logs
		System.out.println(result.getThrowable());
		System.out.println("TC " + testName + " got failed !!");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Report logs
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " got skipped !!");
		
		// Console logs
		System.out.println(result.getThrowable());	
		System.out.println("TC " + testName + " got skipped !!");	
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		File extentReportFile = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("Finished executing all Project Tests.");	
	}
	
	

}
