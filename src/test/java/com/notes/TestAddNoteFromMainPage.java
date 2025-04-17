package com.notes;

import com.notes.dataprovider.NoteDataProvider;
import com.notes.page.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class TestAddNoteFromMainPage {
    @Test(description = "Проверка добавления новых записей",
            dataProviderClass = NoteDataProvider.class,
            dataProvider = "note")
    public void testCreateNoteFromMainPage(String title, String text, String expectedTitle) {
        MainPage page = new MainPage()
                .setTitle(title)
                .setText(text)
                .submitNote();

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(page.getFirstArticleText().getText()).isEqualTo(text);
            softAssertions.assertThat(page.getFirstArticleTitle().getText()).isEqualTo(expectedTitle);
        });
    }
}
