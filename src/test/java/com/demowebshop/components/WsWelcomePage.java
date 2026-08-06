package com.demowebshop.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsWelcomePage {

    public static SelenideElement registerButton = $("a.ico-register");
    public static SelenideElement loginButton = $("a.ico-login");
    public static ElementsCollection headerLinks = $$("div.header-links ul li a");
    public static ElementsCollection topMenu = $$("ul.top-menu li a");

    public WsRegistrationPage openRegistraion(){
        registerButton.click();

        return new WsRegistrationPage();
    }
    public LoginPage openLogin(){
        loginButton.click();

        return new LoginPage();
    }
    public WsWelcomePage chekUserLoggedIn(String email){
        headerLinks.get(0).shouldHave(text(email));

        return this;
    }
    public ComputersPage selectingTab(){
        topMenu.get(1).hover();
        $(byText("Desktops")).click();

        return new ComputersPage();
    }
}
