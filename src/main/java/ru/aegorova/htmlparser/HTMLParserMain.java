package ru.aegorova.htmlparser;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.aegorova.htmlparser.downloader.HTMLDownloader;
import ru.aegorova.htmlparser.downloader.HTMLDownloaderJsoup;
import ru.aegorova.htmlparser.exception.HTMLParserException;
import ru.aegorova.htmlparser.mapper.WordMapper;
import ru.aegorova.htmlparser.mapper.WordMapperImpl;
import ru.aegorova.htmlparser.parser.HTMLParser;
import ru.aegorova.htmlparser.parser.HTMLParserJsoup;
import ru.aegorova.htmlparser.repository.WordRepository;
import ru.aegorova.htmlparser.repository.WordRepositoryPostgres;
import ru.aegorova.htmlparser.service.WordService;
import ru.aegorova.htmlparser.service.WordServiceImpl;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class HTMLParserMain {

    // logger
    private static final Logger log = LoggerFactory.getLogger(HTMLParserMain.class);

    public static void main(String[] args) {

        // storage dir
        final String storage = "storage";
        // creating instances of util classes
        final HTMLDownloader htmlDownloader = new HTMLDownloaderJsoup();
        final HTMLParser htmlParser = new HTMLParserJsoup();
        final WordMapper wordMapper = new WordMapperImpl();
        final WordRepository repository = createWordRepository();
        final WordService service = new WordServiceImpl(repository);
        // Get url from terminal
        final String url = args[0];
        log.info("Get url {} from terminal", url);
        // download and parse html-file
        try {
            log.info("Start download html by url {}", url);
            String htmlFile = htmlDownloader.downloadHtmlFile(url,storage);
            log.info("Finish download html by url {}", url);
            log.info("Start parce htmlfile with path {}", htmlFile);
            List<String> words = htmlParser.parseHtml(htmlFile);
            log.info("Finish parce htmlfile with path {}", htmlFile);
            log.info("Start count uniq words in htmlfile with path {}", htmlFile);
            Map<String,Integer> map = wordMapper.countWords(words);
            log.info("Finish count uniq words in htmlfile with path {}", htmlFile);
            map.forEach((key,value) ->
                    System.out.println("Word: \"" + key + "\" count: " + value));
            log.info("Start save it in db");
            service.saveMapOfWord(map);
            log.info("Finish save it in db");
        } catch (HTMLParserException e) {
            log.error("Main process for url {} failed", url);
            throw new IllegalStateException(e);
        }
    }

    // method to create database connection
    private static WordRepository createWordRepository() {
        log.info("Start to read db properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            log.error("Can't read db properties");
            throw new IllegalStateException(e);
        }
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String url = properties.getProperty("db.url");

        Connection connection;
        log.info("Try to get db connection");
        try {
            connection = DriverManager.getConnection(url, username, password);
            log.info("Successfully connected to db");
        } catch (SQLException e) {
            log.error("Can't connect to db");
            throw new IllegalStateException(e);
        }
        return new WordRepositoryPostgres(connection);
    }
}
