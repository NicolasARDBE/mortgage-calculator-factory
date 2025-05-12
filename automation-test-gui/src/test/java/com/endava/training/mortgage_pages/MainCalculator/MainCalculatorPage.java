package com.endava.training.mortgage_pages.MainCalculator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainCalculatorPage {

    @FindBy(id = "intrstsrate")
    WebElement interestRateField;
    @FindBy(id = "loanterm")
    WebElement loanTermField;
    @FindBy(id = "hoa")
    WebElement monthlyHoaField;
    @FindBy(xpath = "//div[contains(., \"Down payment:\")]//input[@value=\"percent\"]")
    WebElement percentRadio;
    @FindBy(id = "downpayment")
    WebElement downPaymentField;
    @FindBy(css = "div.rw-box.button > input")
    WebElement calculateButton;

    public MainCalculatorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterInterestRate(String interest) {
        interestRateField.sendKeys(interest);
    }

    public void enterLoanTerm(String loanTear) {
        loanTermField.sendKeys(loanTear);
    }

    public void enterMonthlyHoa(String monthlyHoa) {
        monthlyHoaField.sendKeys(monthlyHoa);
    }

    public void changePercentRadio() {
        percentRadio.click();
    }

    public void enterDownPayment(String downPayment) {
        downPaymentField.sendKeys(downPayment);
    }

    public void clickCalculate() {
        calculateButton.click();
    }
}
