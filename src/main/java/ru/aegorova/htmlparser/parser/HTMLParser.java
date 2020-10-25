package ru.aegorova.htmlparser.parser;

import ru.aegorova.htmlparser.exception.HTMLParserException;

import java.util.List;

public interface HTMLParser {
    public List<String> parseHtml(String htmlFile) throws HTMLParserException;
}
