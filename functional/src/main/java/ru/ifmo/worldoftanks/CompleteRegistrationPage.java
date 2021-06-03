package ru.ifmo.worldoftanks;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ifmo.PageObject;

public class CompleteRegistrationPage extends PageObject {
    @Getter
    private final String url = "https://ru.wargaming.net/registration/ru/verify";

    @Getter
    @FindBy(xpath = "//h1[contains(text(), 'АККАУНТ СОЗДАН')]")
    private WebElement completeRegistrationText;

    public CompleteRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public boolean registrationWasSuccessful() {
        return completeRegistrationText.isDisplayed();
    }
}
