package com.kondak.commands;

import com.kondak.environment.Environment;
import com.kondak.visitor.CommandVisitor;

/**
 * Increment the data pointer (to point to the next cell to the right).
 */
public class RightShiftCommand implements Command {

    private final Environment environment;

    public RightShiftCommand(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void execute() {
        environment.rightShift();
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }
}
