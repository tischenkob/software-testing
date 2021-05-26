package ru.ifmo.worldoftanks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainPageTest {

    WebDriver driver;
    String url = "https://worldoftanks.ru/";

    @BeforeAll
    static void setup() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new FirefoxDriver();
        driver.get(url);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void getTitle() {
        String title = driver.getTitle();
        System.out.println(title);
    }


    @ParameterizedTest(name = "Registration")    //ввести почту, пароль, выбрать доступный никнейм
    @ValueSource(strings = {"ITMO_SUPER97", "I_love_2007", "crazy@mail.ru"})
    public void registrationTest(String nickname, String password, String email) {
        WebElement registerButton = driver.findElement(By.xpath("helloWorld"));
    }

    //Войти на сайт и выйти -- ввести почту, пароль, войти на сайт, открыть личный кабинет, выйти
    //Подобрать клан -- открыть список кланов, добавить фильтры, выбрать конкретный клан, написать приветственное сообщение, оставить заявку на вступление
    //Обратиться в поддержку -- открыть форум поддержки, выбрать категорию, выбрать статью, под “Была ли статья полезной?” нажать кнопку “Да”


}
