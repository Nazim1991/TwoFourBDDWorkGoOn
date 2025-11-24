package ru.netology.web.test;

import
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static ru.netology.web.data.DataHelper.getAuthInfo;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCard() {
        var info = getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(info);
        Selenide.open("http://localhost:9999");

        $("[data-test-id='login'] input").setValue(info.getLogin());
        $("[data-test-id='password'] input").setValue(info.getPassword());
        $("[data-test-id='action-login'] input").click();

        $("[data-test-id='code'] input").setValue(info.getLogin());
        $("[data-test-id='login'] input").setValue(verificationCode.getCode());
        $("[data-test-id=actuon-veryfy]").click();

        $("[data-test-id=dashboard]").should(Condition.visible);
    }


}
