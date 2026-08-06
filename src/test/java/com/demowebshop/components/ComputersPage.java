package com.demowebshop.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class ComputersPage {

    public static ElementsCollection productGrid = $$("div.product-grid div");
    public static SelenideElement itemName = $("[itemprop=name]");
    public static SelenideElement itemPrice = $("[itemprop=price]");
    public static ElementsCollection selectOption = $$("dl dd ul");
    public static SelenideElement inputQty = $("input.qty-input");
    public static SelenideElement addButton = $("input.add-to-cart-button");
    public static SelenideElement barNotification = $("div.bar-notification.success");

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
