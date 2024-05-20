/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ultility;

/**
 *
 * @author KENSHIN
 */
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class TestClass {

    public static List<String> generateLabels(LocalDateTime fromDate, LocalDateTime toDate, String timeUnit) {
        List<String> chartLabels = new ArrayList<>();

        switch (timeUnit) {
            case "day":
                for (LocalDateTime date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    chartLabels.add(date.format(DateTimeFormatter.ofPattern("dd/MM")));
                }
                break;
            case "week":
                for (LocalDateTime date = fromDate; !date.isAfter(toDate); date = date.plusWeeks(1)) {
                    LocalDateTime endOfWeek = date.plusDays(6);
                    chartLabels.add(date.format(DateTimeFormatter.ofPattern("dd/MM")) + " - " + endOfWeek.format(DateTimeFormatter.ofPattern("dd/MM")));
                }
                break;
            case "month":
                for (LocalDateTime date = fromDate; !date.isAfter(toDate); date = date.plusMonths(1)) {
                    chartLabels.add(date.getMonth().toString());
                }
                break;
            case "year":
                for (LocalDateTime date = fromDate; !date.isAfter(toDate); date = date.plusYears(1)) {
                    chartLabels.add(String.valueOf(date.getYear()));
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid time unit: " + timeUnit);
        }

        return chartLabels;
    }

    public static void main(String[] args) {
        LocalDateTime fromDate = LocalDateTime.of(2024, 3, 15, 0, 0);
        LocalDateTime toDate = LocalDateTime.of(2024, 3, 17, 0, 0);
        String timeUnit = "day";

        List<String> chartLabels = generateLabels(fromDate, toDate, timeUnit);
        System.out.println(chartLabels);
    }
}
