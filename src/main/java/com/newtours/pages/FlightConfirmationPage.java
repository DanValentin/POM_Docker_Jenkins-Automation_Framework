package com.newtours.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FlightConfirmationPage {

    //aici este introdus logger-ul Log4j
    private static final Logger logger = LogManager.getLogger(FlightConfirmationPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    //aici localizam textul de confirmare
    @FindBy(xpath = "//font[contains(text(),'Flight')]")
    private WebElement flightConfirmationHeader;

    //aici cautam toate elementele din pagina care contin textul "USD"
    @FindBy(xpath = "//font[contains(text(),'USD')]")
    private List<WebElement> prices;

    //aici localizam butonul pentru deconectare
    @FindBy(linkText = "SIGN-OFF")
    private WebElement signOffLink;

    //constructor
    public FlightConfirmationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        logger.debug("driver-ul este initializat"); //aici este folosit logger-ul
    }

    //aceasta metoda verifica textul de confirmare
    public String getPrice(){
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationHeader));
        String price = this.prices.get(1).getText();
        this.signOffLink.click();
        logger.debug("Varificam daca suntem in confimation page si verificam si pretul");
        return price;
    }

}
