package com.demowebshop.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static SelenideElement pageTitle = $("div.page-title h1");
    public static SelenideElement inputEmail = $("input#Email");
    public static SelenideElement inputPassword = $("input#Password");
    public static SelenideElement inputRememberMe = $("input#RememberMe");
    public static SelenideElement inputLoginButton = $("input.login-button");

    public LoginPage checkloginPageOpened() {
        pageTitle.shouldHave(text("Welcome, Please Sign In!"));

        return this;
    }

    public LoginPage enterLogin(String email) {
        inputEmail.setValue(email);

        return this;
    }

    public LoginPage enterPassword(String password) {
        inputPassword.setValue(password);

        return this;
    }

    public LoginPage checkRememberMe() {
        inputRememberMe.click();

        return this;
    }

    public WsWelcomePage submitLogin() {
        inputLoginButton.click();

        return new WsWelcomePage();
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