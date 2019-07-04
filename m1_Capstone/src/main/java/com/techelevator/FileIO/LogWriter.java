package com.techelevator.FileIO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter {

    public void WriteToLog(String msg, double pastBalance, double newBalance)throws IOException {

        File file = new File("log.txt");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

        try( FileWriter fileWriter = new FileWriter(file,true);
                 BufferedWriter buffered = new BufferedWriter(fileWriter)){
            buffered.write(formatter.format(date) + " " + msg + " " + "$" + pastBalance + " " + "$" + newBalance + "\n");
            buffered.flush();
        }
    }
}
