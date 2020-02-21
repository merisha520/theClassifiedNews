package com.classified.algorithm.train;

import com.classified.algorithm.classifier.NaiveBayes;
import com.classified.algorithm.constants.Constants;
import com.classified.algorithm.datamodel.KnowledgeBase;
import com.classified.algorithm.features.Tokenize;
import com.classified.algorithm.folderutil.FolderReader;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Trainer {

    public void train() {
        Tokenize tokenize = new Tokenize();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Constants.MODEL_DIR);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            NaiveBayes nb = new NaiveBayes(tokenize);
            FolderReader fd = new FolderReader();

            //Degree of freedom 4 accuraccy 99% critical value 13.28
            nb.setChisquareCriticalValue(13.28);
            nb.train(fd.getCategorytoDocumentMap(Constants.TRAIN_DIR));
            KnowledgeBase knowledgeBase = nb.getKnowledgeBase();

            objectOutputStream.writeObject(knowledgeBase);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
