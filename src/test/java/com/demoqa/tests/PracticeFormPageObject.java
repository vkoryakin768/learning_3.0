package com.demoqa.tests;


import com.demoqa.components.RegistrationPage;
import com.demoqa.components.ResultComponent;
import com.demoqa.components.TestBase;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;


public class PracticeFormPageObject extends TestBase {

    @Test
    void checkingTheFillingOfAllFields() {

        open("https://demoqa.com/automation-practice-form", RegistrationPage.class)
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setUserEmail("pochta1234@gmail.com")
                .setGender()
                .setUserNumber("9103456523")
                .setDayOfBirth("1996", "July")
                .setSubjectsFirst("Math")
                .setSubjectsSecond("P")
                .setHobbies("Sports")
                .setUploadPicture("Дикпик.png")
                .setCurrentAddress("Random address")
                .setState("NCR")
                .setCity("D", "Delhi")
                .submit();

        // Каждая проверка привязана к своей строке таблицы:
        // сначала находим строку по подписи слева, потом сверяем значение справа
        new ResultComponent().responseTable("Thanks for submitting the form")
                .checkStudentName("Иван", "Иванов")
                .checkStudentEmail("pochta1234@gmail.com")
                .checkGender("Other")
                .checkMobile("9103456523")
                .checkDateOfBirth("14", "July", "1996")
                .checkSubjects("Maths", "Computer Science")
                .checkHobbies("Sports")
                .checkPicture("Дикпик.png")
                .checkAddress("Random address")
                .checkStateAndCity("NCR", "Delhi");
    }
}
