package org.example.Pages;

import org.example.Utilities.LogIt;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;

 public abstract class BasePage {

     private WebDriver driver;
     public WebDriverWait wait;
     public LogIt log = new LogIt();
     public Actions actions;

     /** Constructor*/
     public BasePage(WebDriver driver) {
         this.driver = driver;
         this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
         actions = new Actions(driver);
     }

     /** Methods*/
     public void click(By elementLocation) {
         try {
             waitVisibility(elementLocation);
             moveToElement(elementLocation);
             driver.findElement(elementLocation).click();
             log.info("Clicked " + elementLocation);
         } catch (ElementClickInterceptedException | NoSuchElementException | StaleElementReferenceException e) {
             log.info("Single click attempt failed: " + elementLocation);
         }
     }

     public void doubleClick(By elementLocation) {
         try {
             moveToElement(elementLocation);
             actions.doubleClick(driver.findElement(elementLocation)).perform();
             waitVisibility(elementLocation);
             log.info("Double left clicked " + elementLocation);
         } catch (Exception e) {
             log.error("Double click attempt failed: " + e.getMessage());
         }
     }

     public void clearText(By elementLocation) {
             try {
                 moveToElement(elementLocation);
                 driver.findElement(elementLocation).clear();
                 log.info("Cleared text from " + elementLocation);
             } catch (Exception e) {
                 log.error("Failed to clear text from " + elementLocation);
             }
     }

     public void writeText(By elementLocation, String text) {
         try {
             moveToElement(elementLocation);
             clearText(elementLocation);
             driver.findElement(elementLocation).sendKeys(text);
             log.info("Sent text to " + elementLocation);
         } catch (Exception e) {
             log.error("Failed to clear text from " + elementLocation);
             throw e;
         }
     }

     public String readText(By elementLocation) {
         String text;
         try {
             moveToElement(elementLocation);
             text = driver.findElement(elementLocation).getText();
             log.info("Read text from " + elementLocation);
         } catch (Exception e) {
             throw e;
         }
         return text;
     }

     public int getValue(By elementLocation) {
         int num = 0;
         try {
             moveToElement(elementLocation);
             num = Integer.parseInt(driver.findElement(elementLocation).getAttribute("value"));
             log.info("Read text from " + elementLocation);
         } catch (Exception e) {
             throw e;
         }
         return num;
     }

     public void performKeyPress(Keys key, int count) {
         for (int i = 0; i < count; i++) {
             try {
                 actions.sendKeys(key).perform();
                 log.info("Performed keypress " + key.name());
             } catch (Exception e) {
                 throw e;
             }
         }
     }


     public void performKeyString(String text) {
         try {
             Actions actions = new Actions(driver);
             actions.sendKeys(text).perform();
             log.info("Sent input text to field");
         } catch (Exception e) {
             throw e;
         }
     }

     public void scrollToElement(By elementLocation) {
         try {
             WebElement element = driver.findElement(elementLocation);
             JavascriptExecutor js = (JavascriptExecutor) driver;
             js.executeScript("arguments[0].scrollIntoView();", element);
             log.info("Used 'JS.executeScript' to scroll to element");
         } catch (Exception e) {
             throw e;
         }
     }

     public void scrollToPageTop() {
         try {
             JavascriptExecutor js = (JavascriptExecutor) driver;
             js.executeScript("window.scrollTo(0, 0)");
             log.info("Used 'JS.executeScript' to scroll to top of page");
         } catch (Exception e) {
             throw e;
         }
     }

     public void scrollToPageBottom() {
         try {
             JavascriptExecutor js = (JavascriptExecutor) driver;
             js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
             log.info("Used 'JS.executeScript' to scroll to bottom of page");
         } catch (Exception e) {
             throw e;
         }
     }

     public void waitInvisibility(By element) {
         try {
             wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
         } catch (Exception e) {
             throw e;
         }
     }

     public void waitVisibility(By element) {
         try {
             wait.until(ExpectedConditions.elementToBeClickable(element));
         } catch (Exception e) {
             log.error("Element not clickable " + element);
         }
     }

     public void waitVisibility(By element1, By element2) {
         try {
             wait.until(ExpectedConditions.or(
             ExpectedConditions.elementToBeClickable(element1),
             ExpectedConditions.elementToBeClickable(element2)));
         } catch (Exception e) {
             log.error("Elements not clickable " + element1 +" / " + element2);
         }
     }

     public boolean elementVisible(By element) {
         try {
             return driver.findElement(element).isDisplayed();
         } catch (Exception e) {
             log.error("Element not visible at time of checking " + element);
             return false;
         }
     }

     public boolean waitElementTextMatches(By elementLocation, String text) {
         WebElement element = driver.findElement(elementLocation);
         boolean elementMatched;
         try {
             elementMatched = wait.until(ExpectedConditions.textToBePresentInElement(element, text));
             log.info("Waited until \"" + text + "\" present in element " + element);
         } catch (Exception e) {
             throw e;
         }
         return elementMatched;
     }

     public boolean pageContains(String text) {
         boolean containsText = false;
         try {
             containsText = driver.getPageSource().toLowerCase().contains(text.toLowerCase());
             log.info("Page checked to see if contains text: \"" + text + "\", " + containsText);
         } catch (Exception e) {
             log.error("Page does not currently contain " + text);
         }
         return containsText;
     }

     public void switchToTab(int tabNumber) {
         ArrayList<String> tabs_windows;
         boolean tabs = false;
         int counter = 0;

         do {
             try {
                 tabs_windows = new ArrayList<>(driver.getWindowHandles());
                 if (tabs_windows.size() >= 1) {
                     tabs = true;
                     driver.switchTo().window(tabs_windows.get(tabNumber));
                     log.info("Driver tab switched to index " + tabNumber + " of " + tabs_windows.size() + " tabs available");
                 } else {
                     log.error("Driver was unable to switch to an expected new tab");
                     counter++;
                     try {
                         Thread.sleep(3000);
                         log.info("3 second sleep triggered");
                     } catch (InterruptedException ex) {
                         log.error("Sleep command interrupted: " + ex.getMessage());
                     }
                 }
             } catch (Exception e) {
                 log.error("Unable to switch tab at this time: " + e.getMessage());
             }
         } while (!tabs && counter < 10);
     }

     public void moveToElement(By elementLocation) {
         int refreshCounter = 0;
         boolean found;

         do{
             try {
                 actions.moveToElement(driver.findElement(elementLocation));
                 actions.perform();
                 found = true;
             } catch(Exception e){
                 found = false;
                 refreshCounter++;
                 waitVisibility(elementLocation);
             }
         } while (!found && refreshCounter < 2);
     }

 }