package it.santini.arv7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MainManager {

    private final DataManager dataManager;

    public MainManager() {
        this.dataManager = new DataManager();
    }

    public void organize(String folderPath) throws IOException {

        List<String> inputPaths = listFilesForFolder(new File(folderPath), "Results.csv");

        String header = dataManager.getHeader(inputPaths);


        List<String> aggregatedResults = new ArrayList<>();
        for (String inputPath : inputPaths) {
            aggregatedResults.addAll(dataManager.aggregate(inputPath));
        }

        writeOnFile(folderPath, header, aggregatedResults);
        print(folderPath, header, aggregatedResults);

    }

    private void print(String folderPath, String header, List<String> aggregatedResults) {
        System.out.println(folderPath + "\\resultAggregated.csv");
        System.out.println(header);
        for (String aggregatedResult : aggregatedResults) {
            System.out.println(aggregatedResult);
        }
    }

    private void writeOnFile(String folderPath, String header, List<String> aggregatedResults){

        try (FileWriter writer = new FileWriter(folderPath + "\\resultAggregated.csv")){

            writer.write(header + System.lineSeparator());
            for (String aggregatedResult : aggregatedResults) {
                writer.write(aggregatedResult + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public List<String> listFilesForFolder ( final File folder, String name){

            List<String> fileUrls = new ArrayList<>();
            try (Stream<Path> paths = Files.walk(Paths.get(folder.getAbsolutePath()))) {
                paths
                        .filter(Files::isRegularFile)
                        .filter(p -> p.endsWith(name))
                        .forEach(p -> fileUrls.add(p.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fileUrls;
        }
    }

