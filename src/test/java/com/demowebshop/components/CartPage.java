package com.demowebshop.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {

    private final SelenideElement cartQuantityBadge = $("span.cart-qty");
    private final SelenideElement cartIcon = $("a.ico-cart");
    private final SelenideElement productName = $("a.product-name");
    private final SelenideElement quantityInput = $("input.qty-input");
    private final SelenideElement productSubtotal = $("span.product-subtotal");
    private final SelenideElement productUnitPrice = $("span.product-unit-price");

    @Step("Проверить количество добавленного товара в корзину: {itemQuantity}")
    public CartPage verifyCartQuantity(String itemQuantity) {
        cartQuantityBadge.shouldHave(text("(" + itemQuantity + ")"));
        return this;
    }

    @Step("Открытие страницы корзины")
    public CartPage openCart() {
        cartIcon.click();
        return this;
    }

    @Step("Проверить наименование товара: {itemName}")
    public CartPage checkItemName(String itemName) {
        productName.shouldHave(text(itemName));
        return this;
    }

    @Step("Получить количество товара из корзины")
    public String getQuantityValue() {
        return quantityInput.getAttribute("value");
    }

    @Step("Получаем цену за еденицу товара")
    public String getUnitPrice() {
        return productUnitPrice.getText();
    }

    @Step("Проверяем, что сумма по товару равна цене за единицу × количество")
    public CartPage verifyProductSubtotal(String itemPrice, String itemQuantity) {

        float expectedTotal = Float.parseFloat(itemPrice) * Float.parseFloat(itemQuantity);

        String expectedText = String.format(Locale.US, "%.2f", expectedTotal);

        productSubtotal.shouldHave(text(expectedText));
        return this;
    }
}
