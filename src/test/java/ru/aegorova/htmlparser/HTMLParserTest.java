package ru.aegorova.htmlparser;

import ru.aegorova.htmlparser.exception.HTMLParserException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.aegorova.htmlparser.parser.HTMLParser;
import ru.aegorova.htmlparser.parser.HTMLParserJsoup;

public class HTMLParserTest {

    //logger
    private static final Logger log = LoggerFactory.getLogger(HTMLParserTest.class);

    @Test
    public void parseHtml(){
        String file = "storage/www.simbirsoft.com_1a6e510b-f284-48b8-ade6-fbbb7f4069b9.html";
        HTMLParser parser = new HTMLParserJsoup();
        try {
            parser.parseHtml(file).forEach(System.out::println);
        } catch (HTMLParserException e) {
            log.error("Parsing html file from storage by path {} failed", file);
        }
    }
}
