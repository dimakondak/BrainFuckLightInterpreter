package com.kondak.visitor;

import com.kondak.commands.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * class CommandParserVisitor parses the String of the original brainfuck code and returns the commands tree for the Executor class
 */
public class CommandParserVisitor implements CommandVisitor {
    private static final Logger log = LogManager.getLogger();
    private final Deque<List<Command>> currentNode;
    private final Map<Character, CommandGetter> commands;

    //CommandParserVisitor composite the commands as tree
    public CommandParserVisitor(Map<Character, CommandGetter> commands) {
        currentNode = new LinkedList<>();
        currentNode.addFirst(new LinkedList<>());

        this.commands = commands;
    }

    public List<Command> getCommandList(String validCode) {
        for (char character : validCode.toCharArray()) {
            if (this.commands.containsKey(character)) {
                this.commands.get(character).getCommand().accept(this);
            } else {
                log.error("Got unknown command");
                throw new IllegalArgumentException();
            }
        }
        return currentNode.peekFirst();
    }

    private void addCommand(Command command) {
        if (this.currentNode.peekFirst() != null) {
            currentNode.peekFirst().add(command);
        } else {
            log.error("Bracket not found");
            throw new NoSuchElementException("Bracket not found");
        }

    }

    //if the parser meets a right bracket, a new array is created
    private void pushNode() {
        this.currentNode.push(new LinkedList<>());
    }

    @Override
    public void visit(IncrementCommand incrementCommand) {
        this.addCommand(incrementCommand);
    }

    @Override
    public void visit(DecrementCommand decrementCommand) {
        this.addCommand(decrementCommand);
    }

    @Override
    public void visit(LeftShiftCommand leftShiftCommand) {
        this.addCommand(leftShiftCommand);
    }

    @Override
    public void visit(RightShiftCommand rightShiftCommand) {
        this.addCommand(rightShiftCommand);
    }

    @Override
    public void visit(OutputCommand outputCommand) {
        this.addCommand(outputCommand);
    }

    @Override
    public void visit(BracketCommand bracketCommand) {
        this.pushNode();
        this.addCommand(bracketCommand);
    }

    //if the parser meets a left bracket(container), the array is placed in it
    @Override
    public void visit(CompositeBracketCommand compositeBracketCommand) {
        compositeBracketCommand.setCommands(this.currentNode.pop());
        this.addCommand(compositeBracketCommand);
    }
}
