package com.endava.training.mortgage_pages.CarLoan;

import com.endava.training.Singleton.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarLoanPage {

    WebDriver driver = DriverSingleton.getDriver();


    @FindBy(css = "div.tabsmain > label[for=\"tab2\"]")
    WebElement budgetButton;

    public CarLoanPage() {
        PageFactory.initElements(driver, this);
    }

    public void goToBudgetPage() {
        budgetButton.click();
    }
}