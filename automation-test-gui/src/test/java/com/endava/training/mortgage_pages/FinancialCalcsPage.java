package com.endava.training.mortgage_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FinancialCalcsPage {

    private final WebDriver driver;

    @FindBy(linkText = "Financial Calcs")
    WebElement financialCalcsMenu;

    @FindBy(linkText = "Car")
    WebElement carLink;

    public FinancialCalcsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToCarCalculator() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        new Actions(driver).moveToElement(financialCalcsMenu).perform();
        wait.until(ExpectedConditions.elementToBeClickable(carLink)).click();
    }
}