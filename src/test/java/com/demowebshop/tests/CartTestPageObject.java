package com.demowebshop.tests;


import com.demowebshop.components.CartPage;
import com.demowebshop.components.ComputersPage;
import com.demowebshop.components.WsWelcomePage;
import com.demowebshop.steps.AuthSteps;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.*;
import static com.demowebshop.config.Config.WEB_SHOP_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTestPageObject {

    private static final Faker faker = new Faker();
    private final AuthSteps authSteps = new AuthSteps();
    CartPage cartPage = new CartPage();

    @BeforeEach
    void beforeEach() {

        authSteps.registerNewUser();
    }

    @Test
    void addItemToCartTest() {
        ComputersPage computersPage = open(WEB_SHOP_URL, WsWelcomePage.class)
                .selectingTab()
                .selectionItem();

        String itemName = computersPage.getItemName();
        String itemQuantity = "2";


        computersPage.selectProcessor(0, 1)
        .inputQantity(itemQuantity)
                .addToCartButton()
                .dispayBarNotification()
                .verifyCartQuantity(itemQuantity)
                .openCart()
                .checkItemName(itemName);

        String itemQuantityInCart = cartPage.getQuantityValue();
        assertEquals(itemQuantity, itemQuantityInCart);

        String unitPrice = cartPage.getUnitPrice();

        cartPage.verifyProductSubtotal(unitPrice, itemQuantity);


    }
}
