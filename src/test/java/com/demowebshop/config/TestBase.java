package com.demowebshop.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demowebshop.until.AttachManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;


public class TestBase {
    @BeforeAll
    static  void setUp(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void before(){
        Configuration.browserSize = "1920x1080";
    }
    @AfterEach
    void after(){
        clearBrowserCookies();
        clearBrowserLocalStorage();

        AttachManager.takeScreenshot();
        AttachManager.getPageSource();
        AttachManager.getBrowserConsoleLogs();
    }
}
