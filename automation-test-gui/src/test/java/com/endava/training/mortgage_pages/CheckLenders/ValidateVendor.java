package com.endava.training.mortgage_pages.CheckLenders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ValidateVendor {

    private final WebDriver driver;

    public ValidateVendor(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCurrentUrl() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains
                ("https://cash-out-refinance-icbv3.greenlending.com"));
        return driver.getCurrentUrl();
    }
}