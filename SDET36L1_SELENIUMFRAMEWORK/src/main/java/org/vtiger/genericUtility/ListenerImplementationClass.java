package org.vtiger.genericUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public  class ListenerImplementationClass  implements ITestListener {

	private ExtentReports report;
	private ExtentTest test ;
	public static  ExtentTest testlog ;

	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter spark=new ExtentSparkReporter("./extentreport-output/emailable-extentreport.html");
		spark.config().setDocumentTitle("Document title");
		spark.config().setReportName("Report name");
		spark.config().setTheme(Theme.DARK);

		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os", "windows");
		report.setSystemInfo("browser name", "chrome");
		report.setSystemInfo("browser version", "103.0.5060.134");

	}


	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
		test.assignAuthor("Hari");
		test.assignCategory("smoke");
		testlog = test;

	}
   
	@Override
	public void onTestSuccess(ITestResult result) {
    test.pass(result.getMethod().getMethodName()+"is passed");
    String pathofScrrenshot = UtilityObjectsClass.getWebdriverUtility().takesScreenShotPage(UtilityObjectsClass.getDriver());
    test.addScreenCaptureFromBase64String(pathofScrrenshot, result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getMethod().getMethodName()+"is failed");
		test.fail(result.getThrowable());
		//  UtilityObjectsClass.getWebdriverUtility().TakesScreenShot(result.getMethod().getMethodName(), ));
		String pathofScrrenshot = UtilityObjectsClass.getWebdriverUtility().takesScreenShotPage(UtilityObjectsClass.getDriver());
		test.addScreenCaptureFromBase64String(pathofScrrenshot, result.getMethod().getMethodName());
		//test.addScreenCaptureFromPath(pathofScrrenshot);
	//	UtilityObjectsClass.getWebdriverUtility().TakesScreenShotElement(pathofScrrenshot, null, null)
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName());		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}



	@Override
	public void onFinish(ITestContext context) {
     report.flush();
	}




}
