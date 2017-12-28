package tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenTime {

    public String getTime(){
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        String genDate = formatDate.format(new Date());

        return genDate;
    }

    public String getTimePlusOneYear(String date){
        int day = Integer.parseInt(date.substring(8,10));
        int month = Integer.parseInt(date.substring(5,7));
        int year = Integer.parseInt(date.substring(0,4));

        if (month == 2 && day == 29){
            day = 28;
        }

        year++;
        String newDate = String.format("%04d-%02d-%02d",year,month,day);

        return newDate;
    }

}
