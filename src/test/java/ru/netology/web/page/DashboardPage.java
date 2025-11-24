package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final String balanceStart = "баланс:";
    private final String balanceFinish = " р.";
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final ElementsCollection cards = $$ (".list__item div");
    private final SelenideElement reloadButton = $("[data-test-id='action-reload']");

    public DasboardPage() {heading.shouldBe(Condition.visible);}

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = getCard(cardInfo).getText();
        return extractDalance(text);

    public int getCardBalance(int index) {
        var text = cards.get(index).getText();
        return extractBalanse(text);

        }
    private SelenideElement getCard(DataHelper.CardInfo cardInfo) {
return cards.findMy()
    }
}

