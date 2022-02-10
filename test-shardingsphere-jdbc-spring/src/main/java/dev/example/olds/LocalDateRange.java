package dev.example.olds;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LocalDateRange {
    private LocalDate start;
    private LocalDate end;

    public LocalDateRange() {
    }

    public LocalDateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public static LocalDateRange of(LocalDate start, LocalDate end) {
        return new LocalDateRange(start, end);
    }
}
