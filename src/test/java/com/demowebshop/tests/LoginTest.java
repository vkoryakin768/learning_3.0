package com.demowebshop.tests;


import com.demowebshop.components.LoginPage;
import com.demowebshop.components.WsRegistrationPage;
import com.demowebshop.components.WsWelcomePage;
import com.demowebshop.config.TestBase;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import net.datafaker.Faker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static com.codeborne.selenide.Selenide.*;
import static com.demowebshop.config.Config.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@DisplayName("Тесты авторизации")
public class LoginTest extends TestBase {

    private static final Faker faker = new Faker();
    private String password;
    private String email;

    @Nested
    public class PositiveTests{
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
        @Owner("v.koriakin")
        @Severity(CRITICAL)
        @Link(name = "TASK-01", url = "https://demowebshop.tricentis.com/")
        @DisplayName("Успешная авторизация")
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


    @ParameterizedTest(name = "Авторизация с невалидным email: {0}")
    @CsvFileSource(resources = "/email.csv")
    void invalidEmailLoginTest(String email) {
        open(WEB_SHOP_LOGIN_URL, LoginPage.class)
                .enterLogin(email)
                .enterPassword("password")
                .verefyEmailValidationErrorAppear()
                .submitLogin();
    }
}

