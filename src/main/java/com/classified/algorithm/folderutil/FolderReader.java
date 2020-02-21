package com.classified.algorithm.folderutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FolderReader {

    private String ROOTDIR;

    private Map<String, File> getTrainingFilesDir() {
        Map<String, File> trainingFiles = new HashMap<String, File>();
        String[] categories = {"business", "entertainment", "politics", "sport", "tech"};
        for (String s : categories)
            trainingFiles.put(s, new File(ROOTDIR + s));
        return trainingFiles;
    }

    private String[] readLines(File folder) throws IOException {
        File[] fileNames = folder.listFiles();

        ArrayList<String> lines = new ArrayList<String>();

        for (File file : fileNames) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines.toArray(new String[lines.size()]);
    }

    /**
     * @return Map of category and a line as a document in the category
     * @throws IOException
     */
    public Map<String, String[]> getCategorytoDocumentMap(String dir) throws IOException {
        this.ROOTDIR = dir;
        Map<String, File> datafiles = getTrainingFilesDir();
        String category;
        String[] linesList = null;
        Map<String, String[]> categorytoDocument = new HashMap<String, String[]>();
        for (Map.Entry<String, File> entry : datafiles.entrySet()) {
            category = entry.getKey();
            linesList = readLines(entry.getValue());
            categorytoDocument.put(category, linesList);
        }
        System.out.println(categorytoDocument);
        return categorytoDocument;
    }
}

