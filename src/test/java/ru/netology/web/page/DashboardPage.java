package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final String balanceStart = "баланс:";
    private final String balanceFinish = " р.";
    
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final ElementsCollection cards = $$(".list__item div");
    private final SelenideElement reloadButton = $("[data-test-id='action-reload']");

    // Конструктор с проверкой загрузки страницы
    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    // Получить баланс карты по CardInfo
    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = getCard(cardInfo).getText();
        return extractBalance(text);
    }

    // Получить баланс карты по индексу (альтернативный способ)
    public int getCardBalance(int index) {
        var text = cards.get(index).getText();
        return extractBalance(text);
    }

    // Переход на страницу перевода для конкретной карты
    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        getCard(cardInfo).$("button").click();
        return new TransferPage();
    }

    // Вспомогательный метод для поиска карты по testId
    private SelenideElement getCard(DataHelper.CardInfo cardInfo) {
        return cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId()));
    }

    // Обновление страницы (например, после перевода)
    public void reloadDashboardPage() {
        reloadButton.click();
        heading.shouldBe(visible);
    }

    // Парсинг числа баланса из текста
    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish).trim();
        return Integer.parseInt(value);
    }

    // Метод для проверки баланса (может быть реализован позже)
    public void checkCardBalance(DataHelper.CardInfo cardInfo, int expectedBalance) {
        int actualBalance = getCardBalance(cardInfo);
        // Здесь будет assertion, например:
        // assertThat(actualBalance).isEqualTo(expectedBalance);
    }
}