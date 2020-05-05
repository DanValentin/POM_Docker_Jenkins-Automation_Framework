package com.newtours.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    //aici este introdus logger-ul Log4j
    private static final Logger logger = LogManager.getLogger(RegistrationPage.class);

    //instance level variables
    private WebDriver driver;
    private WebDriverWait wait;

    //aici localizam elementul "username"
    @FindBy(name = "firstName")
    public WebElement firstNameTxt;

    //aici localizam elementul "lastname"
    @FindBy(name = "lastName")
    public WebElement lastNameTxt;

    //aici localizam elementul "email"
    @FindBy(id = "email")
    private WebElement userNameTxt;

    //aici localizam elementul "password"
    @FindBy(name = "password")
    private WebElement passwordTxt;

    //aici localizam elementul "confirm password"
    @FindBy(name = "confirmPassword")
    private WebElement confirmPasswordTxt;

    //aici localizam elementul "register"
    @FindBy(name = "register")
    private WebElement submitBtn;

    //constructorul
    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,30); //facm o noua instanta de WebDriverWait
        PageFactory.initElements(driver, this);
        logger.debug("driver-ul este initializat"); //aici este folosit logger-ul
    }

    //metode:

    //aceasta metoda deschide website-ul si asteapta ca elementul "firstNameTxt" sa fie incarcat
    public  void goTo(){
        this.driver.get("http://newtours.demoaut.com/mercuryregister.php");
        this.wait.until(ExpectedConditions.visibilityOf(this.firstNameTxt));
        logger.debug("pagina web este deschisa");
    }
    //aceasta medoda introduce firstName si lastName
    public void enterUserDetails(String firstName, String lastName){
        this.firstNameTxt.sendKeys(firstName);
        this.lastNameTxt.sendKeys(lastName);
        logger.debug("sunt introduse detaliile user-ului");
    }
    //aceasta metoda introduce userName si password
    public void enterUserCredentials(String userName, String password){
        this.userNameTxt.sendKeys(userName);
        this.passwordTxt.sendKeys(password);
        this.confirmPasswordTxt.sendKeys(password);
        logger.debug("sunt introduse credentialele user-ului");
    }
    //aceasta metoda apasa pe butonul "Register"
    public void submit(){
        this.submitBtn.click();
        logger.debug("este apasat butonul Submit");

    }

}
