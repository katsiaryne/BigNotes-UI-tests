package com.notes;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.notes.config.ConfigProvider.URL;

public abstract class BaseTest {
    @BeforeMethod
    public void setUp() {
        open(URL);
    }

    @AfterMethod
    public void tearDown(){
        closeWebDriver();
    }
}
