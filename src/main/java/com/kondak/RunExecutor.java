package com.kondak;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunExecutor {
    private static final Executor EXECUTOR = new Executor();
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {

        final String CODE = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";

        log.info("Starting program");
        EXECUTOR.execute(CODE);
        log.info("Program completed successfully");
    }
}
