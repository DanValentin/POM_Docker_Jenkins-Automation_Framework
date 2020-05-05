package com.searchmodule.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {

    //aici este introdus logger-ul Log4j
    private static final Logger logger = LogManager.getLogger(SearchPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    //aici localizam elementul campului de cautare
    @FindBy(name = "q")
    private WebElement searchTxt;

    //aici localizam elementul butonul "Video"
    @FindBy(linkText = "Videoclipuri")
    private WebElement videosLink;

    //aici localizam toate clipurile de pe pagina
    @FindBy(className = "g")
    private List<WebElement> allVideos;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
        logger.debug("driver-ul este initializat"); //aici este folosit logger-ul
    }
    //Metode:
    //aici deschidem website-ul
    public  void goTo(){
        this.driver.get("https://google.com");
    }
    //aici efectuam o cautare
    public void doSearch(String keyword){
        this.wait.until(ExpectedConditions.visibilityOf(searchTxt));
        this.searchTxt.sendKeys(keyword);
        this.searchTxt.sendKeys(Keys.ENTER);
        logger.debug("pagina google este deschisa");
    }
    //aici suntem pe pagina cu rezultate si apasam pe butonul "Video"
    public void goToVideos(){
        this.wait.until(ExpectedConditions.visibilityOf(videosLink));
        this.videosLink.click();
        logger.debug("butonul 'video' este apasat");
    }
    //aici printam cate clipuri sunt diponibile pe pagina
    public int getResult(){
        By by = By.className("g");
        this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
        System.out.println(this.allVideos.size());
        logger.debug("este afisat numarul total de clipuri diponibile pe pagina");
        return this.allVideos.size();

    }

}
