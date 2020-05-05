package com.newtours.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindFlightPage {
    //aici este introdus logger-ul Log4j
    private static final Logger logger = LogManager.getLogger(FindFlightPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    //aici cautam butonul "Continue"
    @FindBy(name = "reserveFlights")
    private WebElement firstSubmitBtn;

    //aici cautam butonul "Secure Purchase"
    @FindBy(name = "buyFlights")
    private WebElement secondSubmitBtn;

    //constructor
    public FindFlightPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        logger.debug("driver-ul este initializat"); //aici este folosit logger-ul
    }
    //aceasta metoda da click pe butonul "Reserve Flight"
    public void submitFindFlightPage(){
        this.wait.until(ExpectedConditions.elementToBeClickable(firstSubmitBtn));
        this.firstSubmitBtn.click();
        logger.debug("este apasat butonul Reserve Flight");
    }
    //aceasta medoda da click pe butonul BuyFlights
    public void goToFlightConfirmationPage(){
        this.wait.until(ExpectedConditions.elementToBeClickable(secondSubmitBtn));
        this.secondSubmitBtn.click();
        logger.debug("este apasat butonul BuyFlights");
    }

}
