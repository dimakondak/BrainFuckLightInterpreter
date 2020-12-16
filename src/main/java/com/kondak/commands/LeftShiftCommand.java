package com.kondak.commands;

import com.kondak.environment.Environment;
import com.kondak.visitor.CommandVisitor;

/**
 * Decrement the data pointer (to point to the next cell to the left).
 */
public class LeftShiftCommand implements Command {

    @Override
    public void execute() {
        Environment environment = Environment.getInstance();
        environment.leftShift();
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }
}
