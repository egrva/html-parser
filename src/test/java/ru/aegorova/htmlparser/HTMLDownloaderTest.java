package ru.aegorova.htmlparser;

import ru.aegorova.htmlparser.downloader.HTMLDownloader;
import ru.aegorova.htmlparser.downloader.HTMLDownloaderJsoup;
import ru.aegorova.htmlparser.exception.HTMLParserException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTMLDownloaderTest {

    //logger
    private static final Logger log = LoggerFactory.getLogger(HTMLDownloaderTest.class);

    @Test
    public void downloadUrlTest() {
        HTMLDownloader htmlDownloader = new HTMLDownloaderJsoup();
        // url
        String url = "https://www.simbirsoft.com/";
        // local storage directory
        String directory = "storage";
        try {
            htmlDownloader.downloadHtmlFile(url, directory);
        } catch (HTMLParserException e) {
            log.error("Downloading html-file with by url {} failed", url);
        }
    }


}
