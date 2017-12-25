package models;

import javafx.stage.FileChooser;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportPrinter {
    private String outputFileName;
    private PrintWriter writer;

    public ReportPrinter() throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.now();
        outputFileName = "report-"+dtf.format(localDate)+".txt";
        writer = new PrintWriter(outputFileName, "UTF-8");
    }

    public void printReport(String str) {
        if (str.split(" ")[0].equals("End-report")){
            writer.println(str.substring(11)+" Baht");
            writer.close();
        } else {
            writer.println(str);
        }
//        writer.println(totAll);
//        System.out.println("report has printed !");
    }
}
