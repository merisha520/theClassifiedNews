package com.classified.algorithm.features;

import com.classified.algorithm.datamodel.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tokenize {

    public static String STOP_WORDS_PATH = Tokenize.class.getResource("/stopwords.txt").getPath();

    /**
     * Remoce punctuation, remove extra spaces and lower the case
     *
     * @param text
     * @return token array of String
     */
    public String[] getTokens(String text) {
        return text.replaceAll("\\p{P}", " ")
                .replaceAll("\\s+", " ")
                .replaceAll("\\R", " ")
                .toLowerCase()
                .split(" ");
    }


    /**
     * removes stopword
     *
     * @param text String array
     * @return string array without stop words
     */
    public String[] removeStopWords(String[] text) {
        ArrayList<String> stopWords = new ArrayList<String>();
        ArrayList<String> texts = new ArrayList<String>(Arrays.asList(text));

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(STOP_WORDS_PATH));
            String line = fileReader.readLine();
            while ((line != null)) {
                stopWords.add(line);
                line = fileReader.readLine();
            }

            texts.removeAll(stopWords);

        } catch (IOException e) {
            System.out.println("stop words file not found");
        }

        return texts.toArray(new String[texts.size()]);
    }


    public Map<String, Integer> getKeywordCounts(String[] keywordArray) {
        Map<String, Integer> counts = new HashMap<String, Integer>();

        Integer counter;
        for (int i = 0; i < keywordArray.length; ++i) {
            counter = counts.get(keywordArray[i]);
            if (counter == null) {
                counter = 0;
            }
            counts.put(keywordArray[i], ++counter); //increase counter for the keyword
        }

        return counts;
    }

    public Document tokenize(String text) {
        String[] cleanText = getTokens(text);
        String[] removedStopWords = removeStopWords(cleanText);

        PorterStemmer stemmer = new PorterStemmer();
        ArrayList<String> stemmed = new ArrayList<String>();

        for (String e : removedStopWords) {
            stemmed.add(stemmer.stemWord(e));
        }

        String[] keyWordArray = stemmed.toArray(new String[stemmed.size()]);
        Document doc = new Document();

        doc.tokens = getKeywordCounts(keyWordArray);

        return doc;
    }
}
