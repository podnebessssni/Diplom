package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardData {

    private SelenideElement cardNumber = $("input[type='text'][placeholder='0000 0000 0000 0000']");
    private SelenideElement cardMonth = $("input[type='text'][placeholder='08']");
    private SelenideElement cardYear = $("input[type='text'][placeholder='22']");
    private SelenideElement cardOwner = $$("form div:nth-child(3) .input__control").first();
    private SelenideElement cardCode = $("input[type='text'][placeholder='999']");
    private SelenideElement continueButton = $("form div:nth-child(4) .button__content");
    private SelenideElement successNotification = $(".notification_status_ok .notification__content");
    private SelenideElement errorMessage = $(".notification_status_error .notification__content");
    private SelenideElement wrongFormatNotification = $(".input__sub");


    public void fillCardInformationForSelectedWay(DataHelper.CardInformation cardInformation) {
        cardNumber.setValue(cardInformation.getNumber());
        cardMonth.setValue(cardInformation.getMonth());
        cardYear.setValue(cardInformation.getYear());
        cardOwner.setValue(cardInformation.getHolder());
        cardCode.setValue(cardInformation.getCvc());
        continueButton.click();
    }

    public void checkIfPaymentSuccessful() {
        successNotification.waitUntil(Condition.visible, 15000);
    }

    public void checkIfPaymentNotSuccessful() {
        errorMessage.waitUntil(Condition.visible, 15000);
    }

    public void checkIfWrongFormatOfField() {
        wrongFormatNotification.shouldBe(Condition.visible);
    }

}
