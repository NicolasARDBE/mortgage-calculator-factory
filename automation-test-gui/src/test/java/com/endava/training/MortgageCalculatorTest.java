package com.endava.training;

import com.endava.training.Facade.MortgageCalculatorFacade;
import com.endava.training.Singleton.DriverSingleton;

import com.endava.training.mortgage_pages.CarLoan.*;
import com.endava.training.mortgage_pages.CheckLenders.LendersParametersPage;
import com.endava.training.mortgage_pages.CheckLenders.ValidateVendor;
import com.endava.training.mortgage_pages.FriendlyPage.FriendlyTablePage;
import com.endava.training.Helper.ExtractValue;
import com.endava.training.mortgage_pages.MainCalculator.MainCalculatorResultPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MortgageCalculatorTest {

    private static final Logger logger = LoggerFactory.getLogger(MortgageCalculatorTest.class);
    private MortgageCalculatorFacade facade;

    private WebDriver driver;
    private MainCalculatorResultPage mainCalculatorResult;


    private CarLoanCalculatorResultPage carLoanCalculatorResultPage;
    private CarLoanBudgetResultPage carLoanBudgetResultPage;

    private FriendlyTablePage friendlyTablePage;

    private ValidateVendor validateVendor;

    @BeforeEach
    public void setUp() {
        logger.info("Setting up WebDriver and test objects...");
        driver = DriverSingleton.getDriver();
        facade = new MortgageCalculatorFacade(driver);
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        mainCalculatorResult = new MainCalculatorResultPage(driver);

        carLoanCalculatorResultPage = new CarLoanCalculatorResultPage(driver);
        carLoanBudgetResultPage = new CarLoanBudgetResultPage(driver);

        friendlyTablePage = new FriendlyTablePage(driver);

        validateVendor = new ValidateVendor(driver);
    }

    @Test
    public void calculateMortgage() {
        facade.calculateMortgage("4.5", "15", "100", "10");
        String downPaymentAmount = mainCalculatorResult.getDownPaymentAmount();
        assertEquals("$40,000.00", downPaymentAmount);
        String downPaymentPercentage = mainCalculatorResult.getDownPaymentPercentage();
        assertEquals("10.00%", downPaymentPercentage);

        logger.info("Retrieving and asserting down payment percentage...");
        logger.info("Test completed successfully.");
    }

    @Test
    public void calculateCarLoan() {
        facade.calculateCarLoan("18000", "4", "6.5", "1500", "750", "2500", "500", "7.5", false);
        logger.info("Starting car loan calculation test...");
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
        facade.calculateCarLoanBudget("750", "5", "6.5", "2000", "500", "3000", "1000", "8", true);

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
        ExtractValue extractValue = new ExtractValue();
        String interestRate = "4.50";
        String loanTerm = "15";
        String monthlyHoa = "100";
        String downPayment = "10";

        facade.validateValuesInFriendlyTable(interestRate, loanTerm, monthlyHoa, downPayment);

        logger.info("Validating extracted values from friendly table...");
        assertEquals(interestRate, extractValue.extractNumericValue(friendlyTablePage.getInterestRate()));
        assertEquals(loanTerm, extractValue.extractNumericValue(friendlyTablePage.getLoanTerm()));
        assertEquals(monthlyHoa, extractValue.extractNumericValue(friendlyTablePage.getMonthlyHoa()));

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
        facade.validateLenderRedirection("07008", "620");
        ExtractValue extractValue = new ExtractValue();

        String currentUrl = validateVendor.getCurrentUrl();
        String baseUrl = extractValue.extractBaseUrl(currentUrl);


        logger.info("Validating redirected base URL: {}", baseUrl);
        assertTrue(baseUrl.contains("https://"));

        logger.info("ValidateLenderRedirection test completed successfully.");
    }


    @AfterEach
    public void tearDown() {
        logger.info("Tearing down WebDriver...");
        if (driver != null) {
            DriverSingleton.quitDriver();
        }
        logger.info("Driver closed.");
    }
}