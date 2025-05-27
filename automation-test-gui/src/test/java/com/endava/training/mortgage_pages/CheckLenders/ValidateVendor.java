package com.endava.training.mortgage_pages.CheckLenders;

import com.endava.training.Singleton.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ValidateVendor {

    WebDriver driver = DriverSingleton.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public ValidateVendor() {
        PageFactory.initElements(driver, this);
    }

    public String getCurrentUrl() {

        wait.until(ExpectedConditions.urlContains
                ("https://cash-out-refinance-icbv3.greenlending.com"));
        return driver.getCurrentUrl();
    }
}