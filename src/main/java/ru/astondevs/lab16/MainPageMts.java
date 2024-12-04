package ru.astondevs.lab16;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * @author Tatiana Futarnaya
 */
public class MainPageMts {
    @FindBy(xpath = "//div[@id='pay-section']//h2[contains(text(), 'Онлайн пополнение')]")
    private WebElement blockTitle;

    @FindBy(xpath = "//div[@class='pay__partners']//ul/li/img")
    private List<WebElement> paymentLogos;

    @FindBy(xpath = "//a[contains(text(), 'Подробнее о сервисе')]")
    private WebElement moreInfoLink;

    @FindBy(xpath = "//input[@class='phone' and @type='text']")
    private WebElement phoneNumberInput;

    @FindBy(xpath = "//input[@class='total_rub' and @type='text']")
    private WebElement amountInput;

    @FindBy(xpath = "//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton;

    // Кнопка для открытия выпадающего списка
    @FindBy(xpath = "//div[@class='select__wrapper']")
    private WebElement serviceTypeButton;

    // Локаторы для выбора типа услуги
    @FindBy(xpath = "//p[contains(text(),'Услуги связи')]")
    private WebElement serviceTypeConnection;

    @FindBy(xpath = "//p[contains(text(),'Домашний интернет')]") // Элемент для выбора домашнего интернета
    private WebElement serviceTypeInternet;

    @FindBy(xpath = "//p[contains(text(),'Рассрочка')]") // Элемент для выбора рассрочки
    private WebElement serviceTypeInstallment;

    @FindBy(xpath = "//p[contains(text(),'Рассрочка')]") // Элемент для выбора задолженности
    private WebElement serviceTypeDebt;

    // Поля для услуг связи
    @FindBy(xpath = "//input[@id='connection-email' and  @placeholder='E-mail для отправки чека']")// Плейсхолдер для e-mail услуг связи
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='connection-sum' and @placeholder='Сумма']")// Плейсхолдер для суммы услуг связи
    private WebElement sumInput;

    @FindBy(xpath = "//input[@id='connection-phone' and @placeholder='Номер телефона']") // Плейсхолдер для номера телефона
    private WebElement connectionPhoneInput;

    // Поля для домашнего интернета
    @FindBy(xpath = "//input[@id='internet-phone' and @placeholder='Номер абонента']") // Плейсхолдер для номера абонента
    private WebElement subscriberNumberInput;

    @FindBy(xpath = "//input[@id='internet-sum' and @placeholder='Сумма']") // Плейсхолдер для суммы абонента
    private WebElement subscriberSumInput;

    @FindBy(xpath = "//input[@id='internet-email' and @placeholder='E-mail для отправки чека']") // Плейсхолдер для e-mail абонента
    private WebElement subscriberEmailInput;

    // Поля для рассрочки
    @FindBy(xpath = "//input[@id='score-instalment' and @placeholder='Номер счета на 44']") // Плейсхолдер для номера счета на 44
    private WebElement accountNumberInput;

    @FindBy(xpath = "//input[@id='instalment-sum' and @placeholder='Сумма']") // Плейсхолдер суммы для рассрочки
    private WebElement accountSumNumberInput;

    @FindBy(xpath = "//input[@id='instalment-email' and @placeholder='E-mail для отправки чека']") // Плейсхолдер e-mail для рассрочки
    private WebElement accountEmailNumberInput;

    // Поля для задолженности
    @FindBy(xpath = "//input[@id='score-arrears' and @placeholder='Номер счета на 2073']") // Плейсхолдер для номера счета на 2073
    private WebElement accountNumber2073Input;

    @FindBy(xpath = "//input[@id='arrears-sum' and @placeholder='Сумма']") // Плейсхолдер суммы для номера счета на 2073
    private WebElement accountSumNumber2073Input;

    @FindBy(xpath = "//input[@id='arrears-email' and @placeholder='E-mail для отправки чека']") // Плейсхолдер e-mail для номера счета на 2073
    private WebElement accountEmailNumber2073Input;

    // Поля для реквизитов карты
    @FindBy(xpath = "//app-card-input//label[contains(text(), 'Номер карты')]") // Поле для номера карты
    private WebElement cardNumberInput;

    @FindBy(xpath = "//app-input//label[contains(text(), 'Срок действия')]") // Поле для срока действия карты
    private WebElement cardExpiryInput;

    @FindBy(xpath = "//app-input//label[contains(text(), 'CVC')]") // Поле для CVV
    private WebElement cardCVVInput;

    @FindBy(xpath = "//app-input//label[contains(text(), 'Имя держателя (как на карте)')]") // Поле для имени держателя карты
    private WebElement cardHolderInput;

    public boolean isBlockTitleDisplayed() {
        return blockTitle.isDisplayed();
    }

    public boolean arePaymentLogosDisplayed() {
        return !paymentLogos.isEmpty();
    }

    public void clickMoreInfoLink() {
        moreInfoLink.click();
    }

    public void selectServiceType(String serviceType, WebDriver driver) {
        serviceTypeButton.click(); // Нажимаем на кнопку, чтобы открыть выпадающий список
        WebElement elementToClick = null;
        switch (serviceType) {
            case "Услуги связи":
                elementToClick = serviceTypeConnection;
                break;
            case "Домашний интернет":
                elementToClick = serviceTypeInternet;
                break;
            case "Рассрочка":
                elementToClick = serviceTypeInstallment;
                break;
            case "Задолженность":
                elementToClick = serviceTypeDebt;
                break;
            default:
                throw new IllegalArgumentException("Неизвестный тип услуги: " + serviceType);
        }

        // Используем явное ожидание, чтобы дождаться, пока элемент станет кликабельным
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(elementToClick)); // Ожидание видимости элемента
        wait.until(ExpectedConditions.elementToBeClickable(elementToClick)); // Ожидание кликабельности элемента

        // Прокрутка элемента в видимую область перед кликом
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToClick);

        // Попробуем кликнуть на элемент с помощью JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void enterAmount(String amount) {
        amountInput.sendKeys(amount);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    //Для услуги связи
    public String getPhoneNumberPlaceholder() {
        return connectionPhoneInput.getAttribute("placeholder");
    }

    public String getAmountPlaceholder() {
        return sumInput.getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {
        return emailInput.getAttribute("placeholder");
    }

    //Для домашнего интернета
    public String getSubscriberNumberPlaceholder() {
        return subscriberNumberInput.getAttribute("placeholder");
    }

    public String getSubscriberSumPlaceholder() {
        return subscriberSumInput.getAttribute("placeholder");
    }

    public String getSubscriberEmailPlaceholder() {
        return subscriberEmailInput.getAttribute("placeholder");
    }

    //Для рассрочки
    public String getAccountNumberPlaceholder() {
        return accountNumberInput.getAttribute("placeholder");
    }

    public String getAccountNumberSumPlaceholder() {
        return accountSumNumberInput.getAttribute("placeholder");
    }

    public String getAccountNumberEmailPlaceholder() {
        return accountEmailNumberInput.getAttribute("placeholder");
    }

    //Для задолженности
    public String getAccountNumber2073Placeholder() {
        return accountNumber2073Input.getAttribute("placeholder");
    }

    public String getAccountNumber2073SumPlaceholder() {
        return accountSumNumber2073Input.getAttribute("placeholder");
    }

    public String getAccountNumber2073EmailPlaceholder() {
        return accountEmailNumber2073Input.getAttribute("placeholder");
    }

    // Методы для получения метки реквизитов карты
    public String getCardNumberLabel() {
        return cardNumberInput.getText();
    }

    public String getCardExpiryLabel() {
        return cardExpiryInput.getText();
    }

    public String getCardCVVLabel() {
        return cardCVVInput.getText();
    }

    public String getCardHolderLabel() {
        return cardHolderInput.getText();
    }
}

