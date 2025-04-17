package com.notes.page.base;

import com.codeborne.selenide.SelenideElement;
import com.notes.page.StaticsPage;
import com.notes.page.components.Navigation;
import lombok.Getter;

import static com.codeborne.selenide.Condition.editable;

@Getter
public abstract class BasePage<T extends BasePage<T>> {
    protected final Navigation navigationComponent;

    public BasePage() {
        this.navigationComponent = new Navigation();
    }

    public StaticsPage navigateToStatisticsPage() {
        return navigationComponent.navigateToStatisticsPage();
    }

    protected T setField(SelenideElement element, String value) {
        element.shouldBe(editable).click();
        element.sendKeys(value);
        return (T) this;
    }

    protected T clearFieldAndSetText(SelenideElement element, String text) {
        element.shouldBe(editable).click();
        element.clear();
        element.sendKeys(text);
        return (T) this;
    }

    protected T clickButton(SelenideElement button) {
        button.click();
        return (T) this;
    }
}
