package com.kondak.visitor;

import com.kondak.commands.*;
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
        Map<Character, Command> commands = mock(HashMap.class);
        when(commands.containsKey('+')).thenReturn(true);
        when(commands.get('+')).thenReturn(new IncrementCommand());

        commandParser = new CommandParserVisitor(commands);
        String CODE = "+";

        assertEquals(IncrementCommand.class, commandParser.getCommandList(CODE).get(0).getClass());
    }

    @Test
    void getTaskListTestDecrementCommand() {
        Map<Character, Command> commands = mock(HashMap.class);
        when(commands.containsKey('-')).thenReturn(true);
        when(commands.get('-')).thenReturn(new DecrementCommand());

        commandParser = new CommandParserVisitor(commands);
        String CODE = "-";

        assertEquals(DecrementCommand.class, commandParser.getCommandList(CODE).get(0).getClass());
    }

    @Test
    void getTaskListTestRightShiftCommand() {
        Map<Character, Command> commands = mock(HashMap.class);
        when(commands.containsKey('>')).thenReturn(true);
        when(commands.get('>')).thenReturn(new RightShiftCommand());

        commandParser = new CommandParserVisitor(commands);
        String CODE = ">";

        assertEquals(RightShiftCommand.class, commandParser.getCommandList(CODE).get(0).getClass());
    }

    @Test
    void getTaskListTestLeftShiftCommand() {
        Map<Character, Command> commands = mock(HashMap.class);
        when(commands.containsKey('<')).thenReturn(true);
        when(commands.get('<')).thenReturn(new LeftShiftCommand());

        commandParser = new CommandParserVisitor(commands);
        String CODE = "<";

        assertEquals(LeftShiftCommand.class, commandParser.getCommandList(CODE).get(0).getClass());
    }

    @Test
    void getTaskListTestBracketsCommand() {
        Map<Character, Command> commands = mock(HashMap.class);
        when(commands.containsKey(']')).thenReturn(true);
        when(commands.get(']')).thenReturn(new BracketCommand(false));
        when(commands.containsKey('[')).thenReturn(true);
        when(commands.get('[')).thenReturn(new BracketCommand(true));

        commandParser = new CommandParserVisitor(commands);
        String CODE = "[]";

        assertEquals(BracketCommand.class, commandParser.getCommandList(CODE).get(0).getClass());
    }

    @DisplayName("throw NoSuchElementException when missing bracket.")
    @Test
    void testEmptyTaskList() {
        Map<Character, Command> commands = mock(HashMap.class);
        when(commands.containsKey(']')).thenReturn(true);
        when(commands.get(']')).thenReturn(new BracketCommand(false));

        commandParser = new CommandParserVisitor(commands);
        String CODE = "]";

        assertThrows(NoSuchElementException.class,
                () -> commandParser.getCommandList(CODE),
                "Task list is null");
    }
}
