package com.kondak.commands;

import com.kondak.visitor.CommandVisitor;

/**
 * Interface Command for pattern Command
 */
public interface Command {
    void execute();

    void accept(CommandVisitor commandVisitor);
}
