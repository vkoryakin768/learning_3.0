package ru.bulgakov.qa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SerchTest {

    @Test
    void checkingThePaymentAmountOf47000Rubles() {

        Configuration.pageLoadTimeout = 100000;
        Configuration.timeout = 100000;

        open("https://ya.ru");
        $("#text").setValue("bulgakov qa");
        $("[type=submit]").click();
        $(byText("ivanbulgakovqa.ru")).click();
        switchTo().window(1);
        $$(".t-menu__list li").get(4).click();
        $$("span.tn-atom__button-text").findBy(text("Хочу вкатиться в QA")).click();
        $$("span.tn-atom__button-text").findBy(text("Бегу оплачивать")).click();
        switchTo().window(2);

        $("h3[data-at='H3']").shouldHave(text("₽ 47 000.00"));

    }
}
