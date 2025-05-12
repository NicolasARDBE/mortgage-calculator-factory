package com.endava.training.mortgage_pages.CarLoan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarLoanBudgetResultPage {

    @FindBy(xpath = "//tr[contains(., \"You can afford a vehicle costing\")]//input")
    WebElement affordableVehicleCost;

    @FindBy(xpath = "//tr[contains(., \"Loan you need to qualify for:\")]//input")
    WebElement loanQualify;

    @FindBy(xpath = "//tr[contains(., \"Sales tax amount\")]//input")
    WebElement salesTaxAmount;

    public CarLoanBudgetResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getAffordableVehicleCost() {
        return affordableVehicleCost.getAttribute("value");
    }

    public String getLoanQualify() {
        return loanQualify.getAttribute("value");
    }

    public String getSalesTaxAmount() {
        return salesTaxAmount.getAttribute("value");
    }
}