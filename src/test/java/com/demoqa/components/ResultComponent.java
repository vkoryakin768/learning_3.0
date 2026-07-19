package com.demoqa.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ResultComponent {
    public static SelenideElement tableResponse = $("#example-modal-sizes-title-lg");
    public static SelenideElement tableResult = $(".table.table-dark.table-striped.table-bordered.table-hover");

    public ResultComponent responceTable(String value) {
        tableResponse.shouldHave(text(value));

        return this;
    }

    public ResultComponent resultTable(String value) {
        tableResult.shouldHave(text(value));

        return this;
    }
}
