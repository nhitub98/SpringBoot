package com.example.demoproject.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Timestamp stringToTimestamp(String date) {
            try {
                SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date parsedDate = datetimeFormatter.parse(date);
                return new Timestamp(parsedDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        public static void main(String[] args) {
            String date = "2024-04-16T19:15";
            Timestamp timestamp = stringToTimestamp(date);
            System.out.println("Timestamp: " + timestamp);
        }
    }
