package com.notes;

import com.notes.dataprovider.NoteDataProvider;
import com.notes.page.MainPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class TestAddNoteFromItemPage extends BaseTest {

    @Test(description = "Проверка добавления новых записей с новой страницы",
            dataProviderClass = NoteDataProvider.class,
            dataProvider = "note")
    public void testCreateNoteFromCreatePage(String title, String text, String expectedTitle) {
        MainPage page = new MainPage()
                .openCreatePage()
                .setTitle(title)
                .setText(text)
                .submitNote();

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(page.getFirstArticleTitle().getText()).isEqualTo(expectedTitle);
            softAssertions.assertThat(page.getFirstArticleText().getText()).isEqualTo(text);
        });
    }
}
