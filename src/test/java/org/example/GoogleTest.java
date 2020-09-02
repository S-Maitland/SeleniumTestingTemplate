package org.example;

import org.example.Pages.Google;
import org.example.Pages.Tiobe;
import org.example.Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {
    /**Page Declariations*/
    private Google google;
    private Tiobe tiobe;

    /**Constructor*/
    public GoogleTest() {
    }

    /**Tests*/
    @Test(priority = 0)
    public void OpenGoogleAndLocateSearchBox(){
        //Arrange
        google = new Google(driver);
        //Act
        driver.get("https://www.google.com");
        //Assert
        Assert.assertTrue(google.confirmPageLand());
    }

    @Test(priority = 1, dependsOnMethods = {"OpenGoogleAndLocateSearchBox"})
    public void SearchGoogleForTiobeIndex(){
        //Arrange
        //Act
        google.performSearch();
        //Assert
        Assert.assertTrue(google.pageContains("index | TIOBE - The Software Quality Company"));
    }

    @Test(priority = 2, dependsOnMethods = {"SearchGoogleForTiobeIndex"})
    public void JavaIsNotTopResultOnTiobe(){
        //Arrange
        tiobe = new Tiobe(driver);
        //Act
        google.goToTiobe();
        //Assert
        Assert.assertTrue(tiobe.confirmPageLand());
        Assert.assertTrue(tiobe.checkTopLanguge());
    }
}
