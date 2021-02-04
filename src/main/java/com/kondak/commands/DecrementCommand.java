package com.kondak.commands;

import com.kondak.environment.Environment;
import com.kondak.visitor.CommandVisitor;

/**
 * Decrement (decrease by one) the integer at the data pointer.
 */
public class DecrementCommand implements Command {

    private final Environment environment;

    public DecrementCommand(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void execute() {
        environment.decrement();
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }
}
