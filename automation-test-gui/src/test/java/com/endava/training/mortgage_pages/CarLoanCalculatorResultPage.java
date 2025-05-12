package com.endava.training.mortgage_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarLoanCalculatorResultPage {

    private final WebDriver driver;

    @FindBy(xpath = "//tr[contains(., \"Loan amount:\")]//input[@name=\"loanAmt\"]")
    WebElement loanAmount;
    @FindBy(name = "payment")
    WebElement monthlyPayment;
    @FindBy(name = "salestax")
    WebElement salesTax;
    @FindBy(name = "interestamount")
    WebElement interestAmount;
    @FindBy(name = "totalcost")
    WebElement totalCost;


    public CarLoanCalculatorResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getLoanAmount() {
        return loanAmount.getAttribute("value");
    }

    public String getMonthlyPayment() {
        return monthlyPayment.getAttribute("value");
    }

    public String getSalesTaxAmount() {
        return salesTax.getAttribute("value");
    }

    public String getinterestAmount() {
        return interestAmount.getAttribute("value");
    }

    public String getTotalCost() {
        return totalCost.getAttribute("value");
    }
}