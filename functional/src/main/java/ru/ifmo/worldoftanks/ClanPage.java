package ru.ifmo.worldoftanks;

import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ifmo.PageObject;

import java.util.ArrayList;

@Getter
public class ClanPage extends PageObject {

    private final String url = "https://ru.wargaming.net/clans/wot/find_clan/";

    @FindBy(xpath = "//div[contains(text(), 'Заявка в клан') and contains(text(), 'успешно отправлена')]")
    private WebElement submissionDialogTitle;

    private WebElement chosenClanBlock;

    @FindBy(xpath = "//header[@class='info']//a[span[text()='Подобрать клан']]")
    private WebElement clanPortalLink;

    @FindBy(xpath = "//a[contains(@class, 'sidebar_toggle')]")
    private WebElement subMenuButton;

    public ClanPage(WebDriver driver) {
        super(driver);
    }

    public boolean submissionIsSuccessful() {
        return submissionDialogTitle.isDisplayed();
    }

    public void openClanPortal() {
        clanPortalLink.click();
        var portalTab = new ArrayList<>(driver.getWindowHandles()).get(1);
        driver.switchTo().window(portalTab);
    }

    public void selectClanWith(@NonNull String clanName) {
        chosenClanBlock = findClanBlockFor(clanName);
        chosenClanBlock.click();
    }

    public void sendRequestWith(@NonNull String message) {
        fillReasonToAcceptWith(message);
        clickSendRequestButton();
    }

    public void closeSubMenu() {
        subMenuButton.click();
    }

    private WebElement findClanBlockFor(String clanName) {
        String xpathFormat = "//div[contains(@class, 'tbl-requests_tr')]/descendant::span[text()='%s']";
        String xpath = String.format(xpathFormat, clanName);
        return driver.findElement(By.xpath(xpath));
    }

    private void fillReasonToAcceptWith(@NonNull String message) {
        WebElement reasonToAcceptTextArea = chosenClanBlock.findElement(By.xpath("//textarea"));
        reasonToAcceptTextArea.sendKeys(message);
    }

    private void clickSendRequestButton() {
        WebElement sendRequestButton = chosenClanBlock.findElement(By.xpath("//button/child::span[text()='Отправить заявку']"));
        sendRequestButton.click();
    }

}
