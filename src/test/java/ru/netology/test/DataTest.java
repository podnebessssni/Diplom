package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.page.MainPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldSuccessWithValidDebitCard() throws SQLException {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByCreditCard();
        val validCardInformation = DataHelper.getValidCardInformation();
        CardData.fillCardInformationForSelectedWay(validCardInformation);
        CardData.checkIfPaymentSuccessful();
        val paymentId = SqlHelper.getPaymentId();
        val statusForPaymentByDebitCard = SqlHelper.getStatusForPaymentByDebitCard(paymentId);
        val paymentAmount = SqlHelper.getPaymentAmount(paymentId);
        assertEquals("APPROVED", statusForPaymentByDebitCard);
        assertEquals("4500000", paymentAmount);

    }

    @Test
    void shouldSuccessWithValidCreditCard() throws SQLException {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByCreditCard();
        val validCardInformation = DataHelper.getValidCardInformation();
        CardData.fillCardInformationForSelectedWay(validCardInformation);
        CardData.checkIfPaymentSuccessful();
        val paymentId = SqlHelper.getPaymentId();
        val statusForPaymentByCreditCard = SqlHelper.getStatusForPaymentByCreditCard(paymentId);
        assertEquals("APPROVED", statusForPaymentByCreditCard);
    }

    @Test
    void shouldNotSuccessWithInvalidDebitCard() throws SQLException {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByDebitCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        CardData.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData.checkIfPaymentNotSuccessful();
        val paymentId = SqlHelper.getPaymentId();
        val statusForPaymentByDebitCard = SqlHelper.getStatusForPaymentByDebitCard(paymentId);
        assertThat(statusForPaymentByDebitCard, equalTo("DECLINED"));
    }

    @Test
    void shouldNotSuccessWithInvalidCreditCard() throws SQLException {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByCreditCard();
        val validCardInformation = DataHelper.getInvalidCardInformation();
        CardData.fillCardInformationForSelectedWay(validCardInformation);
        CardData.checkIfPaymentNotSuccessful();
        val paymentId = SqlHelper.getPaymentId();
        val statusForPaymentByCreditCard = SqlHelper.getStatusForPaymentByCreditCard(paymentId);
        assertThat(statusForPaymentByCreditCard, equalTo("DECLINED"));
    }

    @Test
    void shouldNotSuccessWithWrongCardNumber() {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongLongCardNumber();
        CardData.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData.checkIfWrongFormatOfField();
        val CardData2 = mainPage.buyByDebitCard();
        CardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithShortestCardNumber() {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithShortestCardNumber();
        CardData.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData.checkIfWrongFormatOfField();
        val CardData2 = mainPage.buyByDebitCard();
        CardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithWrongMonth() {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongMonth();
        CardData.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData.checkIfWrongFormatOfField();
        val CardData2 = mainPage.buyByDebitCard();
        CardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithWrongYear() {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongYear();
        CardData.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData.checkIfWrongFormatOfField();
        val CardData2 = mainPage.buyByDebitCard();
        CardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithWrongYearFromOneNumber() {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongYearWithOneNumber();
        CardData.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData.checkIfWrongFormatOfField();
        val CardData2 = mainPage.buyByDebitCard();
        CardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithWrongCVC() {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongCvc();
        CardData.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData.checkIfWrongFormatOfField();
        val CardData2 = mainPage.buyByDebitCard();
        CardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithWrongName() {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongHolderName();
        CardData.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData.checkIfWrongFormatOfField();
        val CardData2 = mainPage.buyByDebitCard();
        CardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithoutName() {
        val mainPage = new MainPage();
        val CardData = mainPage.buyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithoutName();
        CardData.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData.checkIfWrongFormatOfField();
        val CardData2 = mainPage.buyByDebitCard();
        CardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData2.checkIfWrongFormatOfField();
    }
}
