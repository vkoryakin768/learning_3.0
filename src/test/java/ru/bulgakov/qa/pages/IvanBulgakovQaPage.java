package ru.bulgakov.qa.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;

public class IvanBulgakovQaPage {

    public static ElementsCollection serchHead = $$(".t-menu__list li");
    public static ElementsCollection textButton = $$("span.tn-atom__button-text");


    public IvanBulgakovQaPage serchCost(int number) {
        serchHead.get(number).click();

        return this;
    }

    public IvanBulgakovQaPage selectJoinQACourse(String value){
        textButton.findBy(text(value)).click();

        return this;
    }

    public IvanBulgakovQaPage proceedToPayment(String value){
        textButton.findBy(text(value)).click();

        return this;
    }
    public LavaTopPage switchPage(int numberPage){
        switchTo().window(numberPage);

        return new LavaTopPage();
    }
}
