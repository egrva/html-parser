package ru.aegorova.htmlparser.exception;

public class HTMLParserException extends Exception {
    public HTMLParserException(String errorMessage, Throwable error) {
        super(errorMessage,error);
    }
}
