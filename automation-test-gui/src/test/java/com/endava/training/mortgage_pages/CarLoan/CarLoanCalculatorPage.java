package com.endava.training.mortgage_pages.CarLoan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CarLoanCalculatorPage {

    @FindBy(xpath = "//tr[contains(., \"Car price\")]//input[@type = \"number\"]")
    WebElement carPriceField;
    @FindBy(xpath = "//tr[contains(., \"Loan term\")]//input[@name= \"payments\"]")
    WebElement loanTermField;
    @FindBy(xpath = "//tr[contains(., \"Interest Rate\")]//input[@type = \"number\"]")
    WebElement interestRateField;
    @FindBy(xpath = "//tr[contains(., \"Sales Tax %\")]//input[@type = \"number\"]")
    WebElement salesTaxRateField;
    @FindBy(xpath = "//tr[contains(., \"Add sales taxes into loan\")]//select")
    WebElement financeSalesTaxSelector;
    @FindBy(xpath = "//tr[contains(., \"Down payment\")]//input")
    WebElement downPaymentField;
    @FindBy(xpath = "//tr[contains(., \"Rebate $\")]//input")
    WebElement cashRebateField;
    @FindBy(xpath = "//tr[contains(., \"Trade-in allowance\")]//input")
    WebElement tradeInValueField;
    @FindBy(xpath = "//tr[contains(., \"Owed on trade-in\")]//input[@name=\"tradein\"]")
    WebElement owedOnTrainField;
    @FindBy(xpath = "//table[@class=\"sortable\"]//input[@value=\"Calculate\" and @onclick=\"computeForm(this.form)\"]")
    WebElement calculateButton;


    public CarLoanCalculatorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterCarPrice(String amount) {
        carPriceField.sendKeys(amount);
    }

    public void enterLoanTerm(String years) {
        loanTermField.sendKeys(years);
    }

    public void enterAnnualInterestRate(String rate) {
        interestRateField.sendKeys(rate);
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
