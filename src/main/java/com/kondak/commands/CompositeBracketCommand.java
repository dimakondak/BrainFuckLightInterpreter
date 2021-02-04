package com.kondak.commands;

import com.kondak.environment.Environment;
import com.kondak.visitor.CommandVisitor;

import java.util.List;

/**
 * If the integer at the data pointer is zero,
 * then instead of moving the instruction pointer forward to the next command,
 * jump it forward to the command after the matching ] command.
 */
public class CompositeBracketCommand extends BracketCommand {

    private List<Command> commands;

    private final Environment environment;

    public CompositeBracketCommand(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void execute() {
        if (this.commands != null) {
            if (environment.getValue() > 0) {
                for (int i = 0; i < this.commands.size(); i++) {
                    this.commands.get(i).execute();
                    if (i == this.commands.size() - 1) {
                        if (environment.getValue() > 0) {
                            i = -1;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visit(this);
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
