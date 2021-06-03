package ru.ifmo.worldoftanks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.ifmo.AbstractUiTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends AbstractUiTest {

    @ParameterizedTest
    @CsvSource("ladymofetta66@mail.ru,SchoolAmNotBest,jesusCHR1ST")
    public void registerUserWith(String email, String username, String password) {

        var mainPage = new MainPage(getDriver());
        waitUntilClickable(mainPage.getRegisterLink());
        var registrationPage = mainPage.clickRegister();
        waitUntilClickable(registrationPage.getEmailField());
        var completeRegistrationPage = registrationPage.registerUserWith(email, username, password);
        waitUntilClickable(completeRegistrationPage.getCompleteRegistrationText());
        assertTrue(completeRegistrationPage.registrationWasSuccessful());
    }

}
