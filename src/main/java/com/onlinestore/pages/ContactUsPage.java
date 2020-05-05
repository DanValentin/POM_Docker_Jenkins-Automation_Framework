package com.onlinestore.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ContactUsPage {
    //aici este introdus logger-ul Log4j
    private static final Logger logger = LogManager.getLogger(ContactUsPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    //aici gasim butonul ContuctUs
    @FindBy(linkText = "Contact us")
    public WebElement contactUsButton;

    //aici gasim dropdown list
    @FindBy(id = "uniform-id_contact")
    public WebElement beforeSelectDropDowm;
    //aici gasim dropdown list
    @FindBy(id = "id_contact")
    public WebElement selectSubiect;

    //aici introducem emailul
    @FindBy(id = "email")
    public WebElement enterEmail;

    //aici introducem numarul comenzii
    @FindBy(id = "id_order")
    public WebElement orderNumber;

    //aici introducem textul dorit
    @FindBy(id = "message")
    public WebElement textMessage;

    //aici gasim butonul Send
    @FindBy(id = "submitMessage")
    public WebElement sendMessage;

    //aici cautam textul de confirmare
    @FindBy(xpath = "//*[@id=\'center_column\']/p")
    public WebElement confirmationMessage;



    //constructor
    public ContactUsPage (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
        logger.debug("driver-ul este initializat"); //aici este folosit logger-ul
    }
    //aceasta metoda deschide website-ul
    public void goTo(){
        this.driver.get("http://automationpractice.com/index.php");
        this.wait.until(ExpectedConditions.visibilityOf(contactUsButton));
        logger.debug("pagina este deschisa");
    }
    //aici apasam pe butonul ContactUs
    public void contactUsClick(){
        this.contactUsButton.click();
        logger.debug("este apasat butonul ContactUs");
    }
    //aici selectam din dropdown list
    public void selectFromDropDown(){
        this.wait.until(ExpectedConditions.visibilityOf(beforeSelectDropDowm));
        this.beforeSelectDropDowm.click();
        Select selectFrom = new Select(this.selectSubiect);
        selectFrom.selectByIndex(1);
        logger.debug("aici este selectat motivul din formular");
    }
    //aici introducem emailul
    public void enterEmailAddress(){
        this.enterEmail.sendKeys("test@test.com");
        logger.debug("aici este introdusa adresa de email");
    }
    //aici introducem numaul comenzii
    public void enterOrderId(){
        this.orderNumber.sendKeys("123456");
        logger.debug("aici este introdusa ordernumber");
    }
    //aici introducem textul
    public void enterMessage(){
        this.textMessage.sendKeys("any text message here");
        logger.debug("aici este introdus mesajul text");
    }
    //aici trimitem formularul
    public void sendForm(){
        this.sendMessage.click();
        logger.debug("aici este apasat pe butonul Send");
    }
    //aici verificam ca mesajul a fost transmis cu succes

    public void textConfirmationText(){
        String actualdText = this.confirmationMessage.getText();
        String expectedText = "Your message has been successfully sent to our team.";
        Assert.assertEquals(actualdText, expectedText);
        logger.debug("aici este verificat daca formularul a fost trimis cu succes");
    }



}
