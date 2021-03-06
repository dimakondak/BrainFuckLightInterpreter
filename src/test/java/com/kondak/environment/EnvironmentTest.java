package com.kondak.environment;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnvironmentTest {
    private static final int MAX_SIZE = 30_000;
    private static final int MAX_VALUE = 65_535;
    private static Environment testInstance;

    @BeforeAll
    public static void init() {
        testInstance = Environment.getInstance();
    }

    @Test
    void checkSingleton() {
        Environment actual = Environment.getInstance();
        assertEquals(testInstance, actual);
    }

    @DisplayName("throw ArrayIndexOutOfBoundsException when cursor > " + MAX_SIZE)
    @Test
    void testRightShiftOutOfBounds() {
        testInstance.reset();
        for (int i = 0; i < (MAX_SIZE - 1); i++) {
            testInstance.rightShift();
        }
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> testInstance.rightShift(),
                "Max index is " + (MAX_SIZE - 1));
    }

    @Test
    void testShifts() {
        testInstance.reset();
        testInstance.rightShift();
        assertEquals(1, testInstance.getCursor());

        testInstance.leftShift();
        assertEquals(0, testInstance.getCursor());
    }

    @DisplayName("throw ArrayIndexOutOfBoundsException when cursor < 0.")
    @Test
    void testLeftShiftOutOfBounds() {
        testInstance.reset();
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> testInstance.leftShift(),
                "index cannot be less than 0");
    }

    @DisplayName("throw ArrayIndexOutOfBoundsException when value > " + MAX_VALUE)
    @Test
    void testIncrementOutOfBounds() {
        testInstance.reset();
        for (int i = 0; i < (MAX_VALUE); i++) {
            testInstance.increment();
        }
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> testInstance.increment(),
                "Value cannot be biggest than " + MAX_VALUE);
    }

    @Test
    void testIncrementAndDecrement() {
        testInstance.reset();
        testInstance.increment();
        assertEquals(1, testInstance.getValue(testInstance.getCursor()));

        testInstance.decrement();
        assertEquals(0, testInstance.getValue(testInstance.getCursor()));
    }

    @DisplayName("throw ArrayIndexOutOfBoundsException when value < " + 0)
    @Test
    void testDecrementOutOfBounds() {
        testInstance.reset();
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> testInstance.decrement(),
                "Value cannot be less than " + 0);
    }

    @Test
    void testOutput() {
        int one = 1;
        char tmp = (char) one;
        String expected = String.valueOf(tmp);

        testInstance.reset();
        testInstance.increment();
        testInstance.output();
        String actual = testInstance.getOutputArr();

        assertEquals(expected, actual);
    }
}