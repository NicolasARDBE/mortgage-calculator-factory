package com.endava.training.mortgage_pages.CarLoan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarLoanPage {


    @FindBy(css = "div.tabsmain > label[for=\"tab2\"]")
    WebElement budgetButton;

    public CarLoanPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void goToBudgetPage() {
        budgetButton.click();
    }
}