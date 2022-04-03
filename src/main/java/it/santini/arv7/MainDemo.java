package it.santini.arv7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainDemo {


    public static void main(String[] args) throws Exception {


        // File path is passed as parameter
        File file = new File(
                "C:\\Users\\048115485\\workspace-experiment\\ARV7\\input\\Results.csv");

        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)

        // Creating an object of BufferedReader class
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)

            //  System.out.println(st);

            if (st.startsWith("\"A")) {

                st = st.replace("\"", "");

                String[] record = st.split(",");

                String well = record[0];
                String sampleName = record[1].substring(1);
                String occurence = record[1].substring(0, 1);


                String targetName = record[2];
                Double ampScore = Double.valueOf(record[3]);
                String cqMean = record[7];

                if (ampScore < 0.8) {
                    cqMean = "-";
                }

                StringBuilder sb = new StringBuilder();
                sb.append(sampleName);
                sb.append(",");
                sb.append(occurence);
                sb.append(",");
                sb.append(cqMean);

                System.out.println(sb.toString());
            }
    }
}

