package com.kondak.visitor;

import com.kondak.commands.*;
import com.kondak.environment.Environment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommandParserVisitorTest {
    CommandParserVisitor commandParser;

    @Test
    void getTaskListTestIncrementCommand() {
        Environment environment = mock(Environment.class);
        Map<Character, CommandGetter> commands = mock(HashMap.class);
        when(commands.containsKey('+')).thenReturn(true);
        when(commands.get('+')).thenReturn(() -> new IncrementCommand(environment));

        commandParser = new CommandParserVisitor(commands);
        String CODE = "+";

        assertEquals(IncrementCommand.class, commandParser.getCommandList(CODE).get(0).getClass());
    }

    @Test
    void getTaskListTestDecrementCommand() {
        Environment environment = mock(Environment.class);
        Map<Character, CommandGetter> commands = mock(HashMap.class);
        when(commands.containsKey('-')).thenReturn(true);
        when(commands.get('-')).thenReturn(() -> new DecrementCommand(environment));

        commandParser = new CommandParserVisitor(commands);
        String CODE = "-";

        assertEquals(DecrementCommand.class, commandParser.getCommandList(CODE).get(0).getClass());
    }

    @Test
    void getTaskListTestRightShiftCommand() {
        Environment environment = mock(Environment.class);
        Map<Character, CommandGetter> commands = mock(HashMap.class);
        when(commands.containsKey('>')).thenReturn(true);
        when(commands.get('>')).thenReturn(() -> new RightShiftCommand(environment));

        commandParser = new CommandParserVisitor(commands);
        String CODE = ">";

        assertEquals(RightShiftCommand.class, commandParser.getCommandList(CODE).get(0).getClass());
    }

    @Test
    void getTaskListTestLeftShiftCommand() {
        Environment environment = mock(Environment.class);
        Map<Character, CommandGetter> commands = mock(HashMap.class);
        when(commands.containsKey('<')).thenReturn(true);
        when(commands.get('<')).thenReturn(() -> new LeftShiftCommand(environment));

        commandParser = new CommandParserVisitor(commands);
        String CODE = "<";

        assertEquals(LeftShiftCommand.class, commandParser.getCommandList(CODE).get(0).getClass());
    }

    @Test
    void getTaskListTestBracketsCommand() {
        Environment environment = mock(Environment.class);
        Map<Character, CommandGetter> commands = mock(HashMap.class);
        when(commands.containsKey(']')).thenReturn(true);
        when(commands.get(']')).thenReturn(() -> new CompositeBracketCommand(environment));
        when(commands.containsKey('[')).thenReturn(true);
        when(commands.get('[')).thenReturn(BracketCommand::new);

        commandParser = new CommandParserVisitor(commands);
        String CODE = "[]";

        assertEquals(CompositeBracketCommand.class, commandParser.getCommandList(CODE).get(0).getClass());
    }

    @DisplayName("throw IllegalArgumentException when used unexpected character")
    @Test
    void testUnexpectedCharacter() {
        Environment environment = mock(Environment.class);
        Map<Character, CommandGetter> commands = mock(HashMap.class);
        when(commands.containsKey('*')).thenReturn(false);

        commandParser = new CommandParserVisitor(commands);
        String CODE = "*";

        assertThrows(IllegalArgumentException.class,
                () -> commandParser.getCommandList(CODE),
                "Unexpected value: " + CODE);
    }

    @DisplayName("throw NoSuchElementException when missing bracket.")
    @Test
    void testEmptyTaskList() {
        Environment environment = mock(Environment.class);
        Map<Character, CommandGetter> commands = mock(HashMap.class);
        when(commands.containsKey(']')).thenReturn(true);
        when(commands.get(']')).thenReturn(() -> new CompositeBracketCommand(environment));

        commandParser = new CommandParserVisitor(commands);
        String CODE = "]";

        assertThrows(NoSuchElementException.class,
                () -> commandParser.getCommandList(CODE),
                "Task list is null");
    }
}
