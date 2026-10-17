package com.demowebshop.tests;


import com.demowebshop.components.WsWelcomePage;
import com.demowebshop.config.TestBase;
import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.open;
import static com.demowebshop.config.Config.WEB_SHOP_URL;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@DisplayName("Тесты на регистрацию")
public class RegistrationTest extends TestBase {

    private static final Faker faker = new Faker();

    @Test
    @Owner("v.koriakin")
    @Tags({@Tag("UI"), @Tag("positive")})
    @Severity(CRITICAL)
    @Epic("Авторизация")
    @Feature("Регистрация")
    @Story("Регистарция нового пользователя")
    @Link(name = "TASK-120", url = "https://demowebshop.tricentis.com/")
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Создаем нового пользователя со случайными данными через интерфейс")
    void successfulregistrationTest() {


        String password = faker.harryPotter().character() + faker.number().positive();
        String email = faker.internet().emailAddress();

        open(WEB_SHOP_URL, WsWelcomePage.class)
                .openRegistraion()
                .verifyRegistrationOpened()
                .selectGender()
                .enterFirsName(faker.name().name())
                .enterLastName(faker.name().lastName())
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .submitRegisration()
                .checkRegistrationCompleted()
                .checkUserLoggedIn(email);


    }

    @Test
    @Owner("v.koriakin")
    @Tags({@Tag("UI"), @Tag("negative")})
    @Severity(CRITICAL)
    @Epic("Авторизация")
    @Feature("Регистрация")
    @Story("Регистарция нового пользователя")
    @Link(name = "TASK-120", url = "https://demowebshop.tricentis.com/")
    @DisplayName("Неуспешная регистрация нового пользователя")
    @Description("Создаем нового пользователя c вводом неверного пароля подтверждения")
    void unsuccessfulRegistrationTest() {

        String password = faker.harryPotter().character() + faker.number().positive();
        String email = faker.internet().emailAddress();

        open(WEB_SHOP_URL, WsWelcomePage.class)
                .openRegistraion()
                .verifyRegistrationOpened()
                .selectGender()
                .enterFirsName(faker.name().name())
                .enterLastName(faker.name().lastName())
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword("qwerty1234")
                .submitRegisration()
                .verefyConfirmPasswordValidationErrorAppear();
    }
}
