package ru.aegorova.htmlparser.service;

import ru.aegorova.htmlparser.repository.WordRepository;

import java.util.Map;

public class WordServiceImpl implements WordService {

    private WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository){
        this.wordRepository = wordRepository;
    }
    // method to save map of words and its occurrences in db
    @Override
    public void saveMapOfWord(Map<String, Integer> map) {
        map.forEach((key,value) -> wordRepository.save(key,value));
    }
}
