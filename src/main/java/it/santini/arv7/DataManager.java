package it.santini.arv7;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataManager {


    public List<String> aggregate(String fileName) throws FileNotFoundException {

        List<Sample> samples = getSamples(fileName);


        List<String> aggregatedResults = new ArrayList<>();


        String sampleName = "start";
        StringBuilder sb = new StringBuilder();
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
        aggregatedResults.add(sb.toString());

        return aggregatedResults;
    }

    private Sample mapToSample(SampleCSV sampleCSV) {

        String cqMeanVerified = sampleCSV.getAmpScore() >= 0.8 ? sampleCSV.getCqMean() : "*";

        return new Sample(
                sampleCSV.getSampleName(),
                sampleCSV.getSampleName().substring(1),
                sampleCSV.getSampleName().substring(0, 1),
                sampleCSV.getTargetName(),
                cqMeanVerified
        );
    }

    private List<Sample> getSamples(String fileName) throws FileNotFoundException {

        List<SampleCSV> sampleCSVs = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(SampleCSV.class)
                .withSkipLines(6)
                .build()
                .parse();


        return sampleCSVs
                .stream()
                .filter(s -> s.getSampleName() != null)
                .map(s -> mapToSample(s))
                .collect(Collectors.toList());
    }


    public String getHeader(List<String> inputPaths) throws FileNotFoundException {

        if (inputPaths.isEmpty()) {
            return "";
        }

        String header = "";
        List<Sample> samples = getSamples(inputPaths.get(0));

        String sampleName = samples.get(0).getSampleName();
        StringBuilder sb = new StringBuilder();

        sb.append("sampleName");
        sb.append(",");
        sb.append("patientId");
        sb.append(",");
        sb.append("occurrences");
        sb.append(",");


        for (Sample sample : samples) {

            if (sampleName.equals(sample.getSampleName())) {
                sb.append(sample.getTargetName());
                sb.append(",");
            }
        }
        return sb.toString();

    }
}
