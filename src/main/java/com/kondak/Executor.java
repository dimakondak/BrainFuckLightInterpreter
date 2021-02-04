package com.kondak;

import com.kondak.commands.Command;
import com.kondak.commands.CommandGetterFactory;
import com.kondak.environment.Environment;
import com.kondak.visitor.CommandParserVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * class Executor validate, parse String code to List of Commands and execute these Commands
 */
public class Executor {
    private static final Logger log = LogManager.getLogger();

    private String result;

    public void execute(String code) {
        if (this.isCodeValid(code)) {
            Environment environment = new Environment();
            log.info("Environment created");

            this.parseStringToCommands(code, environment).forEach(Command::execute);
            result = environment.getOutputArr().trim();
            log.info("Code executed");
        } else {
            log.info("Something gone wrong");
            throw new IllegalArgumentException();
        }
    }

    public String getResult() {
        return result;
    }

    private boolean isCodeValid(String code) {
        if (code == null || code.trim().isEmpty()) {
            log.error("Caught empty expression");
            return false;
        }
        return true;
    }

    private List<Command> parseStringToCommands(String code, Environment environment) {
        CommandParserVisitor parser = new CommandParserVisitor(new CommandGetterFactory().getCommands(environment));
        log.info("Staring parsing the code and composite a list of commands");
        return parser.getCommandList(code);
    }
}
