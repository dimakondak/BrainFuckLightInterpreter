package com.kondak.commands;

import com.kondak.environment.Environment;
import com.kondak.visitor.CommandVisitor;

/**
 * Increment (increase by one) the integer at the data pointer.
 */
public class IncrementCommand implements Command {

    @Override
    public void execute() {
        Environment environment = Environment.getInstance();
        environment.increment();
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }
}
