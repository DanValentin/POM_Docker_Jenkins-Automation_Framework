package com.onlinestore.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OrderProducts {

    //aici este introdus logger-ul Log4j
    private static final Logger logger = LogManager.getLogger(OrderProducts.class);

    private WebDriver driver;
    private WebDriverWait wait;

    //aici localizam butonul SignIn
    @FindBy(linkText = "Sign in")
    private WebElement signInButton;

    //aici localizam campul "email"
    @FindBy(id = "email")
    private WebElement enterEmail;

    //aici localizam campul "password"
    @FindBy(id = "passwd")
    private WebElement enterPassword;

    //aici localizam butonul de Login
    @FindBy(id = "SubmitLogin")
    private WebElement loginButton;

    //aici localizam butonul de Home
    @FindBy(xpath = "//*[@id=\'header_logo\']/a/img")
    private WebElement homeButton;

    //aici localizam primul produs
    @FindBy(xpath = "//*[@id=\'homefeatured\']/li[1]/div")
    private WebElement hoverOverFirstProduct;

    //aici localizam butonul "add to cart"
    @FindBy(xpath = "//*[@id=\'homefeatured\']/li[1]/div/div[2]/div[2]/a[1]/span")
    private WebElement addToCartFirstProduct;

    //aici localizam butonul Proceed to cart
    @FindBy(xpath = "//*[@id=\'layer_cart\']/div[1]/div[2]/div[4]/a/span")
    private WebElement proceedToCart;

    //aici localizam butonul Proceed to Checkout
    @FindBy(xpath = "//*[@id=\'center_column\']/p[2]/a[1]/span")
    public WebElement proceedToCheckout;

    //aici localizam butonul de Proceed to checkout
    @FindBy(name = "processAddress")
    public WebElement proceedToCheckoutAddress;

    //aici localizam butonul de acceptare  de termeni si conditii
    @FindBy(id = "uniform-cgv")
    private WebElement termeniConditii;

    //aici localizam butonul Proceed to checkout
    @FindBy(name = "processCarrier")
    private WebElement proceedToCheckoutCarrier;

    //aici localizam butonul Pay
    @FindBy(xpath = "//a[@class='bankwire']")
    private WebElement payButton;

    //aici localizam butonul de ConfirmOrder
    @FindBy(xpath = "//*[@id='cart_navigation']/button")
    private WebElement confirmOrder;

    //aici localizam textul de confirmare
    @FindBy(xpath = "//*[@id=\'center_column\']/div/p/strong")
    private WebElement confirmareComanda;




    //constructor
    public OrderProducts(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
        //aici initalizam instanta "driver" si "wait"
        PageFactory.initElements(driver, this);
        logger.debug("driver-ul este initializat"); //aici este folosit logger-ul
    }

    //aceasta metoda deschide website-ul
    public void goTo(){
        this.driver.get("http://automationpractice.com/index.php");
        this.wait.until(ExpectedConditions.visibilityOf(this.signInButton));
    }
    //in aceasta metoda ne logam in aplicatie
    public void loginStep(){
        this.signInButton.click();
        this.wait.until(ExpectedConditions.visibilityOf(enterEmail));
        this.enterEmail.sendKeys("dan.popa86@gmail.com");
        this.enterPassword.sendKeys("vs031986");
        this.loginButton.click();
        this.wait.until(ExpectedConditions.visibilityOf(homeButton));
        this.homeButton.click();
        logger.debug("suntem logati in aplicatie");
    }
    //acum adaugam un produs in cos
    public void orderProduct(){
        this.wait.until(ExpectedConditions.visibilityOf(hoverOverFirstProduct));
        Actions actions = new Actions(driver);
        WebElement hoverOverProduct = this.hoverOverFirstProduct;
        actions.moveToElement(hoverOverProduct).perform();
        this.addToCartFirstProduct.click();
    }
    //aici deschidem cosul
    public void proceedOrder(){
        this.wait.until(ExpectedConditions.visibilityOf(proceedToCart));
        this.proceedToCart.click();
        logger.debug("am deschis cosul");
        this.wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
        this.proceedToCheckout.click();
        logger.debug("am deschis checkout-ul");
        this.wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutAddress));
        this.proceedToCheckoutAddress.click();
        logger.debug("am trecut de adresa");
        this.wait.until(ExpectedConditions.visibilityOf(termeniConditii));
        this.termeniConditii.click();
        this.proceedToCheckoutCarrier.click();
        logger.debug("am acceptat termenii si conditiile si am inaintat catre plata");
    }
    //aici efectuamm plata
    public void payOrder(){
        this.wait.until(ExpectedConditions.visibilityOf(payButton));
        this.payButton.click();
        this.wait.until(ExpectedConditions.visibilityOf(confirmOrder));
        this.confirmOrder.click();
        logger.debug("comanda este plasata");
    }
    //aici verificam ca suntem pe pagina de confirmare
    public void confirmationPage(){
        this.wait.until(ExpectedConditions.visibilityOf(confirmareComanda));
        String verificarePlata = this.confirmareComanda.getText();
        String expectedText = "Your order on My Store is complete.";
        Assert.assertEquals(verificarePlata, expectedText);
        logger.debug("verificarea efectuata cu success");
    }


}
