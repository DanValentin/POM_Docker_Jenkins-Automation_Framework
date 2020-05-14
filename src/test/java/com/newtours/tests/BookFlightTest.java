package com.newtours.tests;

import com.newtours.pages.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

public class BookFlightTest extends BaseTest {

    //parametri pentru teste
    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"}) //valoarea acestor parametri este data din fisierul  de tip .xml de la TestNG
    public void setupParameters(String noOfPassengers, String expectedPrice){
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    //aici rulam testul pentru RegistrationPage
    @Test
    @Description("User Registration Page")
    @Epic("EP001")
    @Story("RegistrationPage")
    @Severity(SeverityLevel.BLOCKER)
    public void registrationPage(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("Dan", "Popa");
        registrationPage.enterUserCredentials("DanPopa", "P@ssw0rd");
        registrationPage.submit();
    }
    //aici rulam testul pentru RegistrationConfirmationPage
    @Test(dependsOnMethods = "registrationPage")
    @Description("User Registration Confirmation Page")
    @Epic("EP001")
    @Story("RegistrationConfirmationPage")
    @Severity(SeverityLevel.CRITICAL)
    public void registrationConfirmationPage(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightDetailsPage();
    }
    //aici rulam FlightDetailsPage
    @Test(dependsOnMethods = "registrationConfirmationPage")
    @Description("aici am rulat FlightDetailsPage")
    @Epic("EP001")
    @Story("FlightDetailsPage")
    @Severity(SeverityLevel.MINOR)
    public void flightDetailsPage(){
        FlightsDetailsPage flightsDetailsPage = new FlightsDetailsPage(driver);
        flightsDetailsPage.selectPassengers(noOfPassengers);
        flightsDetailsPage.goToFindFlightsPage();
    }
    //aici rulam FindFlightPage
    @Test(dependsOnMethods = "flightDetailsPage")
    @Description("aici am rulat FindFlightPage")
    @Epic("EP001")
    @Story("FindFlightPage")
    public void findFlightPage(){
        FindFlightPage findFlightPage = new FindFlightPage(driver);
        findFlightPage.submitFindFlightPage();
        findFlightPage.goToFlightConfirmationPage();
    }
    //aici rulam FlightConfirmationPage
    @Description("aici am rulat FlightConfirmationPage")
    @Test(dependsOnMethods = "findFlightPage")
    @Epic("EP001")
    @Story("FlightConfirmationPage")
    public void flightConfirmationPage(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        String actualPrice = flightConfirmationPage.getPrice();
        Assert.assertEquals(actualPrice, expectedPrice); //aici verificam ca pretul afisat este cel dorit (setat in fisierul TestNG)
    }
}
