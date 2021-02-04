package com.kondak;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExecutorTest {
    private static final Executor testInstance = new Executor();

    private String CODE;

    @DisplayName("throw IllegalArgumentException when CODE == null.")
    @Test
    void testNullCode() {
        CODE = null;
        assertThrows(IllegalArgumentException.class,
                () -> testInstance.execute(CODE),
                "Expression is empty");
    }

    @DisplayName("throw IllegalArgumentException when CODE is empty.")
    @Test
    void testEmptyCode() {
        CODE = "";
        assertThrows(IllegalArgumentException.class,
                () -> testInstance.execute(CODE),
                "Expression is empty");
    }

    @DisplayName("throw IllegalArgumentException when CODE with empty spaces.")
    @Test
    void testEmptySpacesCode() {
        CODE = "        ";
        assertThrows(IllegalArgumentException.class,
                () -> testInstance.execute(CODE),
                "Expression is empty");
    }

    @Test()
    void executeHelloWorldCode() {
        String expected = "Hello World!";

        CODE = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
        testInstance.execute(CODE);

        String actual = testInstance.getResult();

        assertArrayEquals(expected.toCharArray(), actual.toCharArray());
    }

    @Test()
    void executeHelloWorld2Code() {
        String expected = "Hello World!";

        CODE = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
        testInstance.execute(CODE);

        String actual = testInstance.getResult();

        assertArrayEquals(expected.toCharArray(), actual.toCharArray());
    }

    @Test()
    void executeTeamDevInternshipCode() {
        String expected = "TeamDev Internship";

        CODE = "++++++++++[>+>+++>+++++++>++++++++++<<<<-]>>>++++++++++++++.>+.----.++++++++++++.<----------------.>--------.+++++++++++++++++.<<++.>+++++.>--------.++++++.---------------.+++++++++++++.----.+++++.-----------.+.+++++++.";
        testInstance.execute(CODE);

        String actual = testInstance.getResult();

        assertArrayEquals(expected.toCharArray(), actual.toCharArray());
    }

    @Test()
    void executeJavaClassesCode() {
        String expected = "Java Classes";

        CODE = "++++++++++[>+>+++>+++++++>++++++++++<<<<-]>>>++++.>---.+++++++++++++++++++++.---------------------.<<++.>-------.>+++++++++++.-----------.++++++++++++++++++..--------------.++++++++++++++.";
        testInstance.execute(CODE);

        String actual = testInstance.getResult();

        assertArrayEquals(expected.toCharArray(), actual.toCharArray());
    }

    @Test()
    void executeMyDreamCode() {
        String expected = "My Dream";

        CODE = "++++++++++[>+>+++>+++++++>++++++++++<<<<-]>>>+++++++.>+++++++++++++++++++++.<<++.>---------.>-------.-------------.----.++++++++++++.";
        testInstance.execute(CODE);

        String actual = testInstance.getResult();

        assertArrayEquals(expected.toCharArray(), actual.toCharArray());
    }
}