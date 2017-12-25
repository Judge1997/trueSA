package tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenTime {

    public String getTime(){
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        String genDate = formatDate.format(new Date());

        return genDate;
    }

    public String getTimePlusOneYear(String date){
        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,10));

        if (month == 2 && day == 29){
            day = 28;
        }

        year++;
        String newDate = String.format("%02d-%02d-%04d",day,month,year);

        return newDate;
    }

}
