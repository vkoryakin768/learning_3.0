package ru.bulgakov.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LavaTopPage {

    public static SelenideElement priceElement = $("h3[data-at='H3']");

    public LavaTopPage checkPrice(String price) {
        priceElement.shouldHave(text(price));

        return this;

    }
}
