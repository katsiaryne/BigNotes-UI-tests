package com.notes.dataprovider;

import org.testng.annotations.DataProvider;

public class DatePickerDataProvider {
    @DataProvider(name = "dates")
    private Object[][] getDates() {
        return new Object[][]{
                {"11", "11", "2024", "11 декабря 2024", "Декабрь 2024"},
                {"1", "1", "2025", "1 февраля 2025", "Февраль 2025"}
        };
    }
}
