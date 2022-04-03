package it.santini.arv7;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainOpenCSV {


    public static void main(String[] args) throws Exception {

        DataManager dataManager = new DataManager();

        String path = "C:\\Users\\048115485\\workspace-experiment\\ARV7\\input\\";

        List<String> files = listFilesForFolder(new File(path), "Results.csv");

        List<String> aggregatedResults = new ArrayList<>();

        System.out.println("------------------");

        String header = dataManager.getHeader(files.get(0));

        System.out.println(header);

        for (String file : files) {
            System.out.println(file);
            aggregatedResults.addAll(dataManager.aggregate(file));
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println(header);
        for(String aggregatedResult : aggregatedResults){
            System.out.println(aggregatedResult);
        }

    }


    public static List<String> listFilesForFolder(final File folder, String name) {

        List<String> files = new ArrayList<>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                files.addAll(listFilesForFolder(fileEntry, name));
            } else {
                if(fileEntry.getName().equals(name))
                files.add(fileEntry.getAbsolutePath());
                System.out.println(fileEntry.getAbsolutePath());
            }
        }
        return files;
    }


}
