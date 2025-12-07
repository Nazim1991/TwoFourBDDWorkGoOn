package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-test-id='error-notification'] .notification__content");

    // Конструктор с проверкой загрузки страницы
    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    // Метод для успешного перевода (возвращает DashboardPage)
    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();  // Исправлено: DashboardPage
    }

    // Базовый метод перевода (без проверки результата)
    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    // Метод для проверки сообщения об ошибке
    public void findErrorMessage(String expectedText) {
        errorMessage.shouldBe(Condition.visible
                .because("Сообщение об ошибке должно появиться"))
                .shouldHave(Condition.text(expectedText)
                .because("Текст ошибки должен быть: " + expectedText),
                Duration.ofSeconds(15));
    }
    
    // Альтернативная, более простая версия метода:
    public void findErrorMessageSimple(String expectedText) {
        errorMessage.shouldBe(Condition.visible, Duration.ofSeconds(15))
                   .shouldHave(Condition.text(expectedText));
    }
}