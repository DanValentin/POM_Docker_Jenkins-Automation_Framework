package com.newtours.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationConfirmationPage {

    //aici este introdus logger-ul Log4j
    private static final Logger logger = LogManager.getLogger(RegistrationConfirmationPage.class);
    private WebDriver driver;
    private WebDriverWait wait;

    //aici localizam textul de confirmare, faptul ca login-ul a fost efectuat cu succes
    @FindBy(partialLinkText = "sign-in")
    public WebElement signInLink;

    //aici localizam butonul "Flights"
    @FindBy(linkText = "Flights")
    public WebElement flightLink;

    //constructorul
    public RegistrationConfirmationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        logger.debug("driver-ul este initializat"); //aici este folosit logger-ul
    }
    //aceasta metoda verifica faca suntm in ConfirmationPage si dupa apasa pe butonul "Flights"
    public void goToFlightDetailsPage(){
        this.wait.until(ExpectedConditions.visibilityOf(signInLink));
        this.flightLink.click();
        logger.debug("este apasat butonul 'Flights");
    }

}
