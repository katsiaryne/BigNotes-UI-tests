package com.notes.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.notes.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.notes.util.PageConstant.DEFAULT_NOTE_TEXT;
import static com.notes.util.PageConstant.DEFAULT_NOTE_TITLE;

@Getter
public class MainPage extends BasePage<MainPage> {
    private final SelenideElement titleField = $(".baseInput__field");
    private final SelenideElement textAreaField = $(".baseTextarea__text");
    private final SelenideElement submitNoteButton = $(".pageCreate__baseButton");
    private final SelenideElement pageCreateNoteButton = $(".pageCreate__baseButtonAdd");
    private final SelenideElement notesContent = $(".vb-content");
    private final ElementsCollection articles = $$x("//div[@class='articlePreview pageCreate__articlePreview']");
    public final By deleteNoteLocator = byXpath(".//button[@class='articlePreview__button'][2]");
    private final By articleTitleLocator = byClassName("articlePreview__title");
    private final By articleTextLocator = byClassName("articlePreview__text");

    public MainPage scrollNotesContent() {
        executeJavaScript("arguments[0].scrollTop = arguments[0].scrollHeight;", notesContent);
        return this;
    }

    public PageCreate openCreatePage() {
        pageCreateNoteButton.click();
        return new PageCreate();
    }

    public MainPage deleteFirstArticle() {
        actions().moveToElement(getFirstArticle()).perform();
        actions().moveToElement(getFirstArticle().find(deleteNoteLocator).shouldBe(visible))
                .click()
                .perform();
        return this;
    }

    public SelenideElement getFirstArticleText() {
        return getFirstArticle().find(articleTextLocator).shouldBe(visible);
    }

    public SelenideElement getFirstArticleTitle() {
        return getFirstArticle().find(articleTitleLocator).shouldBe(visible);
    }

    private SelenideElement getFirstArticle() {
        return articles.first().shouldBe(exist);
    }

    public MainPage setTitle(String title) {
        return setField(titleField, title);
    }

    public MainPage setText(String text) {
        return setField(textAreaField, text);
    }

    public MainPage createNotes(int count) {
        for (int i = 0; i < count; i++) {
            setTitle(DEFAULT_NOTE_TITLE + i);
            setText(DEFAULT_NOTE_TEXT);
            submitNote();
        }
        return this;
    }

    public MainPage submitNote() {
        return clickButton(submitNoteButton);
    }
}
