package com.searchmodule.tests;

import com.searchmodule.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

public class SearchTest extends BaseTest {


    //aici efectuam testul
    @Test
    @Parameters({"keyword"}) //parametrul este setat din fisierul .xml TestNG
    public void search(String keyword){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.goToVideos();
        int size = searchPage.getResult(); //aici stocam numarul de videouri in variabila  "size"
        Assert.assertTrue(size > 0); //aici verificam ca numarul total de videouri este mai mare ca 0
    }
}
