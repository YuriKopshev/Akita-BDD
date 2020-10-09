package ru.netology.web.step;

import cucumber.api.PendingException;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import lombok.val;
import ru.alfabank.alfatest.cucumber.api.AkitaScenario;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.page;
import static com.galenframework.suite.GalenPageActions.open;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static ru.alfabank.tests.core.helpers.PropertyLoader.loadProperty;

public class TemplateSteps {
  private final AkitaScenario scenario = AkitaScenario.getInstance();
    private String secondCard;

    @Пусть("^пользователь залогинен с именем \"([^\"]*)\" и паролем \"([^\"]*)\"$")
  public void loginWithNameAndPassword(String login, String password) {
    val loginUrl = loadProperty("loginUrl");
    open(loginUrl);

    scenario.setCurrentPage(page(LoginPage.class));
    val loginPage = (LoginPage) scenario.getCurrentPage().appeared();
    val authInfo = new DataHelper.AuthInfo(login, password);
    scenario.setCurrentPage(loginPage.validLogin(authInfo));

    val verificationPage = (VerificationPage) scenario.getCurrentPage().appeared();
    val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    scenario.setCurrentPage(verificationPage.validVerify(verificationCode));

    scenario.getCurrentPage().appeared();
  }

  @Когда("^он переводит \"([^\"]*)\" рублей с карты с номером \"([^\"]*)\" на свою \"([^\"]*)\" карту с главной страницы;$")
  public void transferMoneySecondToFirstCard(String sum, String secondCard, String firstCard) throws Throwable {
   val dashboardPage = (DashboardPage) scenario.getCurrentPage().appeared();
    val transferPage = dashboardPage.chooseSecondCard();
    transferPage.transferMoney(DataHelper.getFirstCardNumber(), sum);

  }

    @Тогда("^баланс его \"([^\"]*)\" карты из списка на главной странице должен стать \"([^\"]*)\" рублей\\.$")
    public void balanceFirstCardVerify(String firstCard, String sum) throws Throwable {
        val dashBoardPage = (DashboardPage) scenario.getCurrentPage().appeared();
        val actualResultFirst = dashBoardPage.getFirstCardBalance();
        assertEquals(actualResultFirst,sum);


    }
}
