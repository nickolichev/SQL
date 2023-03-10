package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static ru.netology.data.DataHelper.VerificationCode.*;

public class VerificationPage {
  private final SelenideElement verificationCode = $x("//*[@data-test-id='code']//input");
  private final SelenideElement buttonContinue = $x("//*[@data-test-id='action-verify']");
  private static final SelenideElement errorNotificationCode = $x("//div[contains(@class, 'notification__content')]");

  public static String errorNotificationCode() {
    return errorNotificationCode.should(Condition.visible).getText().trim();
  }

  public VerificationPage validCode() {
    verificationCode.shouldBe(Condition.visible);
    verificationCode.doubleClick().sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.BACK_SPACE);
    verificationCode.setValue(code);
    buttonContinue.click();
    return this;
  }

  public VerificationPage invalidCode() {
    verificationCode.shouldBe(Condition.visible);
    verificationCode.setValue(invalidCode);
    buttonContinue.click();
    return this;
  }
}
