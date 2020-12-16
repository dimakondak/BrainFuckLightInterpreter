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
    private final Map<Character, Command> commands;

    //CommandParserVisitor composite the commands as tree
    public CommandParserVisitor(Map<Character, Command> commands) {
        currentNode = new LinkedList<>();
        currentNode.addFirst(new LinkedList<>());

        this.commands = commands;
    }

    public List<Command> getCommandList(String validCode) {
        for (char character : validCode.toCharArray()) {
            if (this.commands.containsKey(character)) {
                this.commands.get(character).accept(this);
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

    //if the parser meets a left bracket(container), the array is placed in it
    private void popNode() {
        this.addCommand(new BracketCommand(this.currentNode.pop()));
    }

    @Override
    public void visit(IncrementCommand ic) {
        this.addCommand(ic);
    }

    @Override
    public void visit(DecrementCommand dc) {
        this.addCommand(dc);
    }

    @Override
    public void visit(LeftShiftCommand lc) {
        this.addCommand(lc);
    }

    @Override
    public void visit(RightShiftCommand rc) {
        this.addCommand(rc);
    }

    @Override
    public void visit(OutputCommand oc) {
        this.addCommand(oc);
    }

    @Override
    public void visit(BracketCommand bc) {
        if (bc.isStartOfLoop()) {
            this.pushNode();
        } else {
            this.popNode();
        }
    }
}
