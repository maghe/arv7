package it.santini.arv7;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainOpenCSV {


    public static void main(String[] args) throws Exception {

        MainManager mainManager = new MainManager();

     //   String folderPath = "C:\\Users\\048115485\\workspace-experiment\\ARV7\\input\\";
        //String folderPath = "C:\\Users\\048115485\\workspace-experiment\\ARV7\\input\\card ARV7 _1 01.01.22.sds";
        mainManager.organize(args[0]);
    }
}
