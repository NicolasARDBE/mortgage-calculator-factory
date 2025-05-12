package com.endava.training.mortgage_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainCalculatorResultPage {

    private final WebDriver driver;

    @FindBy(xpath = "//div[contains(., \"Down payment amount\")]/h3")
    WebElement downPaymentAmount;
    @FindBy(xpath = "//div[contains(., \"Down payment %\")]/h3")
    WebElement downPaymentPercentage;

    public MainCalculatorResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getDownPaymentAmount() {
        return downPaymentAmount.getText();
    }

    public String getDownPaymentPercentage() {
        return downPaymentPercentage.getText();
    }
}
