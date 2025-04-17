package com.notes.page.components;

import com.codeborne.selenide.SelenideElement;
import com.notes.page.MainPage;
import com.notes.page.StaticsPage;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class Navigation {
    private final SelenideElement mainPageButton = $x("//div[@class='theLayout__menuBody']//a[1]");
    private final SelenideElement statsPageButton = $x("//div[@class='theLayout__menuBody']//a[2]");

    public StaticsPage navigateToStatisticsPage() {
        statsPageButton.click();
        return new StaticsPage();
    }

    public MainPage navigateToMainPage() {
        mainPageButton.click();
        return new MainPage();
    }
}
