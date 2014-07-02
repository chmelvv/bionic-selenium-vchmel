package com.bionic_university.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Viktor_Chmel on 23.06.2014.
 */

/* Test Case
    1) Open google.com in FF
    2) search "selenium"
    3) Obtain first result
    4) Close FF
*/
public class GoogleSearchTest {
    WebDriver driver = new ChromeDriver();

    @BeforeClass //Preconditions
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //wait browser 30 sec
        driver.manage().window().maximize(); //IMPORTANT!!! Has influence on clickability of elements
        driver.get("https://www.google.com.ua");
    }

    @Test
    public void searchTest() {
      //  WebElement searchField = driver.findElement(By.id("gbqfq")); //found id of search field in Google
//        WebElement searchField = driver.findElement(By.name("q")); //found name  of search field in Google
        WebElement searchField = driver.findElement(By.partialLinkText("Selenium"));
        searchField.sendKeys("Selenium IDE"); // transfer "Selenium" to search field

        WebElement seleniumLink = driver.findElement(By.xpath("IDE")); //Found via FireBug and XPath Xpath to first searched element

        Assert.assertEquals(seleniumLink.getText().toString().contains("Selenium"), true);

    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

}
