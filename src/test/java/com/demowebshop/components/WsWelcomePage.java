package com.demowebshop.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsWelcomePage {

    private final SelenideElement registerButton = $("a.ico-register");
    private final SelenideElement loginButton = $("a.ico-login");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");
    private final ElementsCollection topMenu = $$("ul.top-menu li a");

    @Step("Открытие страницы регистрации")
    public WsRegistrationPage openRegistraion(){
        registerButton.click();

        return new WsRegistrationPage();
    }
    @Step("Открытие страницы авторизации")
    public LoginPage openLogin(){
        loginButton.click();

        return new LoginPage();
    }
    @Step("Проверить отображение электронной почты в заголовках страницы: {email}")
    public WsWelcomePage chekUserLoggedIn(String email){
        headerLinks.get(0).shouldHave(text(email));

        return this;
    }
    @Step("Выбор подкатегории 'Decktop' из меню")
    public ComputersPage selectingTab(){
        topMenu.get(1).hover();
        $(byText("Desktops")).click();

        return new ComputersPage();
    }
}
