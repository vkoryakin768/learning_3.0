package com.demowebshop.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static SelenideElement pageTitle = $("div.page-title h1");
    public static SelenideElement inputEmail = $("input#Email");
    public static SelenideElement inputPassword = $("input#Password");
    public static SelenideElement inputRememberMe = $("input#RememberMe");
    public static SelenideElement inputLoginButton = $("input.login-button");
    public static SelenideElement fieldValidation = $("span.field-validation-error");

    @Step("Проверка открытия страницы авторизации")
    public LoginPage checkloginPageOpened() {
        pageTitle.shouldHave(text("Welcome, Please Sign In!"));

        return this;
    }

    @Step("Ввести электронную почту {email}")
    public LoginPage enterLogin(String email) {
        inputEmail.setValue(email);

        return this;
    }

    @Step("Ввести пароль {password}")
    public LoginPage enterPassword(String password) {
        inputPassword.setValue(password);

        return this;
    }

    @Step("Проставление отметки 'Remember me?'")
    public LoginPage checkRememberMe() {
        inputRememberMe.click();

        return this;
    }

    @Step("Подтвердить аторизацию")
    public WsWelcomePage submitLogin() {
        inputLoginButton.click();

        return new WsWelcomePage();
    }

    @Step("Проверить что появилось сообщение с ошибкой валидации почты")
    public LoginPage verefyEmailValidationErrorAppear() {
        fieldValidation.shouldBe(visible);

        return this;
    }

    /*public WsWelcomePage login(String email, String password, boolean rememberMe){
        enterEmail(email)
                .enterPassword(password);
        if(rememberMe){
            checkRememberMe();
        }
        return submitLogin();
    }*/


}