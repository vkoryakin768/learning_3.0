package ru.bulgakov.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class YandexSerchPage {

    public static SelenideElement serchInput = $("#text");
    public static SelenideElement submitButton = $("[type=submit]");


    public YandexSerchPage serch(String value) {
        serchInput.setValue(value);

        return this;
    }

    public YandexSerchResultsPage serchClick() {
        submitButton.click();

        return new YandexSerchResultsPage();
    }
}
