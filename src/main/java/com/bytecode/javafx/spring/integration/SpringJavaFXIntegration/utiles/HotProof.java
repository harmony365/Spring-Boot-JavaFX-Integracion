package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.utiles;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@SpringBootApplication
public class HotProof {

    public static LocalDate date = LocalDate.now();
    //public static Date date2 = new Date.

    public static void main(String[] args) {

       // data1();
      //diasEntreDosFechas(date, new Date("2022-10-18 23:30"));
      //diasEntreDosFechas2(date, new Date("2022-10-18 23:30"));
       // fecha1();
       // otra();

        // Given start Date
        String start_date = "19-10-2022 03:10:20";

        // Given end Date
        String end_date = "19-10-2022 07:30:50";

        // Function Call
        int resultado = findDifferenceDates(start_date, end_date, "H");
        if ( resultado > 3) System.out.println("Fecha de salida supera las 3 horas reglamentarias");
        System.out.println(findDifferenceDates(start_date, end_date, "H"));

    }

    // Function to print difference in
    // time start_date and end_date
    static int findDifferenceDates(String start_date, String end_date, String retorno)
    {

        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time = d2.getTime() - d1.getTime();

            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
            long difference_In_Seconds = (difference_In_Time  / 1000)  % 60;

            long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

            long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;

            long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days  = (difference_In_Time / (1000 * 60 * 60 * 24))  % 365;

            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds

            System.out.print(  "Difference " + "between two dates is: ");

            System.out.println( difference_In_Years + " years, "
                            + difference_In_Days + " days, "
                            + difference_In_Hours + " hours, "
                            + difference_In_Minutes + " minutes, "
                            + difference_In_Seconds + " seconds");

            switch (retorno) {
                case "Y":
                    return (int) difference_In_Years;
                case "D":
                    return (int) difference_In_Days;
                case "H":
                    return (int) difference_In_Hours;
                case "M":
                    return (int) difference_In_Minutes;
                case "S":
                    return (int) difference_In_Seconds;
                default:
                    return 0;
            }

        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }



    public static long diasEntreDosFechas(Date fechaDesde, Date fechaHasta){
        long startTime = fechaDesde.getTime() ;
        long endTime = fechaHasta.getTime();
        long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
        long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
        long dias = diasHasta - diasDesde;

        return dias;
    }

    public static double diasEntreDosFechas2(Date fechaInicial, Date fechaFinal){
        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));

        return dias;
    }
     static void data1(){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try
        {
            Date d1 = df.parse(String.valueOf(date));
            Date d2 = df.parse("2022-10-18 21:30:24");
            long diff = d1.getTime() - d2.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            System.out.println(days);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    static void fecha1(){
        //default time zone

        ZoneId defaultZoneId = ZoneId.systemDefault();

        //creating the instance of LocalDate using the day, month, year info
        LocalDate localDate = LocalDate.of(2022, 10, 19);
        LocalDate localDate2 = LocalDate.of(2022, 10, 19);

        //local date + atStartOfDay() + default time zone + toInstant() = Date
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        Date date2 = Date.from(localDate2.atStartOfDay(defaultZoneId).toInstant());

        //Displaying LocalDate and Date
        System.out.println("LocalDate is: " + localDate);
        System.out.println("Date is: " + date);
        System.out.println("dias: " + diasEntreDosFechas2(date,date2));


    }

    static void otra(){

        ZonedDateTime now = ZonedDateTime.now();
        //ZonedDateTime then = now.minusMonths(10).minusDays(19).minusMinutes(5).minusSeconds(40);

        ZonedDateTime then = now.minusHours(2).minusMinutes(50);
        ZonedDateTime then2 = now.plusHours(3).plusMinutes(30);

        //Duration duration = Duration.between(then2, now);
        Duration duration = Duration.between(now,then2);

        System.out.println(duration);
        System.out.println(duration.getSeconds());
        System.out.println(duration.toMinutes());
        System.out.println(duration.toHours());
        System.out.println(duration.toDays());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a z");
        DateTimeFormatter formatterWithoutZone = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a");

        /*ZonedDateTime zdtWithZoneOffset = ZonedDateTime.parse("2019-03-27 10:15:30 am -05:00", formatter);

        ZonedDateTime zdtInLocalTimeline = zdtWithZoneOffset.withZoneSameInstant(ZoneId.systemDefault());

        System.out.println(zdtWithZoneOffset);
        System.out.println(zdtInLocalTimeline);*/

        LocalDateTime ldt = LocalDateTime.parse("2019-03-27 10:15:30 am", formatterWithoutZone);

        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());

        System.out.println(zdt);


    }

}
