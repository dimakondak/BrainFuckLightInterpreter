package com.kondak.commands;

import com.kondak.visitor.CommandVisitor;

/**
 * If the integer at the data pointer is zero,
 * then instead of moving the instruction pointer forward to the next command,
 * jump it forward to the command after the matching ] command.
 */
public class BracketCommand implements Command {

    @Override
    public void execute() {
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }
}
