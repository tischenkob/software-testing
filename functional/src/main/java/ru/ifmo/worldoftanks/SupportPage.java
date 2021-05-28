package ru.ifmo.worldoftanks;

import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.ifmo.PageObject;

public class SupportPage extends PageObject {
    @Getter
    private final String url = "https://ru.wargaming.net/support/ru/products/wot/";

    public SupportPage(WebDriver driver) {
        super(driver);
    }

    public void clickCardWithCategory(@NonNull String name) {
        String xpathFormat = "//div[@class='category-card']/descendant::span[text()='%s']";
        String xpath = String.format(xpathFormat, name);
        var categoryCard = driver.findElement(By.xpath(xpath));
        categoryCard.click();
    }

    public void rateArticleWith(@NonNull String title) {
        clickArticleWith(title);
        rateArticle();
    }

    public boolean ratingIsSuccessful() {
        var ratingTitle = driver.findElement(By.xpath("//span[text()='Благодарим за отзыв!']"));
        return ratingTitle.isDisplayed();
    }

    private void rateArticle() {
        var xpath = "//div[@class='article-rate_buttons']/button[@data-msgid='Yes']";
        var rateButton = driver.findElement(By.xpath(xpath));
        rateButton.click();
    }

    private void clickArticleWith(@NonNull String title) {
        var article = driver.findElement(By.xpath(String.format("//a[text()='%s']", title)));
        article.click();
    }
}
