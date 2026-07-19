package com.demoqa.tests;


import com.demoqa.components.RegistrationPage;
import com.demoqa.components.ResultComponent;
import com.demoqa.components.TestBase;
import org.junit.jupiter.api.Test;


public class PracticeFormPageObject extends TestBase {

    @Test
    void checkingTheFillingOfAllFields() {

        RegistrationPage registrationPage = new RegistrationPage();
        ResultComponent resultComponent = new ResultComponent();

        registrationPage.openPage()
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

        resultComponent.responceTable("Thanks for submitting the form")
                .resultTable("Иван Иванов")
                .resultTable("pochta1234@gmail.com")
                .resultTable("9103456523")
                .resultTable("14 July,1996")
                .resultTable("Maths, Computer Science")
                .resultTable("Sports")
                .resultTable("Дикпик.png")
                .resultTable("Random address")
                .resultTable("NCR Delhi");
    }
}
