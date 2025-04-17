package com.notes;

import com.notes.dataprovider.NoteDataProvider;
import com.notes.page.MainPage;
import com.notes.page.StaticsPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestNoteEdit extends BaseTest {
    @Test(dataProviderClass = NoteDataProvider.class, dataProvider = "note")
    public void testCreateNoteAndEdit(String title, String text, String expectedTitle) {
        StaticsPage page = new MainPage()
                .createNotes(1)
                .navigateToStatisticsPage()
                .openNote(1)
                .editNote()
                .clearTitleAndSetTitle(title)
                .cleatTextAndSetText(text)
                .saveChanges()
                .navigateToStatisticsPage();
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(page.getNoteTitle(1).getText()).isEqualTo(expectedTitle);
            softAssertions.assertThat(page.getNoteText(1).getText()).isEqualTo(text);
        });
    }
}
