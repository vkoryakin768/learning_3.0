package com.demoqa.tests;

import com.demoqa.components.TestBase;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm extends TestBase {

    @Test
    void checkingTheFillingOfAllFields() {


        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Иван");
        $("#lastName").setValue("Иванов");
        $("#userEmail").setValue("pochta1234@gmail.com");
        $("#gender-radio-3").click();
        $("#userNumber").setValue("9103456523");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1996");
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__day.react-datepicker__day--014").click();
        $("#subjectsInput").setValue("Math");
        $("#react-select-2-option-0").click();
        $("#subjectsInput").setValue("P");
        $("#react-select-2-option-1").click();
        $$("label[for='hobbies-checkbox-1']").findBy(text("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("Дикпик.png");
        $("#currentAddress").setValue("Random address");
        $("#react-select-3-input").setValue("NCR");
        $$("#react-select-3-option-0").findBy(text("NCR")).click();
        $("#react-select-4-input").setValue("D");
        $$("#react-select-4-option-0").findBy(text("Delhi")).click();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("Иван Иванов"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("pochta1234@gmail.com"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("Other"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("9103456523"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("14 July,1996"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("Maths, Computer Science"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("Sports"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("Дикпик.png"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("Random address"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("NCR Delhi"));


    }
}
