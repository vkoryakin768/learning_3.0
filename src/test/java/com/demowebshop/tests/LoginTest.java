package com.demowebshop.tests;

import com.codeborne.selenide.Configuration;
import com.demowebshop.components.WsRegistrationPage;
import com.demowebshop.components.WsWelcomePage;
import net.datafaker.Faker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.*;
import static com.demowebshop.config.Config.WEB_SHOP_REGISTRATION_URL;
import static com.demowebshop.config.Config.WEB_SHOP_URL;

public class LoginTest {

    private static final Faker faker = new Faker();
    private String password;
    private String email;

    @BeforeEach
    void beforeEach() {


        password = faker.harryPotter().character() + faker.number().positive();
        email = faker.internet().emailAddress();

        open(WEB_SHOP_REGISTRATION_URL, WsRegistrationPage.class)
                .register(
                        faker.name().name(),
                        faker.name().lastName(),
                        email,
                        password)
                .checkUserLoggedIn(email);


        clearBrowserCookies();
        clearBrowserLocalStorage();

    }

    @Test
    void succesLoginTest() {

        open(WEB_SHOP_URL, WsWelcomePage.class)
                .openLogin()
                .checkloginPageOpened()
                .enterLogin(email)
                .enterPassword(password)
                .checkRememberMe()
                .submitLogin()
                .chekUserLoggedIn(email);


    }
}

