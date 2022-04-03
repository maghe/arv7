package it.santini.arv7;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class SampleCSV {

    @CsvBindByPosition(position = 0)
    private   String well;
    @CsvBindByPosition(position = 1)
    private String sampleName;
    @CsvBindByPosition(position = 2)
    private String targetName;
    @CsvBindByPosition(position = 3)
    private Double ampScore;
    @CsvBindByPosition(position = 7)
    private String cqMean;
}
