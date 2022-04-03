package it.santini.arv7;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public List<String> aggregate(String fileName) {

        List<String> aggregatedResults = new ArrayList<>();

        try {
            List<SampleCSV> sampleCSVs = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(SampleCSV.class)
                    .withSkipLines(6)
                    .build()
                    .parse();


            String sampleName = "start";
            StringBuilder sb = new StringBuilder();

            List<Sample> samples = new ArrayList<>();
            for (SampleCSV sampleCSV : sampleCSVs) {
                if (sampleCSV.getSampleName() != null) {
                    System.out.println(sampleCSV.toString());
                    samples.add(mapToSample(sampleCSV));
                }
            }


            for (Sample sample : samples) {

                if (sampleName.equals(sample.getSampleName())) {
                    sb.append(sample.getCqMeanVerified() + ",");
                } else {
                    sampleName = sample.getSampleName();
                    aggregatedResults.add(sb.toString());
                    sb = new StringBuilder();
                    sb.append(sampleName + "," + sample.getPatientId() + "," + sample.getOccurrence() + "," + sample.getCqMeanVerified() + ",");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return aggregatedResults;
    }

    private Sample mapToSample(SampleCSV sampleCSV) {

        String cqMeanVerified = sampleCSV.getAmpScore() >= 0.8 ? sampleCSV.getCqMean() : "-";

        return new Sample(
                sampleCSV.getSampleName(),
                sampleCSV.getSampleName().substring(1),
                sampleCSV.getSampleName().substring(0, 1),
                sampleCSV.getTargetName(),
                cqMeanVerified
        );
    }
}
