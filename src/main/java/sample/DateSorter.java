package sample;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateSorter implements IDateSorter {

    private String sortParam;
    private Locale locale;

    public DateSorter(String sortParam, Locale locale) {
        this.sortParam = sortParam;
        this.locale = locale;
    }

    @Override
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        List<LocalDate> firstPart = unsortedDates.stream()
                .filter(this::ifContainsParam)
                .sorted()
                .collect(Collectors.toList());
        List<LocalDate> secondPart = unsortedDates.stream()
                .filter(date -> !ifContainsParam(date))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return Stream.concat(
                        firstPart.stream(),
                        secondPart.stream())
                .collect(Collectors.toList());
    }

    private String monthName(LocalDate date) {
        return date.getMonth().getDisplayName(TextStyle.FULL, locale);
    }

    private boolean ifContainsParam(LocalDate date) {
        return monthName(date).toLowerCase().contains(sortParam);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }
}
