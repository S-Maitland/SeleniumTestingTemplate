package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Google extends BasePage{
    /**WebElements*/
    private final By searchField = By.xpath("//input[@name='q']");
    private final By TiobeHeader = By.xpath("//*[contains(text(),'index | TIOBE - The Software Quality Company')]");

    /**Constructor*/
    public Google(WebDriver driver) {
        super(driver);
    }

    /**Methods*/
    public boolean confirmPageLand(){
        waitVisibility(searchField);
        return elementVisible(searchField);
    }

    public void performSearch() {
        writeText(searchField, "Tiobe Index");
        performKeyPress(Keys.ENTER, 1);
    }

    public void goToTiobe() {
        click(TiobeHeader);
    }
}
