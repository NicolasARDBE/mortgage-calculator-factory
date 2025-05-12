package com.endava.training.mortgage_pages.FriendlyPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FriendlyTablePage {
    @FindBy(xpath = "//tr[contains(., \"Interest rate\")]/td")
    WebElement interestRateField;
    @FindBy(xpath = "//tr[contains(., \"Loan term\")]/td")
    WebElement loanTermField;
    @FindBy(xpath = "//tr[contains(., \"Monthly HOA Fee:\")]/td")
    WebElement monthlyHoaField;
    @FindBy(xpath = "//tr[contains(., \"Down payment:\")]/td")
    WebElement downPaymentField;

    public FriendlyTablePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public String getInterestRate() {
        return interestRateField.getText();
    }

    public String getLoanTerm() {
        return loanTermField.getText();
    }
    public String getMonthlyHoa() {
        return monthlyHoaField.getText();
    }

    public String getDownPayment() {
        return downPaymentField.getText();
    }
}