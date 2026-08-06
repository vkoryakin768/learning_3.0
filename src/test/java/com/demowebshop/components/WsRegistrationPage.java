package com.demowebshop.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsRegistrationPage {

    public SelenideElement pageTitle = $("div.page-title");
    public SelenideElement maleGenderRadio = $("input#gender-male");
    public SelenideElement inputFirstName = $("input#FirstName");
    public SelenideElement inputLastName = $("input#LastName");
    public SelenideElement inputEmail = $("input#Email");
    public SelenideElement inputPassword = $("input#Password");
    public SelenideElement inputConfirmPassword = $("input#ConfirmPassword");
    public SelenideElement registerButtton = $("input#register-button");
    public SelenideElement resultText = $("div.result");
    public ElementsCollection headerLinks = $$("div.header-links ul li a");

    public WsRegistrationPage register(String firstName, String lastName, String email, String password) {

        verifyRegistrationOpened()
                .selectGender()
                .enterFirsName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .submitRegisration()
                .checkRegistrationCompleted()
                ;
        return this;
    }

    public WsRegistrationPage verifyRegistrationOpened() {
        pageTitle.shouldHave(text("Register"));

        return this;
    }

    public WsRegistrationPage selectGender() {
        maleGenderRadio.click();

        return this;
    }

    public WsRegistrationPage enterFirsName(String fistName) {
        inputFirstName.setValue(fistName);

        return this;
    }

    public WsRegistrationPage enterLastName(String lastName) {
        inputLastName.setValue(lastName);

        return this;
    }

    public WsRegistrationPage enterEmail(String email) {
        inputEmail.setValue(email);

        return this;
    }

    public WsRegistrationPage enterPassword(String password) {
        inputPassword.setValue(password);

        return this;
    }

    public WsRegistrationPage enterConfirmPassword(String confirmPassword) {
        inputConfirmPassword.setValue(confirmPassword);

        return this;
    }

    public WsRegistrationPage submitRegisration() {
        registerButtton.click();

        return this;
    }

    public WsRegistrationPage checkRegistrationCompleted() {
        resultText.shouldHave(text("Your registration completed"));

        return this;
    }

    public WsRegistrationPage checkUserLoggedIn(String email) {
        headerLinks.get(0).shouldHave(text(email));

        return this;
    }
}
