package it.santini.arv7;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainOpenCSV {


    public static void main(String[] args) throws Exception {

        AggregateManager aggregateManager = new AggregateManager();

        String path = "C:\\Users\\048115485\\workspace-experiment\\ARV7-maven\\input\\";

        final File folder = new File(path);
        List<String> files = listFilesForFolder(folder);

        for (String file : files) {
            aggregateManager.aggregate(file);
        }
    }


    public static List<String> listFilesForFolder(final File folder) {

        List<String> files = new ArrayList<>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                files.addAll(listFilesForFolder(fileEntry));
            } else {
                if(fileEntry.getName().equals("Results.csv"))
                files.add(fileEntry.getAbsolutePath());
                System.out.println(fileEntry.getAbsolutePath());
            }
        }
        return files;
    }


}
