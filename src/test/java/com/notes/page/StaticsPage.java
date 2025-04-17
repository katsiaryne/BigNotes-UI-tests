package com.notes.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.notes.page.base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.executeJavaScript;

@Getter
public class StaticsPage extends BasePage<StaticsPage> {
    private final SelenideElement pageStatisticDate = $(".pageStatistic__date");
    private final SelenideElement calendarMonthYear = $(".day__month_btn");
    private static final String calendarLocatorString = ".vdp-datepicker.baseCalendar.pageStatistic__calendar";
    private final ElementsCollection notesList = $$(".articlePreview.pageStatistic__article");
    private final By noteTitleLocator = byClassName("articlePreview__title");
    private final By noteTextLocator = byClassName("articlePreview__text");
    private final By deleteNoteButtonLocator = byXpath("//button[@class='articlePreview__button'][2]");
    private final By editNoteButtonLocator = byXpath("//button[@class='articlePreview__button'][2]");

    public StaticsPage setDate(String day, String month, String year) {
        pageStatisticDate.shouldBe(visible);
        executeJavaScript("document.querySelector(arguments[3]).__vue__.setDate(new Date(arguments[0],arguments[1],arguments[2]))", year, month, day, calendarLocatorString);
        return this;
    }

    public SelenideElement getNoteTitle(int index) {
        return findNote(index).find(noteTitleLocator).shouldBe(visible);
    }

    public SelenideElement getNoteText(int index) {
        return findNote(index).find(noteTextLocator).shouldBe(visible);
    }

    public StaticsPage deleteNote(int index) {
        SelenideElement note = findNote(index);
        actions().moveToElement(note).perform();
        actions().moveToElement(note.find(deleteNoteButtonLocator).shouldBe(visible))
                .click()
                .perform();
        return this;
    }

    public NotePage openNote(int index) {
        actions().moveToElement(findNote(index)).click().perform();
        return new NotePage();
    }

    public NotePage editNote(int index) {
        SelenideElement note = findNote(index);
        actions().moveToElement(note).perform();
        actions().moveToElement(note.find(editNoteButtonLocator).shouldBe(visible))
                .click()
                .perform();
        return new NotePage();
    }

    private SelenideElement findNote(int index) {
        return notesList.get(index);
    }
}
