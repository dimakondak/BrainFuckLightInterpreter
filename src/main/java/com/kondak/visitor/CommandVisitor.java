package com.kondak.visitor;

import com.kondak.commands.*;

public interface CommandVisitor {
    void visit(IncrementCommand incrementCommand);

    void visit(DecrementCommand decrementCommand);

    void visit(LeftShiftCommand leftShiftCommand);

    void visit(RightShiftCommand rightShiftCommand);

    void visit(OutputCommand outputCommand);

    void visit(BracketCommand bracketCommand);
}
