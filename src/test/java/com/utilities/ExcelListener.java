package com.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExcelListener implements ITestListener {
	    @Override
	    public void onTestSuccess(ITestResult result) {
	        ExcelReport.logResult(
	            result.getName(),
	            "PASS",
	            null,
	            result.getEndMillis() - result.getStartMillis(),
	            null
	        );
	    }
	    @Override
	    public void onTestFailure(ITestResult result) {
	        String screenshotPath = BaseTest.captureScreenshot(result.getName());
	        ExcelReport.logResult(
	            result.getName(),
	            "FAIL",
	            result.getThrowable().getMessage(),
	            result.getEndMillis() - result.getStartMillis(),
	            screenshotPath
	        );
	    }
	    @Override
	    public void onTestSkipped(ITestResult result) {
	        ExcelReport.logResult(
	            result.getName(),
	            "SKIPPED",
	            result.getThrowable() != null ? result.getThrowable().getMessage() : "Skipped",
	            result.getEndMillis() - result.getStartMillis(),
	            null
	        );
	    }
	    @Override
	    public void onFinish(ITestContext context) {
	        ExcelReport.saveReport();
	        System.out.println("Excel Report Generated: ExcelReport.xlsx");
	    }
	}


