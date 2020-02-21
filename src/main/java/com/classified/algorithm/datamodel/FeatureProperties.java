package com.classified.algorithm.datamodel;

import java.util.HashMap;
import java.util.Map;

public class FeatureProperties {
    /**
     * total number of Observations
     */
    public int n;

    /**
     * It stores the co-occurrences of Feature and Category values
     */
    public Map<String, Map<String, Integer>> featureCategoryJointCount;

    /**
     * Measures how many times each category was found in the training dataset.
     */
    public Map<String, Integer> categoryCounts;

    /**
     * Constructor
     */
    public FeatureProperties() {
        n = 0;
        featureCategoryJointCount = new HashMap<String, Map<String, Integer>>();
        categoryCounts = new HashMap<String, Integer>();
    }
}