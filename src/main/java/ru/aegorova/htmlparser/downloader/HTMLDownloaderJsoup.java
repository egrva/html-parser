package ru.aegorova.htmlparser.downloader;


import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.aegorova.htmlparser.exception.HTMLParserException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

// class to download page by url
public class HTMLDownloaderJsoup implements HTMLDownloader {
    // logger
    private static final Logger log = LoggerFactory.getLogger(HTMLDownloaderJsoup.class);

    // download page by url to directory
    @Override
    public String downloadHtmlFile(String url, String dirPath) throws HTMLParserException {
        try {
            log.info("Start downloading html-file by url {}", url);
            final Connection.Response response = Jsoup.connect(url).execute();
            final Document doc = response.parse();
            final String filename = createFileName(url, dirPath);
            FileUtils.writeStringToFile(new File(filename), doc.outerHtml(), StandardCharsets.UTF_8);
            log.info("Successfully downloaded html-file with path {} by url {}", filename, url);
            return filename;
        } catch (IOException | HTMLParserException e) {
            log.error("Downloading html-file with by url {} failed", url);
            throw new HTMLParserException("Failed to downloading html file by url: " + url, e);
        }
    }

    // method to creat uniq filename by pattern: /path/to/local/storage/www.example.com_UUID.html
    private String createFileName(String url, String dirPath) throws HTMLParserException {
        try {
            URL urlForName = new URL(url);
            return dirPath +
                    "/" +
                    urlForName.getHost() +
                    "_" +
                    UUID.randomUUID().toString() +
                    ".html";
        } catch (MalformedURLException e) {
            throw new HTMLParserException("Incorrect url", e);
        }
    }
}
