package com.demowebshop.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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
    public static SelenideElement fieldValidation = $("span.field-validation-error");

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

    @Step("Проверить открытие страницы регистрации")
    public WsRegistrationPage verifyRegistrationOpened() {
        pageTitle.shouldHave(text("Register"));

        return this;
    }

    @Step("Выбрать мужской пол")
    public WsRegistrationPage selectGender() {
        maleGenderRadio.click();

        return this;
    }

    @Step("Ввести имя: {firstName}")
    public WsRegistrationPage enterFirsName(String fistName) {
        inputFirstName.setValue(fistName);

        return this;
    }

    @Step("Ввести фамилию: {lastName}")
    public WsRegistrationPage enterLastName(String lastName) {
        inputLastName.setValue(lastName);

        return this;
    }

    @Step("Ввести электронную почту: {email}")
    public WsRegistrationPage enterEmail(String email) {
        inputEmail.setValue(email);

        return this;
    }

    @Step("Ввести пароль: {password}")
    public WsRegistrationPage enterPassword(String password) {
        inputPassword.setValue(password);

        return this;
    }

    @Step("Ввести пароль повторно: {confirmPassword}")
    public WsRegistrationPage enterConfirmPassword(String confirmPassword) {
        inputConfirmPassword.setValue(confirmPassword);

        return this;
    }

    @Step("Нажать кнопку регистрации")
    public WsRegistrationPage submitRegisration() {
        registerButtton.click();

        return this;
    }

    @Step("Проверить что появилось сообщение с ошибкой валидации подтверждения пароля")
    public WsRegistrationPage verefyConfirmPasswordValidationErrorAppear() {
        fieldValidation.shouldBe(visible);

        return this;
    }

    @Step("Проверить что пользователь успешно зарегестрировался")
    public WsRegistrationPage checkRegistrationCompleted() {
        resultText.shouldHave(text("Your registration completed"));

        return this;
    }

    @Step("Проверить отображение электронной почты в заголовках страницы: {email}")
    public WsRegistrationPage checkUserLoggedIn(String email) {
        headerLinks.get(0).shouldHave(text(email));

        return this;
    }
}
