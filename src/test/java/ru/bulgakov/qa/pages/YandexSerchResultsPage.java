package ru.bulgakov.qa.pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class YandexSerchResultsPage {

    public YandexSerchResultsPage openLink(String webSiteName) {
        $(byText(webSiteName)).click();

        return this;
    }

    public IvanBulgakovQaPage switchPage(int numberPage){
        switchTo().window(numberPage);

        return new IvanBulgakovQaPage();
    }
}
