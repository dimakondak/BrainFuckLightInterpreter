package com.kondak.commands;

import com.kondak.environment.Environment;
import com.kondak.visitor.CommandVisitor;

/**
 * Decrement the data pointer (to point to the next cell to the left).
 */
public class LeftShiftCommand implements Command {

    private final Environment environment;

    public LeftShiftCommand(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void execute() {
        environment.leftShift();
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }
}
