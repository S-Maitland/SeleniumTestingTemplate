package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Tiobe extends BasePage {
    /**WebElements*/
    private final By tiobeLogo = By.xpath("//*[@id='logo']");
    private final By tiobeTopResult = By.xpath("//*[@id='top20']/tbody/tr[1]/td[4]");

    /**Constructor*/
    public Tiobe(WebDriver driver) {
        super(driver);
    }

    /**Methods*/
    public boolean confirmPageLand(){
        waitVisibility(tiobeLogo);
        return elementVisible(tiobeLogo);
    }

    public boolean checkTopLanguge() {
        String topResult = readText(tiobeTopResult);
        return !topResult.toLowerCase().equals("java");
    }
}
