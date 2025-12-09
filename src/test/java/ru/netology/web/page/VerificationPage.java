package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper.VerificationCode;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public DashboardPage verify(VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}