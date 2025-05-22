package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    private Feline feline;

    @Test
    void testDoesHaveManeForMale() throws Exception {
        Lion lion = new Lion("Самец", feline);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    void testDoesHaveManeForFemale() throws Exception {
        Lion lion = new Lion("Самка", feline);
        assertFalse(lion.doesHaveMane());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Самец", "Самка"})
    void testGetKittens(String sex) throws Exception {
        Lion lion = new Lion(sex, feline);
        when(feline.getKittens()).thenReturn(1);
        assertEquals(1, lion.getKittens());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Самец", "Самка"})
    void testGetFood(String sex) throws Exception {
        Lion lion = new Lion(sex, feline);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);
        assertEquals(expectedFood, lion.getFood());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Неизвестный", "Другое"})
    void testInvalidSex(String invalidSex) {
        Exception exception = assertThrows(Exception.class, () -> new Lion(invalidSex, feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }
}