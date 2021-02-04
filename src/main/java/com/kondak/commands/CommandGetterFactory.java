package com.kondak.commands;

import com.kondak.environment.Environment;

import java.util.HashMap;
import java.util.Map;

public class CommandGetterFactory {
    public Map<Character, CommandGetter> getCommands(Environment environment) {
        return new HashMap<Character, CommandGetter>() {{
            put('+', () -> new IncrementCommand(environment));
            put('-', () -> new DecrementCommand(environment));
            put('>', () -> new RightShiftCommand(environment));
            put('<', () -> new LeftShiftCommand(environment));
            put('.', () -> new OutputCommand(environment));
            put('[', BracketCommand::new);
            put(']', () -> new CompositeBracketCommand(environment));
        }};
    }
}
