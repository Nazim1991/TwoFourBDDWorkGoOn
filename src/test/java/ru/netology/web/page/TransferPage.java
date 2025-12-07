package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

   public class TransferPage {
    private final SelenideElement transferBottom = $(byCssSelector("[data-test-id='action-transfer']"));
    private final SelenideElement amountInput = $(byCssSelector("[data-test-id='amount'] input"));
    private final SelenideElement fromInput = $(byCssSelector("[data-test-id='from'] input"));
    private final SelenideElement transferHead = $(ByText("Пополнение карты"));
    private final SelenideElement errorMessage = $(byCssSelector("[data-test-id='error-notification'] .notification__content"));

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferBottom.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text(expectedText));
    }
}