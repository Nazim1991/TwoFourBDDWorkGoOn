package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCard() {
        var info = DataHelper.getAuthInfo(); // Исправлен вызов метода
        var verificationCode = DataHelper.getVerificationCode(info);
        Selenide.open("http://localhost:9999");

        $("[data-test-id='login'] input").setValue(info.getLogin());
        $("[data-test-id='password'] input").setValue(info.getPassword());
        $("[data-test-id='action-login']").click(); // Убрал .input, если это кнопка

        $("[data-test-id='code'] input").setValue(verificationCode.getCode()); // Исправлено: вводим код, а не логин
        // Строка с повторным вводом логина удалена!
        $("[data-test-id='action-verify']").click(); // Исправлена опечатка
        $("[data-test-id='dashboard']").should(Condition.visible); // Добавлены кавычки в селектор
    }
}