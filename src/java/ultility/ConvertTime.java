/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ultility;

import java.sql.Time;

/**
 *
 * @author win
 */
public class ConvertTime {

    // Method to convert total seconds to Time object
    public static Time secondsToTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return Time.valueOf(timeString);
    }

    // Method to convert Time object to total seconds
    public static int timeToSeconds(Time time) {
        long milliseconds = time.getTime();
        return (int) (milliseconds / 1000);
    }

    public static int convertTimeToSeconds(String timeString) {
        // Split the time string by ":"
        String[] parts = timeString.split(":");

        // Extract hours, minutes, and seconds
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        // Calculate total seconds
        return hours * 3600 + minutes * 60 + seconds;
    }

    public static String convertSecondsToHHMMSS(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d%02d%02d", hours, minutes, seconds);
    }
}
