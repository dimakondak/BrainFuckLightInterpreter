package com.kondak.commands;

import com.kondak.environment.Environment;
import com.kondak.visitor.CommandVisitor;

/**
 * Output the char at the data pointer.
 */
public class OutputCommand implements Command {

    @Override
    public void execute() {
        Environment environment = Environment.getInstance();
        environment.output();
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }
}
