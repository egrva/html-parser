package ru.aegorova.htmlparser.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordMapperImpl implements WordMapper {

    // logger
    private static final Logger log = LoggerFactory.getLogger(WordMapperImpl.class);

    // count uniq words from list and return map: key - uniq word, value - number of occurrences
    @Override
    public Map<String, Integer> countWords(List<String> words) {
        log.info("Start mapping words");
        Map<String, Integer> map = new HashMap<>();
        words.forEach(word -> {
            if (map.containsKey(word))
                map.replace(word, (map.get(word) + 1));
            else
                map.put(word, 1);
        });
        return map;
    }
}
