package com.demowebshop.tests;


import com.demowebshop.components.CartPage;
import com.demowebshop.components.ComputersPage;
import com.demowebshop.components.WsWelcomePage;
import com.demowebshop.config.TestBase;
import com.demowebshop.steps.AuthSteps;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.*;
import static com.demowebshop.config.Config.WEB_SHOP_URL;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты корзины")
public class CartTestPageObject extends TestBase {


    private final AuthSteps authSteps = new AuthSteps();
    CartPage cartPage = new CartPage();

    @BeforeEach
    void beforeEach() {

        authSteps.registerNewUser();
    }

    @Test
    @Owner("v.koriakin")
    @Severity(CRITICAL)
    @Link(name = "TASK-02", url = "https://demowebshop.tricentis.com/")
    @DisplayName("Добавление товара в корзину и проверка соответсвия стоимости на странице товара и в корзине")
    void addItemToCartTest() {
        ComputersPage computersPage = open(WEB_SHOP_URL, WsWelcomePage.class)
                .selectingTab()
                .selectionItem();

        String itemName = computersPage.getItemName();
        String itemQuantity = "2";
        int processorListIndex = 0;
        int processorOptionIndex = 1;

        String calculatedPrice = computersPage.calculateTotalPrice(processorListIndex, processorOptionIndex);

        computersPage.selectProcessor(processorListIndex, processorOptionIndex)
                .inputQantity(itemQuantity)
                .addToCartButton()
                .dispayBarNotification()
                .verifyCartQuantity(itemQuantity)
                .openCart()
                .checkItemName(itemName);

        String itemQuantityInCart = cartPage.getQuantityValue();
        assertEquals(itemQuantity, itemQuantityInCart);


        String unitPrice = cartPage.getUnitPrice();
        assertEquals(calculatedPrice, unitPrice);


        cartPage.verifyProductSubtotal(unitPrice, itemQuantity);
    }
}
