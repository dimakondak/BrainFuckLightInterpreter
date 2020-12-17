package com.kondak;

import com.kondak.commands.Command;
import com.kondak.commands.CommandFactory;
import com.kondak.visitor.CommandParserVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * class Executor validate, parse String code to List of Commands and execute these Commands
 */
public class Executor {
    private static final Logger log = LogManager.getLogger();

    public void execute(String code) {
        if (this.isCodeValid(code)) {
            this.parseStringToCommands(code).forEach(Command::execute);
            log.info("Code executed");
        } else {
            log.info("Something gone wrong");
            throw new IllegalArgumentException();
        }
    }

    private boolean isCodeValid(String code) {
        boolean result;
        if (code == null || code.trim().isEmpty()) {
            log.error("Caught empty expression");
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    private List<Command> parseStringToCommands(String code) {
        CommandParserVisitor parser = new CommandParserVisitor(new CommandFactory().getCommands());
        log.info("Staring parsing the code and composite a list of commands");
        return parser.getCommandList(code);
    }
}
