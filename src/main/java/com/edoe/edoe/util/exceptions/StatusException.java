package com.edoe.edoe.util.exceptions;

public class StatusException extends Exception {
    private static final long serialVersionUID = 1149241039409861914L;

    public StatusException(String msg){
        super(msg);
    }

    public StatusException(String msg, Throwable cause){
        super(msg, cause);
    }
}