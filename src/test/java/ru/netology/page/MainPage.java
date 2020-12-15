package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private SelenideElement buyButton = $$(".button").find(exactText("Купить"));
    private SelenideElement buyByCredit = $$(".button").find(exactText("Купить в кредит"));
    private SelenideElement nameOfSelectedPayMethod = $("#root > div > h3");

    public CardData buyByDebitCard() {
        buyButton.click();
        nameOfSelectedPayMethod.shouldHave(exactText("Оплата по карте"));
        return new CardData();
    }

     public CardData buyByCreditCard() {
        buyByCredit.click();
        nameOfSelectedPayMethod.shouldHave(exactText("Кредит по данным карты"));
        return new CardData();
    }

}
