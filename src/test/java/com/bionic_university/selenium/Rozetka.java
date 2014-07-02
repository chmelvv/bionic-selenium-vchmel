package com.bionic_university.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by Viktor_Chmel on 26.06.2014.
 * 1. Вчимо локатори та TestNG annotations

 2. Треба написати селеніум тест і запустити на Дженкінсі

 Завдання:

 1)Заходимо на сайт http://rozetka.com.ua/ в FF
 2) В  "Поиск" вводимо Samsung G900H Galaxy S5 Black і нажимаємо "Найти"
 3) Відкрилась сторінка з смартфоном
 4) Перевіряємо, що  це Samsung G900H Galaxy S5 Black, а не Nokia (по унікальному полю)
 5) Нажимаємо на ссилку Samsung G900H Galaxy S5 Black
 6) Відкрилась сторінка з характеристиками смартфона
 7) Перевірити чи є в характеристиках Exynos 5422 (Quad 1.9 ГГц + Quad 1.3 ГГц)
 8) Закрити браузер

 */
public class Rozetka {
    WebDriver driver = new FirefoxDriver();
    WebElement firstLink = null;

    @BeforeSuite
    public void setUp(){
        driver.manage().window().maximize();
        driver.get("http://www.rozetka.com.ua");
    }


    @Test
    public void searchModel(){
        WebElement searchField = driver.findElement(By.className("header-search-input-text"));
        searchField.sendKeys("Samsung G900H Galaxy S5 Black");
        WebElement searchButton = driver.findElement(By.className("btn-link-i"));
        searchButton.click();

        this.firstLink = driver.findElement(
                //Choose link of first position in search result list
                By.xpath("(//div[contains(concat(' ', @class, ' '), 'g-i-list-title')])[1]/a"));
        Assert.assertEquals(firstLink.getText(), "Samsung G900H Galaxy S5 Black");
    }

    @Test (dependsOnMethods={"searchModel"})
    public void checkOption(){
        firstLink.click();

        WebElement description = driver.findElement(By.cssSelector(".pp-description"));
        //Exynos 5422 (Quad 1.9 ГГц + Quad 1.3 ГГц)
        Assert.assertTrue(description.getText().contains("Exynos"));

    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}
