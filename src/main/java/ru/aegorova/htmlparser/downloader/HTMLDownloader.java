package ru.aegorova.htmlparser.downloader;

import ru.aegorova.htmlparser.exception.HTMLParserException;

public interface HTMLDownloader {
    String downloadHtmlFile(String url, String dirPath) throws HTMLParserException;
}
