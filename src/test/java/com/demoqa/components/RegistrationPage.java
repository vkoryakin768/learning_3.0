package com.demoqa.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    public static SelenideElement firstNameInput = $("#firstName");
    public static SelenideElement lastNameInput = $("#lastName");
    public static SelenideElement userEmailInput = $("#userEmail");
    public static SelenideElement genderInput = $("#gender-radio-3");
    public static SelenideElement userNumberInput = $("#userNumber");
    public static SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    public static SelenideElement yearSelect = $(".react-datepicker__year-select");
    public static SelenideElement monthSelect = $(".react-datepicker__month-select");
    public static SelenideElement daySelect = $(".react-datepicker__day.react-datepicker__day--014");
    public static SelenideElement subjectsInput = $("#subjectsInput");
    public static SelenideElement subjectsClickFirst = $("#react-select-2-option-0");
    public static SelenideElement subjectsClickSecond = $("#react-select-2-option-1");
    public static ElementsCollection hobbiesInput = $$("label[for='hobbies-checkbox-1']");
    public static SelenideElement uploadPictureInput = $("#uploadPicture");
    public static SelenideElement currentAddressInput = $("#currentAddress");
    public static SelenideElement stateInput = $("#react-select-3-input");
    public static ElementsCollection stateClick = $$("#react-select-3-option-0");
    public static SelenideElement cityInput = $("#react-select-4-input");
    public static ElementsCollection cityClick = $$("#react-select-4-option-0");
    public static SelenideElement submitClick = $("#submit");

    public RegistrationPage openPage() {
        open("https://demoqa.com/automation-practice-form");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender() {
        genderInput.click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDayOfBirth(String year, String month) {
        dateOfBirthInput.click();
        yearSelect.selectOption(year);
        monthSelect.selectOption(month);
        daySelect.click();

        return this;
    }

    public RegistrationPage setSubjectsFirst(String value) {
        subjectsInput.setValue(value);
        subjectsClickFirst.click();

        return this;
    }

    public RegistrationPage setSubjectsSecond(String value) {
        subjectsInput.setValue(value);
        subjectsClickSecond.click();

        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbiesInput.findBy(text(value)).click();

        return this;
    }

    public RegistrationPage setUploadPicture(String value) {
        uploadPictureInput.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public RegistrationPage setState(String value) {
        stateInput.setValue(value);
        stateClick.findBy(text(value)).click();

        return this;
    }

    public RegistrationPage setCity(String value, String city) {
        cityInput.setValue(value);
        cityClick.findBy(text(city)).click();

        return this;
    }

    public RegistrationPage submit() {
        submitClick.click();

        return this;
    }

}
