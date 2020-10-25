package ru.aegorova.htmlparser;

import ru.aegorova.htmlparser.exception.HTMLParserException;
import ru.aegorova.htmlparser.mapper.WordMapper;
import ru.aegorova.htmlparser.mapper.WordMapperImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.aegorova.htmlparser.parser.HTMLParser;
import ru.aegorova.htmlparser.parser.HTMLParserJsoup;

public class WordMapperTest {

    //logger
    private static final Logger log = LoggerFactory.getLogger(WordMapperTest.class);

    @Test
    public void wordsMap() {
        String file = "storage/www.simbirsoft.com_1a6e510b-f284-48b8-ade6-fbbb7f4069b9.html";
        HTMLParser parser = new HTMLParserJsoup();
        WordMapper mapper = new WordMapperImpl();
        try {
            mapper.countWords(parser.parseHtml(file)).forEach((key,value) ->
                            System.out.println("Word: \"" + key + "\" count: " + value));
        } catch (HTMLParserException e) {
            log.error("Parsing html file from storage by path {} failed", file);
        }
    }
}
