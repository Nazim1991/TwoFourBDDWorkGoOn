package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$

import static org.junit.jupiter.api.Assertions.assertAll;

public class MoneyTransferTest {
    DashboardPage dashboardPage;  // 12 usages
    CardInfo firstCardInfo;       // 6 usages
    CardInfo secondCardInfo;      // 6 usages
    int firstCardBalance;         // 4 usages
    int secondCardBalance;        // 4 usages

    @BeforeEach
    void setup() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataHelper.getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);
        firstCardInfo = DataHelper.getFirstCardInfo();
        secondCardInfo = DataHelper.getSecondCardInfo();
        firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
    }

    @Test
    void shouldTransferFromFirstToSecond() {
        var amount = generatedIdAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expectedBalanceSecondCard = secondCardBalance + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
        dashboardPage.reloadDashboardPage();
        assertAll(
            () -> dashboardPage.checkCardBalance(firstCardInfo, expectedBalanceFirstCard),
            () -> dashboardPage.checkCardBalance(secondCardInfo, expectedBalanceSecondCard)
        );
    }

    @Test
    void shouldSetErrorMessageIfAmountMoreBalance() {
        var amount = generatedInvalidAmount(secondCardBalance);
        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        transferPage.makeTransfer(String.valueOf(amount), secondCardInfo);
        assertAll(
            () -> transferPage.findErrorMessage("Операция невозможна: недостаточно средств")
        );
    }
}