package com.endava.training.mortgage_pages.CheckLenders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RedirectLenderPage {

    private final WebDriver driver;

    @FindBy(id = "iFrameResizer0")
    WebElement iframe;

    @FindBy(xpath = "(//a[contains(text(),'View Details')])[1]")
    WebElement viewDetailsButton;

    public RedirectLenderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnViewDetails(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        wait.until(ExpectedConditions.elementToBeClickable(viewDetailsButton));
        String originalWindow = driver.getWindowHandle();
        viewDetailsButton.click();
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
