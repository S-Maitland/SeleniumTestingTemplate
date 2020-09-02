package org.example.Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {

    public String getScreenshot(WebDriver driver, String screenshotName, int status) throws IOException {
        File dest;
        String dateName = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        if(status == ITestResult.SUCCESS) {
            dest = new File("reports/SuccessfulTestScreenshots/" + screenshotName + dateName + ".png");
        } else {
            dest = new File("reports/FailedTestsScreenshots/" + screenshotName + dateName + ".png");
        }
        String destination = dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, dest);
        return destination;
    }

}