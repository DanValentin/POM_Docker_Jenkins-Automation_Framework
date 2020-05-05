package com.newtours.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightsDetailsPage {

    //aici este introdus logger-ul Log4j
    private static final Logger logger = LogManager.getLogger(FlightsDetailsPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    //aici localizam dropdown-ul in care selectam numarul pasagerilor
    @FindBy(name = "passCount")
    private WebElement passengers;

    //aici localizam butonul "Find Flights"
    @FindBy(name = "findFlights")
    private WebElement submitBtn;

    //construsctor
    public FlightsDetailsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        logger.debug("driver-ul este initializat"); //aici este folosit logger-ul
    }
    // aici selectam numarul pasagerilor
    public void selectPassengers (String noOfPassengers){
        this.wait.until(ExpectedConditions.elementToBeClickable(passengers));
        Select select = new Select(passengers);
        select.selectByValue(noOfPassengers);
        logger.debug("sunt selectati numarul de pasageri");
    }
    //aici apasam pe butonul FindFlights
    public void goToFindFlightsPage(){
        this.submitBtn.click();
        logger.debug("este apasat butonul FindFlights ");
    }
}
