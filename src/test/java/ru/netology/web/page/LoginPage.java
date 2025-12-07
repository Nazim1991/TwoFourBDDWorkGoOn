package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    // Элементы страницы
    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passwordField = $("[data-test-id=password] input");
    private final SelenideElement loginButton = $("[data-test-id=action-login]");
    private final SelenideElement errorNotification = $("[data-test-id=error-notification]");

    // Конструктор с проверкой, что мы на странице логина
    public LoginPage() {
        loginField.shouldBe(Condition.visible);
        passwordField.shouldBe(Condition.visible);
        loginButton.shouldBe(Condition.visible);
    }

    // Основной метод для успешного входа
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    // Метод для неуспешного входа (негативный тест)
    public LoginPage invalidLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        // Ожидаем появление сообщения об ошибке
        errorNotification.shouldBe(Condition.visible);
        // Возвращаем эту же страницу, т.к. перехода не произошло
        return this;
    }

    // Метод для получения текста ошибки
    public String getErrorText() {
        return errorNotification.getText();
    }

    // Метод для ввода только логина (может пригодиться)
    public LoginPage enterLogin(String login) {
        loginField.setValue(login);
        return this;
    }

    // Метод для ввода только пароля
    public LoginPage enterPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    // Метод для нажатия кнопки без заполнения полей
    public LoginPage submit() {
        loginButton.click();
        return this;
    }
}