package com.demoqa.booking;

import com.demoqa.booking.dto.AuthRequest;
import com.demoqa.booking.dto.AuthResponse;
import com.demoqa.booking.dto.CreateBookingDto;
import com.demoqa.booking.dto.CreateBookingResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingTests {
    private static final String BOOKING_URL = "https://restful-booker.herokuapp.com";

    private static CreateBookingDto buildBookingRequest() {
        return CreateBookingDto.builder()
                .firstname("Oleg")
                .lastname("Ivanov")
                .totalprice(1500)
                .depositpaid(false)
                .bookingdates(CreateBookingDto.BookingDates.builder()
                        .checkin("2018-01-01")
                        .checkout("2019-01-01")
                        .build())
                .additionalneeds("Breakfast")
                .build();

    }


    @BeforeAll
    static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.filters(new AllureRestAssured());
    }

    @DisplayName("Успешная авторизация")
    @Test
    void authPositiveTest() {
        String user = "admin";
        String password = "password123";


        AuthResponse resp = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest(user, password))
                .when()
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);

        assertThat(resp.getToken()).isNotNull();
    }

    @DisplayName("Успешное создание бронирования")
    @Test
    void CreatePositiveBookingTest() {

        CreateBookingResponse resp = given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(buildBookingRequest())
                .when()
                .post(BOOKING_URL + "/booking")
                .then()
                .statusCode(200)
                .extract().as(CreateBookingResponse.class);
        assertThat(resp.getBookingid()).isNotNull();
        assertThat(resp.getBooking().getBookingdates().getCheckout()).isEqualTo("2019-01-01");

    }

    @ParameterizedTest(name = "Неуспешная авторизация c: [{0}] и [{1}] ")
    @MethodSource("com.demoqa.booking.config.InvalidCredentials#invalidCredentials")
    void authNegativeTest(String user, String password) {

        AuthResponse resp = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest(user, password))
                .when()
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);

        assertThat(resp.getReason()).isEqualTo("Bad credentials");
        assertThat(resp.getToken()).isNull();
    }

    @ParameterizedTest(name = "Неуспешная авторизация с пустым body: [{0}]")
    @MethodSource("com.demoqa.booking.config.InvalidCredentials#invalidCredentialsEmptyBody")
    void authEmptyBodyTest(String body) {

        AuthResponse resp = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);

        assertThat(resp.getReason()).isEqualTo("Bad credentials");
        assertThat(resp.getToken()).isNull();
    }

    @ParameterizedTest(name = "Неуспешное создание бронирования: {0}")
    @MethodSource("com.demoqa.booking.config.InvalidTestScenarios#invalidScenarios")
    void CreateNegativeBookingTestWithoutLastName(String scenarioName, Object requestBody) {

        String resp = given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(requestBody)
                .when()
                .post(BOOKING_URL + "/booking")
                .then()
                .statusCode(500)
                .extract().asString();

        assertThat(resp).isEqualTo("Internal Server Error");

    }

}
