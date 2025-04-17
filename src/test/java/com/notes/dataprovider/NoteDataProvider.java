package com.notes.dataprovider;

import org.testng.annotations.DataProvider;

public class NoteDataProvider {
    @DataProvider(name = "note")
    public Object[][] getNotes() {
        return new Object[][]{
                {"new title", "new text", "new title"},
                {"", "new text", "(Без темы)"}
        };
    }
}
