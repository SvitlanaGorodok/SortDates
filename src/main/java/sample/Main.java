package sample;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


public class Main {
    public static void main(String[] args) {
        List<LocalDate> unsortedDates = List.of(toDate("2005-07-01"), toDate("2005-01-02"),
                toDate("2005-01-01"), toDate("2005-05-03"));
        DateSorter sorter = new DateSorter("r", Locale.ENGLISH);
        System.out.println(sorter.sortDates(unsortedDates));
        sorter.setLocale(Locale.FRENCH);
        sorter.setSortParam("u");
        System.out.println(sorter.sortDates(unsortedDates));
    }

    private static LocalDate toDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}