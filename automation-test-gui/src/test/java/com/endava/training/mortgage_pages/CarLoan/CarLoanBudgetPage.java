package com.endava.training.mortgage_pages.CarLoan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CarLoanBudgetPage {

    @FindBy(xpath = "//tr[contains(., \"Monthly payment\")]//input[@type = \"number\"]")
    WebElement monthlyPaymentField;
    @FindBy(xpath = "//tr[contains(., \"Loan term (years)\")]//input[@type = \"number\"]")
    WebElement loanTermField;
    @FindBy(xpath = "//tr[contains(., \"Annual interest rate\")]//input[@type = \"number\"]")
    WebElement annualInterestRateField;
    @FindBy(xpath = "//tr[contains(., \"Sales tax rate\")]//input[@type = \"number\"]")
    WebElement salesTaxRateField;
    @FindBy(xpath = "//tr[contains(., \"Finance\")]//select")
    WebElement financeSalesTaxSelector;
    @FindBy(xpath = "//tr[contains(., \"Your down payment\")]//input")
    WebElement downPaymentField;
    @FindBy(xpath = "//tr[contains(., \"Cash rebate added to down payment\")]//input")
    WebElement cashRebateField;
    @FindBy(xpath = "//tr[contains(., \"Trade-in value\")]//input")
    WebElement tradeInValueField;
    @FindBy(xpath = "//tr[contains(., \"Owed on trade-in\")]//input[@name=\"tradeInNeg\"]")
    WebElement owedOnTrainField;
    @FindBy(xpath = "//form[contains(., \"Auto Loan Structure\")]//input[@type=\"button\"]")
    WebElement calculateButton;


    public CarLoanBudgetPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterMonthlyPayment(String amount) {
        monthlyPaymentField.sendKeys(amount);
    }

    public void enterLoanTerm(String years) {
        loanTermField.sendKeys(years);
    }

    public void enterAnnualInterestRate(String rate) {
        annualInterestRateField.sendKeys(rate);
    }

    public void enterSalesTaxRate(String rate) {
        salesTaxRateField.sendKeys(rate);
    }

    public void selectFinanceSalesTax(boolean finance) {
        Select select = new Select(financeSalesTaxSelector);
        select.selectByValue(finance ? "1" : "0");
    }

    public void enterDownPayment(String amount) {
        downPaymentField.sendKeys(amount);
    }

    public void enterCashRebate(String amount) {
        cashRebateField.sendKeys(amount);
    }

    public void enterTradeInValue(String amount) {
        tradeInValueField.sendKeys(amount);
    }

    public void enterOwedOnTradeIn(String amount) {
        owedOnTrainField.sendKeys(amount);
    }

    public void clickCalculate() {
        calculateButton.click();
    }
}
