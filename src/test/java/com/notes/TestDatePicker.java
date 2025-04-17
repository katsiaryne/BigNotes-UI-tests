package com.notes;

import com.notes.dataprovider.DatePickerDataProvider;
import com.notes.page.MainPage;
import com.notes.page.StaticsPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDatePicker extends BaseTest {
    @Test(description = "Проверка установки даты",
            dataProviderClass = DatePickerDataProvider.class,
            dataProvider = "dates")
    @Parameters
    public void testSetDatePicker(String day, String month, String year, String date, String monthAndYear) {
        StaticsPage page = new MainPage()
                .navigateToStatisticsPage()
                .setDate(day, month, year);
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(page.getPageStatisticDate().getText()).isEqualTo(date);
            softAssertions.assertThat(page.getCalendarMonthYear().getText()).isEqualTo(monthAndYear);
        });
    }
}
