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
        List<Command> taskList = this.parseStringToCommands(this.validate(code));

        taskList.forEach(Command::execute);
        log.info("Code executed");
    }

    public String validate(String code) {
        if (code == null || code.trim().isEmpty()) {
            log.error("Caught empty expression");
            throw new IllegalArgumentException("Expression is empty");
        } else {
            return code;
        }
    }

    private List<Command> parseStringToCommands(String validCode) {
        CommandParserVisitor parser = new CommandParserVisitor(new CommandFactory().getCommands());

        log.info("Staring parsing the code and composite a list of commands");

        return parser.getCommandList(validCode);
    }
}
