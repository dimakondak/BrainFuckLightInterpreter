package com.kondak;

import com.kondak.environment.Environment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExecutorTest {
    private static final Executor testInstance = new Executor();

    private String CODE;

    @DisplayName("throw IllegalArgumentException when CODE == null.")
    @Test
    void testNullCode() {
        CODE = null;
        assertThrows(IllegalArgumentException.class,
                () -> testInstance.validate(CODE),
                "Expression is empty");
    }

    @DisplayName("throw IllegalArgumentException when CODE is empty.")
    @Test
    void testEmptyCode() {
        CODE = "";
        assertThrows(IllegalArgumentException.class,
                () -> testInstance.validate(CODE),
                "Expression is empty");
    }

    @DisplayName("throw IllegalArgumentException when CODE with empty spaces.")
    @Test
    void testEmptySpacesCode() {
        CODE = "        ";
        assertThrows(IllegalArgumentException.class,
                () -> testInstance.validate(CODE),
                "Expression is empty");
    }

    @Test()
    void executeHelloWorldCode() {
        String expected = "Hello World!";

        Environment environment = Environment.getInstance();
        environment.reset();


        CODE = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
        testInstance.execute(CODE);

        String actual = environment.getOutputArr().trim();

        assertArrayEquals(expected.toCharArray(), actual.toCharArray());
    }

    @Test()
    void executeHelloWorld2Code() {
        String expected = "Hello World!";

        Environment environment = Environment.getInstance();
        environment.reset();


        CODE = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
        testInstance.execute(CODE);

        String actual = environment.getOutputArr().trim();

        assertArrayEquals(expected.toCharArray(), actual.toCharArray());
    }

}