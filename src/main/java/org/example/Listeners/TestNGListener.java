package org.example.Listeners;

import org.example.Tests.BaseTest;
import org.example.Utilities.LogIt;
import org.example.Utilities.Time;
import org.jetbrains.annotations.NotNull;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    private LogIt log = new LogIt();

    @Override
    public void onStart(@NotNull ITestContext context) {
        log.testNG("TESTING STARTED: " + context.getName());
    }

    @Override
    public void onFinish(@NotNull ITestContext context) {
        log.testNG("TESTING FINISHED: " + context.getName());
    }

    @Override
    public void onTestStart(@NotNull ITestResult result) {
        log.testNG("Test Case Started: " + "(" + result.getTestClass().getName() +") - " + result.getName());
    }

    @Override
    public void onTestSuccess(@NotNull ITestResult result) {
        log.success("Test Case Passed: " + "(" + result.getTestClass().getName() +") - " + result.getName());
    }

    @Override
    public void onTestFailure(@NotNull ITestResult result) {
        log.fail("Test Case Failed: " + "(" + result.getTestClass().getName() +") - " + result.getName() +" - " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(@NotNull ITestResult result) {
        log.alert("Test Case Skipped: " + "(" + result.getTestClass().getName() +") - " + result.getName() +" - " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(@NotNull ITestResult result) {
        log.fail("Test Case Failed Within Success Percentage: " + result.getName() + result.getThrowable());
    }

    @Override
    public void onTestFailedWithTimeout(@NotNull ITestResult result) {
        System.out.println("Test Case Failed With Timeout: " + result.getName() + result.getThrowable());
    }
}
