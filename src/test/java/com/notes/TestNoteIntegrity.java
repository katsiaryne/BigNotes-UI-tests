package com.notes;

import com.notes.page.MainPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestNoteIntegrity extends BaseTest {
    @Test(description = "Проверка целостности записей")
    public void testNoteIntegrity() {
        MainPage page = new MainPage()
                .createNotes(10)
                .deleteFirstArticle()
                .scrollNotesContent();
        assertThat(page.getArticles()).hasSizeGreaterThanOrEqualTo(9);
    }
}
