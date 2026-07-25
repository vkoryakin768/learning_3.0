package com.demoqa.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ResultComponent {

    // Заголовок модалки "Thanks for submitting the form"
    public static SelenideElement tableResponse = $("#example-modal-sizes-title-lg");
    // Сама таблица с результатами
    public static SelenideElement tableResult = $(".table.table-dark.table-striped.table-bordered.table-hover");
    // Все строки таблицы. Каждая строка имеет вид: <tr><td>Подпись</td><td>Значение</td></tr>
    // Например: <tr><td>Student Email</td><td>pochta1234@gmail.com</td></tr>
    public static ElementsCollection resultRows = tableResult.$$("tbody tr");

    // Подписи строк таблицы (левая колонка) - ровно так, как они написаны на странице.
    // Enum нужен, чтобы в тесте нельзя было опечататься в строке "Date of Birth"
    // и чтобы IDE подсказывала список доступных строк.
    public enum ResultRow {
        STUDENT_NAME("Student Name"),
        STUDENT_EMAIL("Student Email"),
        GENDER("Gender"),
        MOBILE("Mobile"),
        DATE_OF_BIRTH("Date of Birth"),
        SUBJECTS("Subjects"),
        HOBBIES("Hobbies"),
        PICTURE("Picture"),
        ADDRESS("Address"),
        STATE_AND_CITY("State and City");

        private final String label;

        ResultRow(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    // Проверяем, что модалка вообще открылась и заголовок в ней тот, что ждем
    public ResultComponent responseTable(String value) {
        tableResponse.shouldBe(visible).shouldHave(exactText(value));

        return this;
    }

    // Главный метод. Раньше мы проверяли "где-то в таблице есть такой текст" -
    // тест проходил, даже если телефон случайно попал бы в строку с адресом.
    // Теперь ищем конкретную строку и проверяем значение именно в ней.
    public ResultComponent checkResultRow(ResultRow row, String expectedValue) {
        // 1. Из всех строк таблицы берем ту, в тексте которой есть нужная подпись.
        //    findBy(text(...)) - это фильтр коллекции: Selenide перебирает <tr> и берет первый подходящий.
        //    В CSS искать по тексту нельзя, поэтому текст проверяется условием, а не селектором.
        SelenideElement tableRow = resultRows.findBy(text(row.getLabel()));

        // 2. Страхуемся: первая ячейка строки должна быть ровно нашей подписью.
        //    text() ищет подстроку, exactText() - полное совпадение.
        //    Если верстку поменяют и мы поймаем не ту строку - тест упадет здесь, а не молча пройдет.
        tableRow.$("td:nth-child(1)").shouldHave(exactText(row.getLabel()));

        // 3. Вторая ячейка той же строки - это значение. Его и сверяем с ожидаемым.
        tableRow.$("td:nth-child(2)").shouldHave(exactText(expectedValue));

        return this;
    }

    // Ниже - обертки над checkResultRow под каждую строку таблицы.
    // Они нужны, чтобы тест читался как текст и чтобы формат значения (пробелы, запятые)
    // был спрятан здесь, а не размазан по тестам.

    // На странице имя и фамилия склеиваются через пробел: "Иван Иванов"
    public ResultComponent checkStudentName(String firstName, String lastName) {
        return checkResultRow(ResultRow.STUDENT_NAME, firstName + " " + lastName);
    }

    public ResultComponent checkStudentEmail(String email) {
        return checkResultRow(ResultRow.STUDENT_EMAIL, email);
    }

    public ResultComponent checkGender(String gender) {
        return checkResultRow(ResultRow.GENDER, gender);
    }

    public ResultComponent checkMobile(String mobile) {
        return checkResultRow(ResultRow.MOBILE, mobile);
    }

    // Дата выводится в формате "14 July,1996" - пробел после дня, запятая без пробела перед годом
    public ResultComponent checkDateOfBirth(String day, String month, String year) {
        return checkResultRow(ResultRow.DATE_OF_BIRTH, day + " " + month + "," + year);
    }

    // Предметов может быть несколько, на странице они через запятую с пробелом: "Maths, Computer Science".
    // Многоточие (varargs) позволяет передать сколько угодно: checkSubjects("Maths", "Computer Science")
    public ResultComponent checkSubjects(String... subjects) {
        return checkResultRow(ResultRow.SUBJECTS, String.join(", ", subjects));
    }

    // Хобби собираются так же, как предметы: "Sports, Music"
    public ResultComponent checkHobbies(String... hobbies) {
        return checkResultRow(ResultRow.HOBBIES, String.join(", ", hobbies));
    }

    // В таблице показывается только имя файла, без пути
    public ResultComponent checkPicture(String fileName) {
        return checkResultRow(ResultRow.PICTURE, fileName);
    }

    public ResultComponent checkAddress(String address) {
        return checkResultRow(ResultRow.ADDRESS, address);
    }

    // Штат и город идут в одной строке через пробел: "NCR Delhi"
    public ResultComponent checkStateAndCity(String state, String city) {
        return checkResultRow(ResultRow.STATE_AND_CITY, state + " " + city);
    }
}
