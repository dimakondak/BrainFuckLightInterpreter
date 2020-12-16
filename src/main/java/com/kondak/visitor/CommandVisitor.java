package com.kondak.visitor;

import com.kondak.commands.*;

public interface CommandVisitor {
    void visit(IncrementCommand ic);

    void visit(DecrementCommand dc);

    void visit(LeftShiftCommand lc);

    void visit(RightShiftCommand rc);

    void visit(OutputCommand oc);

    void visit(BracketCommand bc);
}
