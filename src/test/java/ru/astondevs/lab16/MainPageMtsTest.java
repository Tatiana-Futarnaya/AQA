package ru.astondevs.lab16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Tatiana Futarnaya
 */
class MainPageMtsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPageMts mainPageMts;

    @BeforeEach
     void setUp() {
        // Настройка WebDriverManager для автоматической загрузки драйвера
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPageMts = PageFactory.initElements(driver, MainPageMts.class);
        driver.get("https://mts.by");

        try {
            WebElement acceptCookiesModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            acceptCookiesModal.click();
        } catch (Exception ignored) {
        }

    }

    @AfterEach
    public void tearDown() {
        driver.quit();  // Закрытие браузера
    }

    // 1. Проверка названия указанного блока
    @Test
     void testBlockTitle() {
        assertTrue(mainPageMts.isBlockTitleDisplayed(), "Блок 'Онлайн пополнение без комиссии' не найден");
    }

    // 2. Проверка наличия логотипов платёжных систем
    @Test
     void testPaymentLogos() {
        assertTrue(mainPageMts.arePaymentLogosDisplayed(), "Логотипы платёжных систем не найдены");
    }

    // 3. Проверка работы ссылки «Подробнее о сервисе»
    @Test
     void testMoreInfoLink() {
        mainPageMts.clickMoreInfoLink();
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));  // Ожидание, пока URL не изменится
        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"),
                "Ссылка 'Подробнее о сервисе' не работает");
    }

    // 4. Заполнение полей и проверка работы кнопки «Продолжить»
    @Test
     void testContinueButton() {
        mainPageMts.selectServiceType("Услуги связи", driver);
        mainPageMts.enterPhoneNumber("297777777");
        mainPageMts.enterAmount("5");
        mainPageMts.clickContinueButton();
        WebElement element = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'bepaid-app__container')]")));
        assertTrue(element.isDisplayed(), "Модальное окно не отображается");
    }

    // 5. Проверка надписей в незаполненных полях для услуг связи
    @ParameterizedTest
    @CsvSource({
            "Номер телефона"
    })
    void shouldDisplayCorrectPhonePlaceholderForServiceConnection(String expectedPhonePlaceholder) {
        mainPageMts.selectServiceType("Услуги связи", driver); // Выбор услуги

        String actualPhonePlaceholder = mainPageMts.getPhoneNumberPlaceholder();

        assertEquals(expectedPhonePlaceholder, actualPhonePlaceholder, "Неверная надпись в поле телефона");
    }

    @ParameterizedTest
    @CsvSource({
            "Сумма"
    })
    void shouldDisplayCorrectAmountPlaceholderForServiceConnection(String expectedAmountPlaceholder) {
        mainPageMts.selectServiceType("Услуги связи", driver); // Выбор услуги

        String actualAmountPlaceholder = mainPageMts.getAmountPlaceholder();

        assertEquals(expectedAmountPlaceholder, actualAmountPlaceholder, "Неверная надпись в поле суммы");
    }

    @ParameterizedTest
    @CsvSource({
            "E-mail для отправки чека"
    })
    void shouldDisplayCorrectEmailPlaceholderForServiceConnection(String expectedEmailPlaceholder) {
        mainPageMts.selectServiceType("Услуги связи", driver); // Выбор услуги

        String actualEmailPlaceholder = mainPageMts.getEmailPlaceholder();

        assertEquals(expectedEmailPlaceholder, actualEmailPlaceholder, "Неверная надпись в поле email");
    }

    // 6. Проверка надписей в незаполненных полях для домашнего интернета
    @ParameterizedTest
    @CsvSource({
            "Номер абонента"
    })
    void shouldDisplayCorrectSubscriberPlaceholderForHomeInternet(String expectedSubscriberPlaceholder) {
        mainPageMts.selectServiceType("Домашний интернет", driver); // Выбор услуги

        String actualSubscriberPlaceholder = mainPageMts.getSubscriberNumberPlaceholder();

        assertEquals(expectedSubscriberPlaceholder, actualSubscriberPlaceholder, "Неверная надпись в поле номера абонента");
    }

    @ParameterizedTest
    @CsvSource({
            "Сумма"
    })
    void shouldDisplayCorrectAmountPlaceholderForHomeInternet(String expectedAmountPlaceholder) {
        mainPageMts.selectServiceType("Домашний интернет", driver); // Выбор услуги

        String actualAmountPlaceholder = mainPageMts.getSubscriberSumPlaceholder();

        assertEquals(expectedAmountPlaceholder, actualAmountPlaceholder, "Неверная надпись в поле суммы");
    }

    @ParameterizedTest
    @CsvSource({
            "E-mail для отправки чека"
    })
    void shouldDisplayCorrectEmailPlaceholderForHomeInternet(String expectedEmailPlaceholder) {
        mainPageMts.selectServiceType("Домашний интернет", driver); // Выбор услуги

        String actualEmailPlaceholder = mainPageMts.getSubscriberEmailPlaceholder();

        assertEquals(expectedEmailPlaceholder, actualEmailPlaceholder, "Неверная надпись в поле email");
    }

    // 7. Проверка надписей в незаполненных полях для рассрочки
    @ParameterizedTest
    @CsvSource({
            "Номер счета на 44"
    })
    void shouldDisplayCorrectAccountPlaceholderForInstallment(String expectedAccountPlaceholder) {
        mainPageMts.selectServiceType("Рассрочка", driver); // Выбор услуги

        String actualAccountPlaceholder = mainPageMts.getAccountNumberPlaceholder();

        assertEquals(expectedAccountPlaceholder, actualAccountPlaceholder, "Неверная надпись в поле номера счета на 44");
    }

    @ParameterizedTest
    @CsvSource({
            "Сумма"
    })
    void shouldDisplayCorrectAmountPlaceholderForInstallment(String expectedAmountPlaceholder) {
        mainPageMts.selectServiceType("Рассрочка", driver); // Выбор услуги

        String actualAmountPlaceholder = mainPageMts.getAccountNumberSumPlaceholder();

        assertEquals(expectedAmountPlaceholder, actualAmountPlaceholder, "Неверная надпись в поле суммы");
    }

    @ParameterizedTest
    @CsvSource({
            "E-mail для отправки чека"
    })
    void shouldDisplayCorrectEmailPlaceholderForInstallment(String expectedEmailPlaceholder) {
        mainPageMts.selectServiceType("Рассрочка", driver); // Выбор услуги

        String actualEmailPlaceholder = mainPageMts.getAccountNumberEmailPlaceholder();

        assertEquals(expectedEmailPlaceholder, actualEmailPlaceholder, "Неверная надпись в поле email");
    }

    // 8. Проверка надписей в незаполненных полях для задолженности
    @ParameterizedTest
    @CsvSource({
            "Номер счета на 2073"
    })
    void shouldDisplayCorrectAccountPlaceholderForDebtService(String expectedAccountPlaceholder) {
        mainPageMts.selectServiceType("Задолженность", driver); // Выбор услуги

        String actualAccountPlaceholder = mainPageMts.getAccountNumber2073Placeholder();

        assertEquals(expectedAccountPlaceholder, actualAccountPlaceholder, "Неверная надпись в поле номера счета на 2073");
    }

    @ParameterizedTest
    @CsvSource({
            "Сумма"
    })
    void shouldDisplayCorrectAmountPlaceholderForDebtService(String expectedAmountPlaceholder) {
        mainPageMts.selectServiceType("Задолженность", driver); // Выбор услуги

        String actualAmountPlaceholder = mainPageMts.getAccountNumber2073SumPlaceholder();

        assertEquals(expectedAmountPlaceholder, actualAmountPlaceholder, "Неверная надпись в поле суммы");
    }

    @ParameterizedTest
    @CsvSource({
            "E-mail для отправки чека"
    })
    void shouldDisplayCorrectEmailPlaceholderForDebtService(String expectedEmailPlaceholder) {
        mainPageMts.selectServiceType("Задолженность", driver); // Выбор услуги

        String actualEmailPlaceholder = mainPageMts.getAccountNumber2073EmailPlaceholder();

        assertEquals(expectedEmailPlaceholder, actualEmailPlaceholder, "Неверная надпись в поле email");
    }

    private void fillPaymentFields(String phoneNumber, String amount) {
        mainPageMts.enterPhoneNumber(phoneNumber);
        mainPageMts.enterAmount(amount);
        mainPageMts.clickContinueButton();
    }

    private String formatAmount(String amount) {
        double amountDouble = Double.parseDouble(amount);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#.00", symbols);
        return decimalFormat.format(amountDouble);
    }

    private void preparePayment(String phoneNumber, String amount) {
        mainPageMts.selectServiceType("Услуги связи", driver);
        fillPaymentFields(phoneNumber, amount);

         wait.until(ExpectedConditions
                 .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'bepaid-app__container')]")));

        // Переключитесь на фрейм по имени или индексу
        driver.switchTo().frame(driver
                .findElement(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//app-card-input//label[text()='Номер карты']")));
    }

    // 9. Проверка корректности отображения номера телефона
    @ParameterizedTest
    @CsvSource({"297777777, 5"})
     void testDisplayedPhoneNumber(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount); // Вызов перед конкретным тестом

        String displayedPhoneNumber
                = wait
                .until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'pay-description__text')]//span")))
                .getText();
        String actualPhoneNumber = displayedPhoneNumber.replaceAll("[^0-9]", "");

        assertEquals("375" + phoneNumber, actualPhoneNumber, "Номер телефона отображается неверно");
    }

    // 10. Проверка корректности отображения суммы
    @ParameterizedTest
    @CsvSource({"297777777, 5"})
     void testDisplayedAmount(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount); // Вызов перед конкретным тестом

        String displayedAmount
                = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'pay-description__cost')]//span")))
                .getText();
        String actualAmount = displayedAmount.replaceAll("[^0-9.]", "");

        assertEquals(formatAmount(amount), actualAmount, "Сумма отображается неверно");
    }

    // 11. Проверка корректности отображения суммы на кнопке
    @ParameterizedTest
    @CsvSource({"297777777, 5"})
     void testAmountDisplayedOnButton(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        WebElement amountButton =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'colored disabled')]")));
        String buttonText = amountButton.getText().replaceAll("[^0-9.]", ""); // Удаляем все, кроме цифр и точки

        assertEquals(formatAmount(amount), buttonText, "Сумма на кнопке отображается неверно");
    }

    // 12. Тест для проверки наличия иконок платежных систем
    @ParameterizedTest
    @CsvSource({"297777777, 5"})
     void testPaymentIconsCount(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        List<WebElement> paymentIcons =
                driver.findElements(By.xpath("//div[contains(@class, 'icons-container ng-tns-c46-1')]//img"));
        assertTrue(paymentIcons.size() >= 5, "Количество иконок платежных систем меньше 5");
    }

    // 13. Проверка надписей в незаполненных полях для ввода реквизитов карты
    @ParameterizedTest
    @CsvSource({"297777777777, 5"})
    void shouldDisplayCorrectCardNumberLabel(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        assertEquals("Номер карты", mainPageMts.getCardNumberLabel(), "Неверная надпись в поле номера карты");
    }

    @ParameterizedTest
    @CsvSource({"297777777777, 5"})
    void shouldDisplayCorrectCardExpiryLabel(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        assertEquals("Срок действия", mainPageMts.getCardExpiryLabel(), "Неверная надпись в поле срока действия");
    }

    @ParameterizedTest
    @CsvSource({"297777777777, 5"})
    void shouldDisplayCorrectCardCVVLabel(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        assertEquals("CVC", mainPageMts.getCardCVVLabel(), "Неверная надпись в поле CVV");
    }

    @ParameterizedTest
    @CsvSource({"297777777777, 5"})
    void shouldDisplayCorrectCardHolderLabel(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        assertEquals("Имя держателя (как на карте)", mainPageMts.getCardHolderLabel(), "Неверная надпись в поле имени держателя карты");
    }
}