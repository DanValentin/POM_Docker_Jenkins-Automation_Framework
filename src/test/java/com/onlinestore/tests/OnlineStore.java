package com.onlinestore.tests;

import com.onlinestore.pages.ContactUsPage;
import com.onlinestore.pages.OrderProducts;
import org.testng.annotations.Test;
import tests.BaseTest;

public class OnlineStore extends BaseTest {



    @Test
    public void orderProducts(){
        OrderProducts orderProducts = new OrderProducts(driver);
        orderProducts.goTo();
        orderProducts.loginStep();
        orderProducts.orderProduct();
        orderProducts.proceedOrder();
        orderProducts.payOrder();
        orderProducts.confirmationPage();
    }
    @Test
    public void contactUsPage(){
        ContactUsPage contactUs = new ContactUsPage(driver);

       contactUs.goTo();
       contactUs.contactUsClick();
       contactUs.selectFromDropDown();
       contactUs.enterEmailAddress();
       contactUs.enterOrderId();
       contactUs.enterMessage();
       contactUs.sendForm();
       contactUs.textConfirmationText();
    }
}
