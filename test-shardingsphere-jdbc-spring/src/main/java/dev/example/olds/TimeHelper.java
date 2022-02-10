package dev.example.olds;

import com.google.common.collect.Lists;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class TimeHelper {

    public static LocalDate min(LocalDate x, LocalDate y) {
        return x.compareTo(y) < 0 ? x : y;
    }

    public static LocalDate max(LocalDate x, LocalDate y) {
        return x.compareTo(y) > 0 ? x : y;
    }

    public static List<LocalDateRange> splitByMonth(LocalDate start, LocalDate end) {
        // 确保 s <= e; 避免下方算法进入 死循环;
        LocalDate s = min(start, end);
        LocalDate e = max(start, end);

        // 属于 同一月份
        if (s.getYear() == e.getYear() && s.getMonthValue() == e.getMonthValue()) {
            return Lists.newArrayList(LocalDateRange.of(s, e));
        }

        // 包含 多个月份
        List<LocalDateRange> list = new ArrayList<>();
        while (s.compareTo(e) <= 0) {
            // 月底
            LocalDate last = min(e, s.with(TemporalAdjusters.lastDayOfMonth()));
            list.add(LocalDateRange.of(s, last));
            // 下个月初
            s = last.plusDays(1L);
        }
        return list;
    }
}
