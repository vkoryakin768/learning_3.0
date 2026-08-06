package com.demowebshop.tests;

import com.codeborne.selenide.Configuration;
import com.demowebshop.components.WsWelcomePage;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.open;
import static com.demowebshop.config.Config.WEB_SHOP_URL;

public class RegistrationTest {

    private static final Faker faker = new Faker();

    @Test
    void registrationTest() {


        String password = faker.harryPotter().character() + faker.number().positive();
        String email = faker.internet().emailAddress();

        open(WEB_SHOP_URL , WsWelcomePage.class)
                .openRegistraion()
                .verifyRegistrationOpened()
                .selectGender()
                .enterFirsName(faker.name().name())
                .enterLastName(faker.name().lastName())
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .submitRegisration()
                .checkRegistrationCompleted()
                .checkUserLoggedIn(email);


    }
}
