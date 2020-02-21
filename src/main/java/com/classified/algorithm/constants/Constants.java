package com.classified.algorithm.constants;

public interface Constants {
    public static final String MODEL_DIR = Constants.class
            .getResource("/savedmodel/knowledgebase.txt")
            .getPath();

    public static final String TRAIN_DIR = Constants.class
            .getResource("/bbc/train/")
            .getPath();

    public static final String TESTDIR = Constants.class
            .getResource("/bbc/test/")
            .getPath();
}