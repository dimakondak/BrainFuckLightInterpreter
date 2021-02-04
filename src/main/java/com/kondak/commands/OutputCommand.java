package com.kondak.commands;

import com.kondak.environment.Environment;
import com.kondak.visitor.CommandVisitor;

/**
 * Output the char at the data pointer.
 */
public class OutputCommand implements Command {

    private final Environment environment;

    public OutputCommand(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void execute() {
        environment.output();
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }
}
