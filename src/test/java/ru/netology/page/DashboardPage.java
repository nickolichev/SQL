package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
  private static final SelenideElement heading = $x("//*[@data-test-id='dashboard']");
  private static final SelenideElement confirmation = $x("//p[contains(@class, 'paragraph_theme_alfa-on-white')]");

  public static String heading() {
    return heading.getText().trim();
  }

  public static SelenideElement confirmation() {
    return confirmation.should(Condition.visible);
  }
}
