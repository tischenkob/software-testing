package ru.ifmo.worldoftanks;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ifmo.PageObject;

public class LoginPage extends PageObject {

    @Getter
    private final String url = "https://ru.wargaming.net/id/signin/";

    @FindBy(xpath = "//input[@id='id_email']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@id='id_password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button/child::span[@text='Войти']")
    private WebElement enterButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage enterWith(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        enterButton.click();
        return new MainPage(driver);
    }


}
