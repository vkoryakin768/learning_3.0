package com.demoqa.booking.config;

import com.demoqa.booking.dto.CreateBookingDto;
import io.restassured.specification.Argument;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class InvalidTestScenarios {
    public static Stream<Arguments> invalidScenarios() {
        return Stream.of(
                Arguments.of("Без обязательного поля firstname", new CreateBookingDto(null, "Ivanov", 1500, false, new CreateBookingDto.BookingDates("2018-01-01", "2019-01-01"), "Breakfast")),
                Arguments.of("Без обязательного поля lastname", new CreateBookingDto("Oleg", null, 1500, false, new CreateBookingDto.BookingDates("2018-01-01", "2019-01-01"), "Breakfast")),
                Arguments.of("Пустое тело запроса", "")
        );
    }
}
