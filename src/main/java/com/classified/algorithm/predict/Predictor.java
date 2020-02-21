package com.classified.algorithm.predict;

import com.classified.algorithm.classifier.NaiveBayes;
import com.classified.algorithm.constants.Constants;
import com.classified.algorithm.datamodel.KnowledgeBase;
import com.classified.algorithm.features.Tokenize;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Predictor {

    public String predict(String text) throws Exception {
        Tokenize tokenize = new Tokenize();

        FileInputStream fileInputStream = new FileInputStream(Constants.MODEL_DIR);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        KnowledgeBase knowledgeBase = (KnowledgeBase) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();

        NaiveBayes nb = new NaiveBayes(knowledgeBase, tokenize);
        String output = nb.predict(text);
        return output;
    }
}
