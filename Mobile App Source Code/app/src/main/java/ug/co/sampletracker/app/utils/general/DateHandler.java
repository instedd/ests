package ug.co.sampletracker.app.utils.general;

import android.database.Cursor;
import android.util.Log;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import ug.co.sampletracker.app.utils.constants.ConstStrings;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/22/2017.
 */

public class DateHandler {
    public static Long persistDate(Date date) {

        if (date != null) {
            return date.getTime();
        }
        return null;

    }

    public static Date loadDate(Cursor cursor, int dateColumnIndex) {

        if (cursor.isNull(dateColumnIndex)) {
            return null;
        }
        return new Date(cursor.getLong(dateColumnIndex));

    }

    public static Date getDateObjectFromDateString(String dateString) {
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat(ConstStrings.DATE_FORMAT_TXN);
        try {
            Date date = simpleDateFormat.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean dateIsBeforeToday(Date selectedDate) {

       Date today =  DateHandler.dateTodayMidnight();
        return ! new DateHandler().checkIfEndDateIsNotBeforeStartDate(today, selectedDate);

    }

    private static Date dateTodayMidnight() {

        // today
        Calendar date = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        // next day
        // date.add(Calendar.DAY_OF_MONTH, 1);

        return date.getTime();
    }

    public String getCurrentYear() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getCurrentMonth() {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getCurrentDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getTransactionYear(String inputDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date(inputDate);
        return dateFormat.format(date);
    }

    public String getTransactionMonth(String inputDate) {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date(inputDate);
        return dateFormat.format(date);
    }

    public String getTransactionDay(String inputDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date(inputDate);
        return dateFormat.format(date);
    }

    public String getPaymentDate(String transactionDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(transactionDate.replace("-", "/"));
        return dateFormat.format(date);
    }

    public String getPaymentYear(String transactionDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date(transactionDate);
        return dateFormat.format(date);
    }

    public String getPaymentMonth(String transactionDate) {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date(transactionDate);
        return dateFormat.format(date);
    }

    public String getPaymentDay(String transactionDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date(transactionDate);
        return dateFormat.format(date);
    }

    public String getPaymentMinutes(String transactionDate) {
        DateFormat dateFormat = new SimpleDateFormat("mm");
        Date date = new Date(transactionDate);
        return dateFormat.format(date);
    }

    public String getPaymentHour(String transactionDate) {
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date(transactionDate);
        return dateFormat.format(date);
    }

    public String getDateNarative() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getHourNarative() {
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getMinuteNarative() {
        DateFormat dateFormat = new SimpleDateFormat("mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String generateRandomString() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(20, random).toString(11);
    }

    public String getCurrentTimeAndDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getPaywayTimeAndDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String date_part = dateFormat.format(date);
        // HH:mm:ss.SSS
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        Date time = new Date();
        String time_part = timeFormat.format(time);
        time_part += "000+06:00";
        return date_part.trim() + "T" + time_part.trim();
    }

    public String getTimeOfTransaction() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd : HH:mm:ss");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        return currentDateString;
    }

    public String getUsernameLastNumbers() {
        DateFormat dateFormat = new SimpleDateFormat("ddss");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        return currentDateString;
    }

    public String getOtherNumbers() {
        DateFormat dateFormat = new SimpleDateFormat("MMmm");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        return currentDateString;
    }

    public String getInterswitchRef() {
        DateFormat dateFormat = new SimpleDateFormat("ddssmm");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        return currentDateString;
    }

    public String getCurrentTimeInMilliSeconds() {
        return "" + new Date().getTime();
    }

    public String getCurrentTimeInSeconds() {
        return "" + new Date().getSeconds();
    }

    public String getYearTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        return currentDateString;
    }

    public String formatStringIntoParsableDate(String date, String format) {
        String[] temp_array_of_strings = date.split(" ");
        String final_date_string_month = "";
        String final_date_string_date = "";
        String final_date_string_year = "";
        if (format.equalsIgnoreCase("dd/mm/yyyy")) {
            final_date_string_month = this.getMonthNumber(temp_array_of_strings[0].trim());
            final_date_string_date = temp_array_of_strings[1];
            final_date_string_year = temp_array_of_strings[2];
        }
        return final_date_string_date + "/" + final_date_string_month + "/" + final_date_string_year;
    }

    public String getMonthNumber(String month_name) {
        if (month_name.equalsIgnoreCase("January")) {
            return "01";
        } else if (month_name.equalsIgnoreCase("February")) {
            return "02";
        } else if (month_name.equalsIgnoreCase("March")) {
            return "03";
        } else if (month_name.equalsIgnoreCase("April")) {
            return "04";
        } else if (month_name.equalsIgnoreCase("May")) {
            return "05";
        } else if (month_name.equalsIgnoreCase("June")) {
            return "06";
        } else if (month_name.equalsIgnoreCase("July")) {
            return "07";
        } else if (month_name.equalsIgnoreCase("August")) {
            return "08";
        } else if (month_name.equalsIgnoreCase("September")) {
            return "09";
        } else if (month_name.equalsIgnoreCase("October")) {
            return "10";
        } else if (month_name.equalsIgnoreCase("November")) {
            return "11";
        } else if (month_name.equalsIgnoreCase("December")) {
            return "12";
        } else {
            return null;
        }
    }

    public Calendar getCurrentCalendarDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar;
    }

    public Calendar getCalendarObjectFromStringDate(String date_string, String format) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(date_string);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            System.out.println("TimeHandler#getCalendarObjectFromStringDate exception : " + e.getMessage());
            return null;
        }
    }

    public Date getDateObjectFromStringDate(String date_string, String format) {
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(date_string);

            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            System.out.println("TimeHandler#getCalendarObjectFromStringDate exception : " + e.getMessage());
            return null;
        }
    }

    public String getDateStringFromCalendar(Calendar calendar_object) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(calendar_object.getTime());
    }

    public String getCurrentDateString() {
        DateFormat dateFormat = new SimpleDateFormat(ConstStrings.DATE_FORMAT_TXN);
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    public String getPegasusCurrentDateString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    public String getDateStringAfterSetDays(int num_of_days) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = this.getCalendarAfterSetDays(num_of_days);
        return dateFormat.format(calendar.getTime());
    }

    public Calendar getCalendarAfterSetDays(int number_of_days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, number_of_days);
        return calendar;
    }

    public String getDateAndTimeFromCalendarObject(Calendar calendar_object) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date_object = calendar_object.getTime();
        return dateFormat.format(date_object);
    }

    public static Date getFirstDateOfThisMonth() {
        String[] dateTokens = new SimpleDateFormat("dd-MM-yyyy").format(new Date()).split("-");
        return new GregorianCalendar(Integer.parseInt(dateTokens[2]), Integer.parseInt(dateTokens[1]) - 1, 01)
                .getTime();
    }

    public static Date getLastDateOfThisMonth() {
        String[] dateTokens = new SimpleDateFormat("dd-MM-yyyy").format(new Date()).split("-");
        return new GregorianCalendar(Integer.parseInt(dateTokens[2]), Integer.parseInt(dateTokens[1]) - 1, 31)
                .getTime();
    }

    public String getAfricellTimeOfTransaction() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        return currentDateString;
    }

    public String getAfricellMoneyTimeOfTransaction() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        return currentDateString;
    }

    public void testDateString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Date 1 :" + format.format(getFirstDateOfThisMonth()));
        System.out.println("Date 2 :" + format.format(getLastDateOfThisMonth()));
    }

    public String generatePasswordString() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public boolean checkIfDatesAreTheSame(Date startDate, Date endDate)
    {
        boolean isEqual = false;

        if (startDate.compareTo(endDate) > 0) {
            isEqual = false;
        }else if(startDate.compareTo(endDate) < 0) {
            isEqual = false;
        }else if(startDate.compareTo(endDate) == 0){
            isEqual = true;
        }
        return isEqual;
    }

    public boolean checkIfEndDateIsNotBeforeStartDate(Date startDate, Date endDate)
    {


        Log.e(DateHandler.class.getName(),"Start date : "+startDate+" End dat : "+endDate);
        boolean isAfter = false;
        if (endDate.compareTo(startDate) > 0) {
            Log.e(DateHandler.class.getName(),"Action >0");
            isAfter = true;
        }else if(endDate.compareTo(endDate) < 0) {
            Log.e(DateHandler.class.getName(),"Action <0");
            isAfter = false;
        }else if(startDate.compareTo(endDate) == 0){
            Log.e(DateHandler.class.getName(),"Action ==0");
            isAfter = true;
        }
        return isAfter;


       /* Log.e(DateHandler.class.getName(),"Start date : "+startDate+" End dat : "+endDate);
        boolean isAfter = false;
        if (endDate.compareTo(startDate) > 0) {
            isAfter = true;
        }else if(endDate.compareTo(endDate) < 0) {
            isAfter = false;
        }else if(startDate.compareTo(endDate) == 0){
           // isAfter = true;
            isAfter = true;
        }
        return isAfter;*/
    }

    public boolean checkIfStartDateIsEqualToCurrentDate(Date start_date){
        boolean isEqual = false;

        try {

            int currentYear = 0;
            int currentMonth = 0;
            int currentDay   = 0;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            long currentTime = System.currentTimeMillis();
            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(currentTime);

            currentYear = now.get(Calendar.YEAR);
            currentMonth = now.get(Calendar.MONTH) + 1;
            currentDay   = now.get(Calendar.DATE);

            String currentDate = currentYear+"-"+currentMonth+"-"+currentDay;

            if (start_date.compareTo(sdf.parse(currentDate)) > 0) {
                isEqual = true;
            }else if(start_date.compareTo(sdf.parse(currentDate)) < 0){
                isEqual = false;
            }else if(start_date.compareTo(sdf.parse(currentDate)) == 0){
                isEqual = true;
            }
        } catch (ParseException ex) {
            Logger.getLogger(DateHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isEqual;
    }



    //         public void disAbleDateEditing(JDateChooser dateChooser)
//        {
//            Date date = new Date();
//             JTextFieldDateEditor recordDate = (JTextFieldDateEditor) dateChooser.getDateEditor();
//             recordDate.setDate(date);
//             recordDate.setEditable(false);
//        }
//


    public static String getTimeOfDay() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        return " "+currentDateString;
    }
    public String getDateStringBeforeSetDays(int num_of_days) {
        DateFormat dateFormat = new SimpleDateFormat(ConstStrings.DATE_FORMAT_TXN);
        Calendar calendar = this.getCalendarAfterSetDays(num_of_days*-1);
        return dateFormat.format(calendar.getTime());
    }

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }

    public String getHumanReadableDate(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        String dateString = dateFormat.format(date);
        return dateString;
    }

    public static Date dateTimeNow() {
        return new Date();
    }

    public static Date buildDate(int year, int month, int day) {

        try{

            Calendar calendar = new GregorianCalendar(year,month,day);
            return calendar.getTime();

        }catch (Exception ex){

            return new Date();

        }

    }
}
