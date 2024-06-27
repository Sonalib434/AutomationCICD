package selenium.reusableabstractcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import selenium.pageobjectmodel.TestComponents.BaseClass;
import selenium.resource.ExtentReporterNG;

public class Listeners extends BaseClass implements ITestListener {

	ExtentReporterNG extentReport = new ExtentReporterNG();
	ExtentReports extent = extentReport.getReportObject();
	ExtentTest test;
	
	ThreadLocal<ExtentTest> tl = new ThreadLocal<ExtentTest>(); // Thread Safe
	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		tl.set(test); //unique thread id for each test
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		//test.log(Status.PASS, "Test Passed");
		tl.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		//test.log(Status.FAIL, "Test Failed");
		//test.fail(result.getThrowable()); //printing error msg
		
		tl.get().log(Status.FAIL, "Test Failed");
		tl.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//ScreenShot of failure and attach it to report
		try 
		{
			String filePath = getScreenShot(result.getMethod().getMethodName(),driver);
			//test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
			tl.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
