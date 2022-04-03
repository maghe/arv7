package it.santini.arv7;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class AggregateManager {
    public void aggregate(String fileName) {
        try {
            List<Sample> beans = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(Sample.class)
                    .withSkipLines(6)
                    .build()
                    .parse();


            String sampleName = "start";
            StringBuilder sb = new StringBuilder();
            for (Sample sample : beans) {


                //     sb.append(sampleName + ",");

                if (sampleName.equals(sample.getSampleName())) {
                    sb.append(sample.getCqMean() + ",");
                } else {
                    sampleName = sample.getSampleName();
                    System.out.println(sb.toString());
                    sb = new StringBuilder();
                    sb.append(sampleName + "," + sample.getCqMean() + ",");
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
