package com.demoqa.booking.config;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class InvalidCredentials {
    public static Stream<Arguments> invalidCredentials() {
        return Stream.of(
                Arguments.of("admin", "password"),
                Arguments.of("admin1", "password123"),
                Arguments.of("admin", " "),
                Arguments.of("  ", "password123")
        );
    }

    public static Stream<Arguments> invalidCredentialsEmptyBody() {
        return Stream.of(
                Arguments.of("{}"),
                Arguments.of("")
        );
    }
}
