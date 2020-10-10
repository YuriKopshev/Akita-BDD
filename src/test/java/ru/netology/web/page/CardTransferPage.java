package ru.netology.web.page;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
    @Name("Страница перевода")
public class CardTransferPage extends AkitaPage {
    @Name("Сумма")
    @FindBy(css = "[data-test-id=amount] input.input__control")
    private SelenideElement amountField;
    @Name("Откуда")
    @FindBy(css = "[data-test-id=from] input.input__control")
    private SelenideElement fromField;
    @Name("Пополнить")
    @FindBy(css = "[data-test-id=action-transfer]")
    private SelenideElement button;



    public void transferMoney(DataHelper.CardInfo cardInfo, String sum) {
        amountField.setValue(String.valueOf(sum));
        fromField.setValue(cardInfo.getCardNumber());
        button.click();
    }

}

