package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper.VerificationCode;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    // Добавьте элементы страницы верификации
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        // Проверяем, что мы на странице верификации
        codeField.shouldBe(Condition.visible);
        verifyButton.shouldBe(Condition.visible);
    }

    public DashboardPage verify(VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}