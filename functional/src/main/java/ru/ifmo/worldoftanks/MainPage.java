package ru.ifmo.worldoftanks;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ru.ifmo.PageObject;

@Getter
public class MainPage extends PageObject {

    private final String url = "https://worldoftanks.ru";

    @FindBy(xpath = "//a[text() = 'создать аккаунт']")
    private WebElement registerLink;

    @FindBy(xpath = "//div[@class = 'cm-menu_static']/a[span[text() = 'Центр поддержки']]")
    private WebElement supportLink;

    @FindBy(xpath = "//a[contains(text(), 'Кланы') and @class='nav-submenu_link']")
    private WebElement clanCenterLink;

    @FindBy(xpath = "//a[contains(@class, 'cm-link') and text() = 'Войти']")
    private WebElement enterLink;

    @FindBy(xpath = "//a[contains(@class, 'cm-user-menu-link')]")
    private WebElement profileLink;

    @FindBy(xpath = "//a[contains(@class, 'cm-footer-logout_link') and contains(text(), 'Выйти')]")
    private WebElement exitLink;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAuthenticated() {
        return profileLink.isDisplayed();
    }

    public boolean isNotAuthenticated() {
        return !this.isAuthenticated();
    }

    public RegistrationPage clickRegister() {
        registerLink.click();
        return new RegistrationPage(driver);
    }

    public SupportPage clickSupport() {

        supportLink.click();
        return new SupportPage(driver);
    }

    public ClanPage clickClan() {
        clanCenterLink.click();
        return new ClanPage(driver);
    }

    public LoginPage clickEnter() {
        enterLink.click();
        return new LoginPage(driver);
    }

    public MainPage clickExit() {
        if (this.isAuthenticated()) {
            profileLink.click();
            exitLink.click();
        } else {
            throw new IllegalStateException();
        }
        return new MainPage(driver);
    }

}
