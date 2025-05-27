package com.endava.training.mortgage_pages;

import com.endava.training.Singleton.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DefaultPage {

    WebDriver driver = DriverSingleton.getDriver();

    public DefaultPage() {
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://www.mortgagecalculator.org/");
    }
}
