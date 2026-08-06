package com.demowebshop.components;

import com.codeborne.selenide.SelenideElement;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {

    public static SelenideElement cartQuantityBadge = $("span.cart-qty");
    public static SelenideElement cartIcon = $("a.ico-cart");
    public static SelenideElement productName = $("a.product-name");
    public static SelenideElement quantityInput = $("input.qty-input");
    public static SelenideElement productSubtotal = $("span.product-subtotal");
    public static SelenideElement productUnitPrice = $("span.product-unit-price");

    public CartPage verifyCartQuantity(String itemQuantity) {
        cartQuantityBadge.shouldHave(text("(" + itemQuantity + ")"));
        return this;
    }

    public CartPage openCart() {
        cartIcon.click();
        return this;
    }

    public CartPage checkItemName(String itemName) {
        productName.shouldHave(text(itemName));
        return this;
    }

    public String getQuantityValue() {
        return quantityInput.getAttribute("value");
    }

    public String getUnitPrice() {
        return productUnitPrice.getText();
    }

    public CartPage verifyProductSubtotal(String itemPrice, String itemQuantity) {

        float expectedTotal = Float.parseFloat(itemPrice) * Float.parseFloat(itemQuantity);

        String expectedText = String.format(Locale.US, "%.2f", expectedTotal);

        productSubtotal.shouldHave(text(expectedText));
        return this;
    }
}
