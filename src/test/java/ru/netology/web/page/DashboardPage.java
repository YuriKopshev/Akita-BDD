package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

import static com.codeborne.selenide.Selenide.*;

@Name("Дашбоард")
public class DashboardPage extends AkitaPage {
  @FindBy(css = "[data-test-id=dashboard]")
  private SelenideElement heading;
  @FindBy(css = "[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"] .button")
  private SelenideElement firstCard;
  @FindBy(css = "[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"] .button")
  private SelenideElement secondCard;

  private ElementsCollection cards = $$(".list__item");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";


  public CardTransferPage chooseFirstCard() {
    firstCard.click();
    return page(CardTransferPage.class);
  }

  public CardTransferPage chooseSecondCard() {
    secondCard.click();
    return new CardTransferPage();
  }

  public int getFirstCardBalance() {
    val text = cards.first().text();
    return extractBalance(text);
  }

  public int getSecondCardBalance() {
    val text = cards.last().text();
    return extractBalance(text);
  }

  private int extractBalance(String text) {
    val start = text.indexOf(balanceStart);
    val finish = text.indexOf(balanceFinish);
    val value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }
}