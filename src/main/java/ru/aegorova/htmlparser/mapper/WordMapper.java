package ru.aegorova.htmlparser.mapper;

import java.util.List;
import java.util.Map;

public interface WordMapper {
    Map<String, Integer> countWords(List<String> words);
}
