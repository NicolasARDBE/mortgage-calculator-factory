package com.endava.training.mortgage_pages.CheckLenders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LendersParametersPage {
    private final WebDriver driver;
    @FindBy(css = "div#rtable > iframe")
    WebElement iframe;
    @FindBy(id = "rate-table-zip")
    WebElement zipCodeField;
    @FindBy(id = "rate-table-credit-score")
    WebElement creditScoreSelector;
    @FindBy(xpath = "//div[@class = \"icb-rate-table__filter-field icb-rate-table__filter-field-search\"]/button")
    WebElement updateLendersButton;
    @FindBy(xpath = "(//a[contains(text(),'View Details')])[1]")
    WebElement viewDetailsButton;

    public LendersParametersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterZipCode(String zipCode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        wait.until(ExpectedConditions.elementToBeClickable(zipCodeField));
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
    }

    public void selectCreditScore(String value){
        Select select = new Select(creditScoreSelector);
        select.selectByValue(value);
    }

    public void clickOnUpdateLenders(){
        updateLendersButton.click();
    }
    public void clickOnViewDetails(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
