package ru.netology.test;

import ru.netology.data.Cleaner;
import org.junit.jupiter.api.*;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.*;
import static ru.netology.page.DashboardPage.*;
import static ru.netology.page.LoginPage.*;
import static ru.netology.page.VerificationPage.errorNotificationCode;

public class AuthTest {

  private static final String url = "http://localhost:9999";
  private static final String errorLoginOrPassword = "Ошибка! Неверно указан логин или пароль";
  private static final String errorCode = "Ошибка! Неверно указан код! Попробуйте ещё раз.";
  private static final String personalAccount = "Личный кабинет";

  private LoginPage loginPage;
  private VerificationPage verificationPage;

  @BeforeEach
  public void setUp() {
    open(url);
    loginPage = new LoginPage();
    verificationPage = new VerificationPage();
  }

  @AfterAll
  static void cleanScript() {
    Cleaner.cleanupDataBase();
  }

  @Order(1)
  @Test
  void testInvalidLogin() {
    loginPage.invalidLogin();
    Assertions.assertEquals(errorLoginOrPassword, getNotificationError());

  }

  @Order(2)
  @Test
   void testInvalidPassword() {
    loginPage.invalidPassword();
    Assertions.assertEquals(errorLoginOrPassword, getNotificationError());
  }

  // Баг. Написано issue
  @Order(3)
  @Test
  void testAuthorization() {
    loginPage.validAuth();
    verificationPage.invalidCode();
    Assertions.assertEquals(errorCode, errorNotificationCode());
    verificationPage.invalidCode();
    Assertions.assertEquals(errorCode, errorNotificationCode());
    verificationPage.invalidCode();
    Assertions.assertEquals(errorCode, errorNotificationCode());
    verificationPage.validCode();
    Assertions.assertEquals(personalAccount, heading());
  }
}
