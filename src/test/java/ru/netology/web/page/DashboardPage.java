package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private final String balanceStart = "баланс: "; // 3 usages  
    private final String balanceFinish = " р."; // 2 usages  
    private final SelenideElement heading = $(byCssSelector("[data-test-id=dashboard]")); // 2 usages  
    private final ElementCollection cards = $$(byCssSelector("[data-test-id^='list-item'] div")); // 2 usages  
    private final SelenideElement reloadButton = $(byCssSelector("[data-test-id='action-reload']")); // 1 usage  

    public DashboardPage() {  
        heading.shouldBe(visible);  
    }  

    public int getCardBalance(DataHelper.CardInfo cardInfo) { // 2 usages  
        var text = getCard(cardInfo).getText();  
        return extractBalance(text);  
    }  

    public int getCardBalance(int index) { // no usages  
        var text = cards.get(index).getText();  
        return extractBalance(text);  
    }  

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) { // 2 usages  
        getCard(cardInfo).$(byCssSelector("button")).click();  
        return new TransferPage();  
    }  

    private SelenideElement getCard(DataHelper.CardInfo cardInfo) { // 3 usages  
        return cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId()));  
    }  

    public void reloadDashboardPage() { // 2 usages  
        reloadButton.click();  
        heading.shouldBe(visible);  
    }  

    private int extractBalance(String text) { // 2 usages  
        var start = text.indexOf(balanceStart);  
        var finish = text.indexOf(balanceFinish);  
        var value = text.substring(start + balanceStart.length(), finish);  
        return Integer.parseInt(value);  
    }  

    public void checkCardBalance(DataHelper.CardInfo cardInfo, int expectedBalance) { // 4 usages  
        getCard(cardInfo)  
            .shouldBe(visible)  
            .shouldHave(text(balanceStart + expectedBalance + balanceFinish));  
    }  
}