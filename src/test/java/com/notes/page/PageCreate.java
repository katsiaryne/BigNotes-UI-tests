package com.notes.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class PageCreate {
    private final SelenideElement titleField = $(".popup__wrapper .baseInput__field");
    private final SelenideElement textAreaField = $(".popup__wrapper .baseTextarea__text");
    private final SelenideElement submitNoteButton = $(".popup__baseButton");

    public MainPage submitNote() {
        submitNoteButton.shouldBe(visible).click();
        return new MainPage();
    }

    public PageCreate setText(String text) {
        return setField(textAreaField, text);
    }

    public PageCreate setTitle(String title) {
        return setField(titleField, title);
    }

    private PageCreate setField(SelenideElement element, String value) {
        element.shouldBe(editable).click();
        element.sendKeys(value);
        return this;
    }
}
