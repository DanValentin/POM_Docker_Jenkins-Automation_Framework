package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    //aici initializam ChromeDriver-ul
    // ITestContext este o clasa de la TestNG cu care putem extrage informatii din fisierul TestNG .xml
    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {

        //BROWSER => chrome / firefox
        //HUB_HOST => localhost / 10.0.1.3 / hostname

        //aici sunt declarate valori default pentru  HUB_HOST si pt LOCALHOST
        String host = "localhost";
        DesiredCapabilities dc;

        //aici este selectat automat browser-ul cu care se ruleaza testele, daca valoarea nu este aplicata default
        if (System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            dc = DesiredCapabilities.firefox();
        } else {
            dc = DesiredCapabilities.chrome();
        }
        //aici setam valoarea pentru "HOST"
        if (System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }
        //aici este setata o variabila care extrage numele testului din fisierul TestNG .xml
        String testName = ctx.getCurrentXmlTest().getName();
        //aici este format url-ul pentru  Selenium Grid
        String completeUrl = "http://" + host + ":4444/wd/hub";
        //aici este setat numele testului in Zalenium
        dc.setCapability("name", testName);
        //aici este invocat RemoteDriver-ul cu argumentele "completeUrl" si "dc"
        this.driver = new RemoteWebDriver(new URL(completeUrl), dc);
    }


    //aici inchidem ChromeDriver-ul
    @AfterTest
    public void quitDriver(){
        this.driver.quit();
        this.driver = null;
    }
}
