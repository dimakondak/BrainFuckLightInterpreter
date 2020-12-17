package com.kondak.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    public Map<Character, Command> getCommands() {
        return new HashMap<Character, Command>() {{
            put('+', new IncrementCommand());
            put('-', new DecrementCommand());
            put('>', new RightShiftCommand());
            put('<', new LeftShiftCommand());
            put('.', new OutputCommand());
            put(']', new BracketCommand(false));
            put('[', new BracketCommand(true));
        }};
    }
}
