package com.classified.algorithm.datamodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class KnowledgeBase implements Serializable {
    /**
     * number of training observations
     */
    public int n = 0;

    /**
     * number of categories
     */
    public int c = 0;

    /**
     * number of features
     */
    public int d = 0;

    /**
     * log priors for log( P(c) )
     */
    public Map<String, Double> logPriors = new HashMap<String, Double>();

    /**
     * log likelihood for log( P(x|c) )
     */
    public Map<String, Map<String, Double>> logLikelihoods = new HashMap<String, Map<String, Double>>();
}