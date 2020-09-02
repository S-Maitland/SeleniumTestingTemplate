package org.example.Tests;
import org.example.Utilities.Screenshot;
import org.example.WebDrivers.DriverManager;
import org.example.WebDrivers.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Listeners(org.example.Listeners.TestNGListener.class)
public class BaseTest {

    private DriverManager driverManager;
    public static WebDriver driver;
    public Properties credentials;
    private Screenshot screenShot;
    private FileInputStream fis;
    private ITestResult result;

    @BeforeClass
    public void setup() throws IOException {
        screenShot = new Screenshot();
        driverManager = new DriverManager();
        driver = driverManager.getWebDriver(DriverType.CHROME);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) throws IOException {
        this.result = result;
        if (result.getStatus() == ITestResult.FAILURE) {
            String classAndMethodName = result.getTestClass().getName() + "_" + result.getName();
            screenShot.getScreenshot(driver, classAndMethodName, result.getStatus());
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() throws IOException {
        String classAndMethodName = result.getTestClass().getName() + "_" + result.getName();
        if(result.getStatus() == ITestResult.SUCCESS){
                screenShot.getScreenshot(driver, classAndMethodName, result.getStatus());
            }
        driverManager.quitWebDriver();
    }
}
