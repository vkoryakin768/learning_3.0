package com.demowebshop.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


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

    public ComputersPage selectionItem() {
        productGrid.get(0).click();
        return this;
    }

    public String getItemName() {
        return itemName.getText();
    }

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

    public String calculateTotalPrice(int processorListIndex, int processorOptionIndex) {
        String basePrice = getItemPrice();
        String surcharge = getProcessorSurcharge(processorListIndex, processorOptionIndex);

        float totalPrice = Float.parseFloat(basePrice) + Float.parseFloat(surcharge);

        return String.format(Locale.US, "%.2f", totalPrice);
    }

    public ComputersPage selectProcessor(int listIndex, int optionIndex) {
        selectOption.get(listIndex).$$("li input").get(optionIndex).click();

        return this;
    }

    public ComputersPage inputQantity(String itemQuantity) {
        inputQty.setValue(itemQuantity);
        return this;
    }

    public ComputersPage addToCartButton() {
        addButton.click();
        return this;
    }

    public CartPage dispayBarNotification() {
        barNotification.shouldBe(visible);
        return new CartPage();
    }


}
