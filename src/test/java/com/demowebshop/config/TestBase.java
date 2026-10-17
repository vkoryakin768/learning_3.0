package com.demowebshop.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demowebshop.until.AttachManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.demowebshop.config.Config.getSelenoidChromeOptions;
import static com.demowebshop.config.Config.getWebdriverConfig;


public class TestBase {

    private static final WebDriverConfig config = getWebdriverConfig();

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browserSize = config.browserSize();
        Configuration.browser = config.browser();

        if ("remote".equals(System.getProperty("run"))) {
            Configuration.remote = "https://" + config.selenoidUser() + ":" + config.selenoidPassword() + "@" + config.selenoidUrl();
            Configuration.browserCapabilities = getSelenoidChromeOptions();

        }

    }


    @AfterEach
    void after() {
        clearBrowserCookies();
        clearBrowserLocalStorage();

        AttachManager.takeScreenshot();
        AttachManager.getPageSource();
        AttachManager.getBrowserConsoleLogs();

        if ("remote".equals(config.run())) {
            AttachManager.addVideo();
        }

    }
}
