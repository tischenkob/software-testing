package ru.ifmo.worldoftanks;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ifmo.PageObject;

@Getter
public class RegistrationPage extends PageObject {

    private final String url = "https://ru.wargaming.net/registration/ru";

    @FindBy(xpath = "//input[@type = 'email' and @name = 'login']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id = 'id_name']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@id = 'id_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id = 'id_re_password']")
    private WebElement repeatPasswordField;

    @FindBy(xpath = "//label[contains(@class, 'checkbox-game-label') and @for='id_eula']")
    private WebElement licenseAgreementCheckbox;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement continueButton;

    @FindBy(xpath = "//a[@data-suggestion][1]")
    private WebElement suggestedNameButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public CompleteRegistrationPage registerUserWith(String email, String name, String password) {
        emailField.sendKeys(email);
        nameField.sendKeys(name);
//        if (suggestedNameButton.isDisplayed()) {
//            suggestedNameButton.click();
//        }
        passwordField.sendKeys(password);
        repeatPasswordField.sendKeys(password);
        licenseAgreementCheckbox.click();

        continueButton.click();

        new WebDriverWait(driver, 7);

        return new CompleteRegistrationPage(driver);
    }

}
