package com.kondak.commands;

import com.kondak.environment.Environment;
import com.kondak.visitor.CommandVisitor;

import java.util.List;

/**
 * If the integer at the data pointer is zero,
 * then instead of moving the instruction pointer forward to the next command,
 * jump it forward to the command after the matching ] command.
 */
public class BracketCommand implements Command {

    //BracketCommand is Composite
    private List<Command> node;

    private boolean startOfLoop;

    public boolean isStartOfLoop() {
        return startOfLoop;
    }

    public BracketCommand(List<Command> node) {
        this.node = node;
    }

    public BracketCommand(Boolean startOfLoop) {
        this.startOfLoop = startOfLoop;
    }

    @Override
    public void execute() {
        Environment environment = Environment.getInstance();
        if (environment.getValue() > 0) {
            node.forEach(Command::execute);
        }
        if (environment.getValue() > 0) {
            this.execute();
        }
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }
}
