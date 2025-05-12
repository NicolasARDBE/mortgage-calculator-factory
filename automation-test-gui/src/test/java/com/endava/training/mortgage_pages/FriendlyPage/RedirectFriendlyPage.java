package com.endava.training.mortgage_pages.FriendlyPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RedirectFriendlyPage {

    private final WebDriver driver;

    @FindBy(css = "input[value=\"Printer Friendly Page\"]")
    WebElement friendlyPageButton;

    public RedirectFriendlyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickFriendlyPage() {
        String originalWindow = driver.getWindowHandle();
        friendlyPageButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
