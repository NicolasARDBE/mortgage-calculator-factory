package com.endava.training.mortgage_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarLoanPage {

    private final WebDriver driver;

    @FindBy(css = "div.tabsmain > label[for=\"tab2\"]")
    WebElement budgetButton;

    public CarLoanPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToBudgetPage() {
        budgetButton.click();
    }
}