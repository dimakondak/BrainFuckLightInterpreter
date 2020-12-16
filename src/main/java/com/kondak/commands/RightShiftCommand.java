package com.kondak.commands;

import com.kondak.environment.Environment;
import com.kondak.visitor.CommandVisitor;

/**
 * Increment the data pointer (to point to the next cell to the right).
 */
public class RightShiftCommand implements Command {

    @Override
    public void execute() {
        Environment environment = Environment.getInstance();
        environment.rightShift();
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }
}
