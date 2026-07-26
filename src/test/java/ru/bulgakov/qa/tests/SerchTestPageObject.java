package ru.bulgakov.qa.tests;


import com.demoqa.components.TestBase;
import org.junit.jupiter.api.Test;
import ru.bulgakov.qa.pages.YandexSerchPage;

import static com.codeborne.selenide.Selenide.*;


public class SerchTestPageObject extends TestBase {

    @Test
    void checkingThePaymentAmountOf47000Rubles() {

        open("https://ya.ru", YandexSerchPage.class)
                .serch("bulgakov qa")
                .serchClick()
                .openLink("ivanbulgakovqa.ru")
                .switchPage(1)
                .serchCost(4)
                .selectJoinQACourse("Хочу вкатиться в QA")
                .proceedToPayment("Бегу оплачивать")
                .switchPage(2)
                .checkPrice("₽ 47 000.00");
    }
}
