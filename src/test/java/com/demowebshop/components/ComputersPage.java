package com.demowebshop.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class ComputersPage {

    private final ElementsCollection productGrid = $$("div.product-grid div");
    private final SelenideElement itemName = $("[itemprop=name]");
    private final SelenideElement itemPrice = $("[itemprop=price]");
    private final ElementsCollection selectOption = $$("dl dd ul");
    private final SelenideElement inputQty = $("input.qty-input");
    private final SelenideElement addButton = $("input.add-to-cart-button");
    private final SelenideElement barNotification = $("div.bar-notification.success");

    @Step("Выбор товара")
    public ComputersPage selectionItem() {
        productGrid.get(0).click();
        return this;
    }

    @Step("Получаем наименование товара")
    public String getItemName() {
        return itemName.getText();
    }

    @Step("Получаем цену товара")
    public String getItemPrice() {
        return itemPrice.getText();
    }

    public String getProcessorSurcharge(int listIndex, int optionIndex) {
        String label = selectOption.get(listIndex).$$("li label").get(optionIndex).getText().trim();


        if (label.contains("[") && label.contains("]")) {

            String surcharge = label.replaceAll(".*\\[(.*)\\].*", "$1");

            return surcharge.replace("+", "");
        }


        return "0";
    }

    @Step("Считаем итоговую цену товара")
    public String calculateTotalPrice(int processorListIndex, int processorOptionIndex) {
        String basePrice = getItemPrice();
        String surcharge = getProcessorSurcharge(processorListIndex, processorOptionIndex);

        float totalPrice = Float.parseFloat(basePrice) + Float.parseFloat(surcharge);

        return String.format(Locale.US, "%.2f", totalPrice);
    }

    @Step("Выбор процессора из списка {listIndex}, вариант {optionIndex}")
    public ComputersPage selectProcessor(int listIndex, int optionIndex) {
        selectOption.get(listIndex).$$("li input").get(optionIndex).click();

        return this;
    }

    @Step("Ввести количество товара: {itemQuantity}")
    public ComputersPage inputQantity(String itemQuantity) {
        inputQty.setValue(itemQuantity);
        return this;
    }

    @Step("Добавляем товар в корзину")
    public ComputersPage addToCartButton() {
        addButton.click();
        return this;
    }

    @Step("Проверям отображения баннера об успешном добавлении товара в корзину")
    public CartPage dispayBarNotification() {
        barNotification.shouldBe(visible);
        return new CartPage();
    }


}
