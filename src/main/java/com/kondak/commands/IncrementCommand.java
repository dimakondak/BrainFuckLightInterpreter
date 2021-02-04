package com.kondak.commands;

import com.kondak.environment.Environment;
import com.kondak.visitor.CommandVisitor;

/**
 * Increment (increase by one) the integer at the data pointer.
 */
public class IncrementCommand implements Command {

    private final Environment environment;

    public IncrementCommand(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void execute() {
        environment.increment();
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }
}
