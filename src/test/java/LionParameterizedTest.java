package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionParameterizedTest {

    @Mock
    private Feline feline;

    private static Stream<Arguments> provideSexAndManeStatus() {
        return Stream.of(
                Arguments.of("Самец", true),
                Arguments.of("Самка", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSexAndManeStatus")
    void testDoesHaveMane(String sex, boolean expectedManeStatus) throws Exception {
        Lion lion = new Lion(sex, feline);
        assertEquals(expectedManeStatus, lion.doesHaveMane());
    }
}