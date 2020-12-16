package com.kondak.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    public Map<Character, Command> getCommands() {
        Map<Character, Command> commandMap = new HashMap<>();
        commandMap.put('+', new IncrementCommand());
        commandMap.put('-', new DecrementCommand());
        commandMap.put('>', new RightShiftCommand());
        commandMap.put('<', new LeftShiftCommand());
        commandMap.put('.', new OutputCommand());

        commandMap.put(']', new BracketCommand(false));
        commandMap.put('[', new BracketCommand(true));

        return commandMap;
    }
}
