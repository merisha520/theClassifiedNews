package com.classified.algorithm.test;

import com.classified.algorithm.constants.Constants;
import com.classified.algorithm.predict.Predictor;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class Tester {
    public void test() throws Exception {
        //File[] Files = new File(Constants.TESTDIR + "business").listFiles();
//        File[] Files = new File(Constants.TESTDIR + "entertainment").listFiles();
//        File[] Files = new File(Constants.TESTDIR + "politics").listFiles();
//        File[] Files = new File(Constants.TESTDIR + "sport").listFiles();
        File[] Files = new File(Constants.TESTDIR + "tech").listFiles();


        Predictor predictor = new Predictor();

        Map<String, Integer> confusionMAtrix = new HashMap<String, Integer>();
        confusionMAtrix.put("business", 0);
        confusionMAtrix.put("entertainment", 0);
        confusionMAtrix.put("politics", 0);
        confusionMAtrix.put("sport", 0);
        confusionMAtrix.put("tech", 0);

        for (File file : Files) {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            System.out.println(file.getName());
            String text = new String(data, "UTF-8");

            String predCategory = predictor.predict(text);
            confusionMAtrix.put(predCategory, confusionMAtrix.get(predCategory) + 1);
        }
        System.out.println(confusionMAtrix.toString());
    }

}