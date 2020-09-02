package org.example.WebDrivers;

import org.example.Utilities.LogIt;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {
    private WebDriver driver;
    private LogIt log = new LogIt();

    public WebDriver getWebDriver(DriverType driverType) {
        switch (driverType) {
            case FIREFOX:
                if (driver == null) {
                    System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
                    FirefoxOptions options = new FirefoxOptions();
//                    options.add_Argument("--headless");
                    this.driver = new FirefoxDriver(options);
                }

            case EDGE:
                if (driver == null) {
                    System.setProperty("webdriver.edge.driver", "src\\main\\resources\\msedgedriver.exe");
                    EdgeOptions options = new EdgeOptions();
                    this.driver = new EdgeDriver(options);
                }

            default:
                if (driver == null) {
                    System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver_83.exe");
                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("--headless");
                    options.addArguments("start-maximized");
                    options.addArguments("version");
                    this.driver = new ChromeDriver(options);
                }
        }
        return driver;
    }

    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            log.info("Quit WebDriver Instance");
        } else {
            log.info("Driver is set to null");
        }
    }

}
