package com.classified.webApi;

import java.io.Serializable;
import java.util.Map;

public class DemoModel implements Serializable {
    private String originalString;
    private String[] cleanedText;
    private String[] stopWordsRemoved;
    private String[] porterStemmed;
    private Map<String, Integer> keyWordCounts;
    private Map<String, Double> predictedProbabilities;
    private String predictedCategory;

    public DemoModel(){}

    public DemoModel(String originalString, String[] cleanedText, String[] stopWordsRemoved, String[] porterStemmed, Map<String, Integer> keyWordCounts, Map<String, Double> predictedProbabilities, String predictedCategory) {
        this.originalString = originalString;
        this.cleanedText = cleanedText;
        this.stopWordsRemoved = stopWordsRemoved;
        this.porterStemmed = porterStemmed;
        this.keyWordCounts = keyWordCounts;
        this.predictedProbabilities = predictedProbabilities;
        this.predictedCategory = predictedCategory;
    }

    public String getOriginalString() {
        return originalString;
    }

    public void setOriginalString(String originalString) {
        this.originalString = originalString;
    }

    public String[] getCleanedText() {
        return cleanedText;
    }

    public void setCleanedText(String[] cleanedText) {
        this.cleanedText = cleanedText;
    }

    public String[] getStopWordsRemoved() {
        return stopWordsRemoved;
    }

    public void setStopWordsRemoved(String[] stopWordsRemoved) {
        this.stopWordsRemoved = stopWordsRemoved;
    }

    public String[] getPorterStemmed() {
        return porterStemmed;
    }

    public void setPorterStemmed(String[] porterStemmed) {
        this.porterStemmed = porterStemmed;
    }

    public Map<String, Integer> getKeyWordCounts() {
        return keyWordCounts;
    }

    public void setKeyWordCounts(Map<String, Integer> keyWordCounts) {
        this.keyWordCounts = keyWordCounts;
    }

    public Map<String, Double> getPredictedProbabilities() {
        return predictedProbabilities;
    }

    public void setPredictedProbabilities(Map<String, Double> predictedProbabilities) {
        this.predictedProbabilities = predictedProbabilities;
    }

    public String getPredictedCategory() {
        return predictedCategory;
    }

    public void setPredictedCategory(String predictedCategory) {
        this.predictedCategory = predictedCategory;
    }
}
