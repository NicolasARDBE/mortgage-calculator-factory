package com.endava.training;

import com.endava.training.mortgage_pages.*;

import com.endava.training.mortgage_pages.CarLoan.*;
import com.endava.training.mortgage_pages.CheckLenders.LendersParametersPage;
import com.endava.training.mortgage_pages.CheckLenders.ValidateVendor;
import com.endava.training.mortgage_pages.FriendlyPage.FriendlyTablePage;
import com.endava.training.mortgage_pages.FriendlyPage.RedirectFriendlyPage;
import com.endava.training.mortgage_pages.Helper.ExtractValue;
import com.endava.training.mortgage_pages.MainCalculator.MainCalculatorPage;
import com.endava.training.mortgage_pages.MainCalculator.MainCalculatorResultPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MortgageCalculatorTest {

    private static final Logger logger = LoggerFactory.getLogger(MortgageCalculatorTest.class);

    private WebDriver driver;
    private DefaultPage defaultPage;
    private MainCalculatorPage mainCalculator;
    private MainCalculatorResultPage mainCalculatorResult;
    private FinancialCalcsPage financialCalcPage;

    private CarLoanPage carLoanPage;
    private CarLoanCalculatorPage carLoanCalculatorPage;
    private CarLoanCalculatorResultPage carLoanCalculatorResultPage;
    private CarLoanBudgetPage carLoanBudgetPage;
    private CarLoanBudgetResultPage carLoanBudgetResultPage;

    private RedirectFriendlyPage redirectFriendlyPage;
    private FriendlyTablePage friendlyTablePage;

    private LendersParametersPage lendersParametersPage;
    private ValidateVendor validateVendor;

    @BeforeEach
    public void setUp() {
        logger.info("Setting up WebDriver and test objects...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        defaultPage = new DefaultPage(driver);
        mainCalculator = new MainCalculatorPage(driver);
        mainCalculatorResult = new MainCalculatorResultPage(driver);
        financialCalcPage = new FinancialCalcsPage(driver);

        carLoanPage = new CarLoanPage(driver);
        carLoanCalculatorPage = new CarLoanCalculatorPage(driver);
        carLoanCalculatorResultPage = new CarLoanCalculatorResultPage(driver);
        carLoanBudgetPage = new CarLoanBudgetPage(driver);
        carLoanBudgetResultPage = new CarLoanBudgetResultPage(driver);

        redirectFriendlyPage = new RedirectFriendlyPage(driver);
        friendlyTablePage = new FriendlyTablePage(driver);

        lendersParametersPage = new LendersParametersPage(driver);
        validateVendor = new ValidateVendor(driver);
    }

    @Test
    public void calculateMortgage() {
        logger.info("Starting mortgage calculation test...");

        logger.info("Opening default page...");
        defaultPage.open();

        logger.info("Entering interest rate: 4.5");
        mainCalculator.enterInterestRate("4.5");

        logger.info("Entering loan term: 15 years");
        mainCalculator.enterLoanTerm("15");

        logger.info("Entering monthly HOA: 100");
        mainCalculator.enterMonthlyHoa("100");

        logger.info("Switching down payment input to percentage");
        mainCalculator.changePercentRadio();

        logger.info("Entering down payment percentage: 10%");
        mainCalculator.enterDownPayment("10");

        logger.info("Clicking calculate button...");
        mainCalculator.clickCalculate();

        logger.info("Retrieving and asserting down payment amount...");
        String downPaymentAmount = mainCalculatorResult.getDownPaymentAmount();
        assertEquals("$40,000.00", downPaymentAmount);

        logger.info("Retrieving and asserting down payment percentage...");
        String downPaymentPercentage = mainCalculatorResult.getDownPaymentPercentage();
        assertEquals("10.00%", downPaymentPercentage);

        logger.info("Test completed successfully.");
    }

    @Test
    public void calculateCarLoan() {
        logger.info("Starting car loan calculation test...");

        logger.info("Opening default page...");
        defaultPage.open();

        logger.info("Navigating to Car Loan Calculator...");
        financialCalcPage.goToCarCalculator();

        logger.info("Entering car price: 18000");
        carLoanCalculatorPage.enterCarPrice("18000");

        logger.info("Entering loan term: 4 years");
        carLoanCalculatorPage.enterLoanTerm("4");

        logger.info("Entering annual interest rate: 6.5%");
        carLoanCalculatorPage.enterAnnualInterestRate("6.5");

        logger.info("Entering down payment: 1500");
        carLoanCalculatorPage.enterDownPayment("1500");

        logger.info("Entering cash rebate: 750");
        carLoanCalculatorPage.enterCashRebate("750");

        logger.info("Entering trade-in value: 2500");
        carLoanCalculatorPage.enterTradeInValue("2500");

        logger.info("Entering owed on trade-in: 500");
        carLoanCalculatorPage.enterOwedOnTradeIn("500");

        logger.info("Entering sales tax rate: 7.5%");
        carLoanCalculatorPage.enterSalesTaxRate("7.5");

        logger.info("Selecting NOT to finance sales tax");
        carLoanCalculatorPage.selectFinanceSalesTax(false);

        logger.info("Clicking calculate button...");
        carLoanCalculatorPage.clickCalculate();

        logger.info("Retrieving result values...");
        String loanAmount = carLoanCalculatorResultPage.getLoanAmount();
        String monthlyPayment = carLoanCalculatorResultPage.getMonthlyPayment();
        String salesTaxAmount = carLoanCalculatorResultPage.getSalesTaxAmount();
        String interestAmount = carLoanCalculatorResultPage.getinterestAmount();
        String totalCost = carLoanCalculatorResultPage.getTotalCost();

        logger.info("Asserting expected values...");
        assertEquals("$12,000.00", loanAmount);
        assertEquals("$284.58", monthlyPayment);
        assertEquals("$1,162.50", salesTaxAmount);
        assertEquals("$1,659.81", interestAmount);
        assertEquals("$17,572.31", totalCost);

        logger.info("Car loan test completed successfully.");
    }

    @Test
    public void calculateCarLoanBudget() {
        logger.info("Starting car loan budget test...");

        logger.info("Opening default page...");
        defaultPage.open();

        logger.info("Navigating to Car Loan Calculator...");
        financialCalcPage.goToCarCalculator();

        logger.info("Navigating to Budget Page...");
        carLoanPage.goToBudgetPage();

        logger.info("Entering monthly payment you can afford: 750");
        carLoanBudgetPage.enterMonthlyPayment("750");

        logger.info("Entering loan term: 5 years");
        carLoanBudgetPage.enterLoanTerm("5");

        logger.info("Entering annual interest rate: 6.5%");
        carLoanBudgetPage.enterAnnualInterestRate("6.5");

        logger.info("Entering down payment: 2000");
        carLoanBudgetPage.enterDownPayment("2000");

        logger.info("Entering cash rebate: 500");
        carLoanBudgetPage.enterCashRebate("500");

        logger.info("Entering trade-in value: 3000");
        carLoanBudgetPage.enterTradeInValue("3000");

        logger.info("Entering owed on trade-in: 1000");
        carLoanBudgetPage.enterOwedOnTradeIn("1000");

        logger.info("Entering sales tax rate: 8%");
        carLoanBudgetPage.enterSalesTaxRate("8");

        logger.info("Selecting to finance sales tax");
        carLoanBudgetPage.selectFinanceSalesTax(true);

        logger.info("Clicking calculate button...");
        carLoanBudgetPage.clickCalculate();

        logger.info("Retrieving result values...");
        String affordableVehicleCost = carLoanBudgetResultPage.getAffordableVehicleCost();
        String loanQualify = carLoanBudgetResultPage.getLoanQualify();
        String salesTaxAmount = carLoanBudgetResultPage.getSalesTaxAmount();

        logger.info("Asserting expected values...");
        assertEquals("$39,881", affordableVehicleCost);
        assertEquals("$38,332", loanQualify);
        assertEquals("$2,950", salesTaxAmount);

        logger.info("Car loan budget test completed successfully.");
    }

    @Test
    public void ValidateValuesInFriendlyTable() {
        logger.info("Starting ValidateValuesInFriendlyTable test...");
        ExtractValue extractValue = new ExtractValue();
        String interestRate = "4.50";
        String loanTerm = "15";
        String montlyHoa = "100";
        String downPayment = "10";

        logger.info("Opening default page...");
        defaultPage.open();

        logger.info("Entering Interest Rate: {}", interestRate);
        mainCalculator.enterInterestRate(interestRate);

        logger.info("Entering Loan Term: {}", loanTerm);
        mainCalculator.enterLoanTerm(loanTerm);

        logger.info("Entering Monthly HOA: {}", montlyHoa);
        mainCalculator.enterMonthlyHoa(montlyHoa);

        logger.info("Switching to percentage down payment...");
        mainCalculator.changePercentRadio();

        logger.info("Entering Down Payment (percentage): {}", downPayment);
        mainCalculator.enterDownPayment(downPayment);

        logger.info("Clicking calculate...");
        mainCalculator.clickCalculate();

        logger.info("Redirecting to friendly table page...");
        redirectFriendlyPage.clickFriendlyPage();

        logger.info("Validating extracted values from friendly table...");
        assertEquals(interestRate, extractValue.extractNumericValue(friendlyTablePage.getInterestRate()));
        assertEquals(loanTerm, extractValue.extractNumericValue(friendlyTablePage.getLoanTerm()));
        assertEquals(montlyHoa, extractValue.extractNumericValue(friendlyTablePage.getMonthlyHoa()));

        try {
            assertEquals(downPayment, extractValue.extractNumericValue(friendlyTablePage.getDownPayment()));
        } catch (AssertionError error) {
            logger.error("Assertion failed for down payment: {}", error.getMessage());
            throw error;
        }

        logger.info("ValidateValuesInFriendlyTable test completed successfully.");
    }

    @Test
    public void ValidateLenderRedirection() {
        logger.info("Starting ValidateLenderRedirection test...");
        ExtractValue extractValue = new ExtractValue();

        logger.info("Opening default page...");
        defaultPage.open();

        logger.info("Entering ZIP code: 07008");
        lendersParametersPage.enterZipCode("07008");

        logger.info("Selecting credit score: 620");
        lendersParametersPage.selectCreditScore("620");

        logger.info("Clicking update lenders...");
        lendersParametersPage.clickOnUpdateLenders();

        logger.info("Clicking view details...");
        lendersParametersPage.clickOnViewDetails();

        String currentUrl = validateVendor.getCurrentUrl();
        String baseUrl = extractValue.extractBaseUrl(currentUrl);

        logger.info("Validating redirected base URL: {}", baseUrl);
        assertEquals("https://cash-out-refinance-icbv3.greenlending.com", baseUrl);

        logger.info("ValidateLenderRedirection test completed successfully.");
    }



    @AfterEach
    public void tearDown() {
        logger.info("Tearing down WebDriver...");
        if (driver != null) {
            driver.quit();
        }
        logger.info("Driver closed.");
    }
}
