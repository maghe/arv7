package it.santini.arv7;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sample {
    private String sampleName;
    private String patientId;
    private String occurrence;
    private String targetName;
    private String cqMeanVerified;
}
