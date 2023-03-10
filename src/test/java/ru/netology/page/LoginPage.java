package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static ru.netology.data.DataHelper.AuthInfo.*;


public class LoginPage {
  private final SelenideElement fieldLogin = $x("//*[@data-test-id='login']//input");
  private final SelenideElement fieldPassword = $x("//*[@data-test-id='password']//input");
  private final SelenideElement buttonContinue = $x("//*[@data-test-id='action-login']");
  private static final SelenideElement errorNotification = $x("//div[contains(@class, 'notification__content')]");

  public static String getNotificationError() {
    return errorNotification.should(Condition.visible).getText().trim();
  }

  public LoginPage validAuth() {
    fieldLogin.setValue(login);
    fieldPassword.setValue(password);
    buttonContinue.click();
    DashboardPage.confirmation();
    return this;
  }

  public LoginPage invalidLogin() {
    fieldLogin.setValue(invalidLogin);
    fieldPassword.setValue(password);
    buttonContinue.click();
    return this;
  }

  public LoginPage invalidPassword() {
    fieldLogin.setValue(login);
    fieldPassword.setValue(invalidPassword);
    buttonContinue.click();
    return this;
  }
}
