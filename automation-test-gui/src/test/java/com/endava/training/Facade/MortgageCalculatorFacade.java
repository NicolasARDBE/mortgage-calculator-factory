package com.endava.training.Facade;

import com.endava.training.mortgage_pages.*;
import com.endava.training.mortgage_pages.CarLoan.*;
import com.endava.training.mortgage_pages.CheckLenders.LendersParametersPage;
import com.endava.training.mortgage_pages.FriendlyPage.RedirectFriendlyPage;
import com.endava.training.mortgage_pages.MainCalculator.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MortgageCalculatorFacade {

    private static final Logger logger = LoggerFactory.getLogger(MortgageCalculatorFacade.class);
    private final DefaultPage defaultPage;
    private final MainCalculatorPage mainCalculatorPage;
    private final FinancialCalcsPage financialCalcPage;
    private final CarLoanCalculatorPage carLoanCalculatorPage;
    private final CarLoanPage carLoanPage;
    private final CarLoanBudgetPage carLoanBudgetPage;
    private final RedirectFriendlyPage redirectFriendlyPage;
    private final LendersParametersPage lendersParametersPage;


    public MortgageCalculatorFacade(WebDriver driver) {
        defaultPage = new DefaultPage(driver);
        mainCalculatorPage = new MainCalculatorPage(driver);
        financialCalcPage = new FinancialCalcsPage(driver);
        carLoanCalculatorPage = new CarLoanCalculatorPage(driver);
        carLoanPage = new CarLoanPage(driver);
        carLoanBudgetPage = new CarLoanBudgetPage(driver);
        redirectFriendlyPage = new RedirectFriendlyPage(driver);
        lendersParametersPage = new LendersParametersPage(driver);
    }

    public void calculateMortgage(String interestRate, String loanTerm, String hoa, String downPayment) {
        logger.info("Starting mortgage calculation test...");
        logger.info("Opening default page...");
        defaultPage.open();
        logger.info("Entering interest rate: " + interestRate + "...");
        mainCalculatorPage.enterInterestRate(interestRate);
        logger.info("Entering loan term: " + loanTerm + "...");
        mainCalculatorPage.enterLoanTerm(loanTerm);
        logger.info("Entering HOA: " + hoa + "...");
        mainCalculatorPage.enterMonthlyHoa(hoa);
        logger.info("Changing to percent radio button...");
        mainCalculatorPage.changePercentRadio();
        logger.info("Entering down payment: " + downPayment + "...");
        mainCalculatorPage.enterDownPayment(downPayment);
        logger.info("Clicking calculate button...");
        mainCalculatorPage.clickCalculate();
    }

    public void calculateCarLoan(String carPrice, String loanTerm, String interestRate, String downPayment, String cashRebate, String tradeInValue, String owedOnTradeIn, String salesTaxRate, boolean financeSalesTax) {
        logger.info("Starting car loan calculation test...");
        logger.info("Opening default page...");
        defaultPage.open();
        logger.info("Navigating to car calculator...");
        financialCalcPage.goToCarCalculator();
        logger.info("Entering car price: " + carPrice + "...");
        carLoanCalculatorPage.enterCarPrice(carPrice);
        logger.info("Entering loan term: " + loanTerm + "...");
        carLoanCalculatorPage.enterLoanTerm(loanTerm);
        logger.info("Entering interest rate: " + interestRate + "...");
        carLoanCalculatorPage.enterAnnualInterestRate(interestRate);
        logger.info("Entering down payment: " + downPayment + "...");
        carLoanCalculatorPage.enterDownPayment(downPayment);
        logger.info("Entering cash rebate: " + cashRebate + "...");
        carLoanCalculatorPage.enterCashRebate(cashRebate);
        logger.info("Entering trade-in value: " + tradeInValue + "...");
        carLoanCalculatorPage.enterTradeInValue(tradeInValue);
        logger.info("Entering owed on trade-in: " + owedOnTradeIn + "...");
        carLoanCalculatorPage.enterOwedOnTradeIn(owedOnTradeIn);
        logger.info("Entering sales tax rate: " + salesTaxRate + "...");
        carLoanCalculatorPage.enterSalesTaxRate(salesTaxRate);
        logger.info("Selecting finance sales tax: " + financeSalesTax + "...");
        carLoanCalculatorPage.selectFinanceSalesTax(financeSalesTax);
        logger.info("Clicking calculate button...");
        carLoanCalculatorPage.clickCalculate();
    }

    public void calculateCarLoanBudget(String yearlyPayment, String loanTerm, String annualInterestRate, String downPayment, String cashRebate, String tradeInValue, String owedOnTradeIn, String salesTaxRate, boolean financeSalesTax) {
        logger.info("Starting car loan budget test...");
        logger.info("Opening default page...");
        defaultPage.open();
        logger.info("Navigating to financial calculations page...");
        financialCalcPage.goToCarCalculator();
        logger.info("Navigating to car loan budget page...");
        carLoanPage.goToBudgetPage();
        logger.info("Entering monthly payment you can afford: " + yearlyPayment + "...");
        carLoanBudgetPage.enterMonthlyPayment(yearlyPayment);
        logger.info("Entering loan term: " + loanTerm + "...");
        carLoanBudgetPage.enterLoanTerm(loanTerm);
        logger.info("Entering annual interest rate: " + annualInterestRate + "...");
        carLoanBudgetPage.enterAnnualInterestRate(annualInterestRate);
        logger.info("Entering down payment: " + downPayment + "...");
        carLoanBudgetPage.enterDownPayment(downPayment);
        logger.info("Entering cash rebate: " + cashRebate + "...");
        carLoanBudgetPage.enterCashRebate(cashRebate);
        logger.info("Entering trade-in value: " + tradeInValue + "...");
        carLoanBudgetPage.enterTradeInValue(tradeInValue);
        logger.info("Entering owed on trade-in: " + owedOnTradeIn + "...");
        carLoanBudgetPage.enterOwedOnTradeIn(owedOnTradeIn);
        logger.info("Entering sales tax rate: " + salesTaxRate + "...");
        carLoanBudgetPage.enterSalesTaxRate(salesTaxRate);
        logger.info("Selecting to finance sales tax: " + financeSalesTax + "...");
        carLoanBudgetPage.selectFinanceSalesTax(financeSalesTax);
        logger.info("Clicking calculate button...");
        carLoanBudgetPage.clickCalculate();
    }

    public void validateValuesInFriendlyTable(String interestRate, String loanTerm, String monthlyHoa, String downPayment) {
        logger.info("Starting ValidateValuesInFriendlyTable test...");
        logger.info("Opening default page...");
        defaultPage.open();
        logger.info("Entering Interest Rate: {}", interestRate);
        mainCalculatorPage.enterInterestRate(interestRate);
        logger.info("Entering Loan Term: {}", loanTerm);
        mainCalculatorPage.enterLoanTerm(loanTerm);
        logger.info("Entering Monthly HOA: {}", monthlyHoa);
        mainCalculatorPage.enterMonthlyHoa(monthlyHoa);
        logger.info("Switching to percentage down payment...");
        mainCalculatorPage.changePercentRadio();
        logger.info("Entering Down Payment (percentage): {}", downPayment);
        mainCalculatorPage.enterDownPayment(downPayment);
        logger.info("Clicking calculate...");
        mainCalculatorPage.clickCalculate();
        logger.info("Redirecting to friendly table page...");
        redirectFriendlyPage.clickFriendlyPage();
    }

    public void validateLenderRedirection(String zipCode, String creditScore) {
        logger.info("Starting ValidateLenderRedirection test...");
        logger.info("Opening default page...");
        defaultPage.open();
        logger.info("Entering ZIP code: {}", zipCode);
        lendersParametersPage.enterZipCode(zipCode);
        logger.info("Selecting credit score: {}", creditScore);
        lendersParametersPage.selectCreditScore(creditScore);
        logger.info("Clicking update lenders...");
        lendersParametersPage.clickOnUpdateLenders();
        logger.info("Clicking view details...");
        lendersParametersPage.clickOnViewDetails();
    }
}