package ru.aegorova.htmlparser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.aegorova.htmlparser.exception.HTMLParserException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class HTMLParserJsoup implements HTMLParser {

    // logger
    private static final Logger log = LoggerFactory.getLogger(HTMLParserJsoup.class);

    //this method get path to downloaded html file and return list of words from this page
    @Override
    public List<String> parseHtml(String htmlFile) throws HTMLParserException {
        try {
            File input = new File(htmlFile);
            log.info("Successfully get html file from storage by path {}", input);
            // get text from html page
            Document doc = Jsoup.parse(input, "UTF-8");
            List<String> words = Arrays.stream(doc.text()
                    // First of all replace all digits and signs with spaces
                    .replaceAll("[^A-Za-zА-Яа-я]", " ")
                    // then delete overspaces
                    .replaceAll("( )+", " ")
                    // and divide string by spaces
                    .split(" ").clone())
                    // this is regex to divide strings such as "wordWordWord"
                    .map(l -> Arrays.asList(l.split("(?<!(^|[А-Я]))(?=[А-Я])" +
                            "|(?<!^)(?=[А-Я][а-я])" +
                            "(?<!(^|[A-Z]))(?=[A-Z])" +
                            "|(?<!^)(?=[A-Z][a-z])")))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
            log.info("Successfully parsed html file from storage by path {}", input);
            return words;
        } catch (IOException e) {
            log.error("Parsing html file from storage by path {} failed", htmlFile);
            throw new HTMLParserException("Can't parse html file by path: " + htmlFile, e);
        }
    }
}
