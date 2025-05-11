package com.endava.training;

import com.endava.training.mortgage_pages.DefaultPage;
import com.endava.training.mortgage_pages.MainCalculator;
import com.endava.training.mortgage_pages.MainCalculatorResult;

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
    private MainCalculator mainCalculator;
    private MainCalculatorResult mainCalculatorResult;

    @BeforeEach
    public void setUp() {
        logger.info("Setting up WebDriver and test objects...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        defaultPage = new DefaultPage(driver);
        mainCalculator = new MainCalculator(driver);
        mainCalculatorResult = new MainCalculatorResult(driver);
        logger.info("Setup completed.");
    }

    @Test
    public void CalculateMortgage() {
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

    @AfterEach
    public void tearDown() {
        logger.info("Tearing down WebDriver...");
        if (driver != null) {
            driver.quit();
        }
        logger.info("Driver closed.");
    }
}
