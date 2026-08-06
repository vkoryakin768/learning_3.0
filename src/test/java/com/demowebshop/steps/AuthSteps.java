package com.demowebshop.steps;

import com.demowebshop.components.WsRegistrationPage;
import net.datafaker.Faker;

import static com.codeborne.selenide.Selenide.open;
import static com.demowebshop.config.Config.WEB_SHOP_REGISTRATION_URL;

public class AuthSteps {

    private static final Faker faker = new Faker();

    public void registerNewUser(){
        open(WEB_SHOP_REGISTRATION_URL, WsRegistrationPage.class)
                .register(
                        faker.name().name(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.harryPotter().character() + faker.number().positive());

    }
}
