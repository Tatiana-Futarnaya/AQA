package ru.astondevs.lab18;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Tatiana Futarnaya
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

        addEnvironmentInfo();
        addExecutorInfo();

        try {
            WebElement acceptCookiesModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            acceptCookiesModal.click();
        } catch (Exception ignored) {
        }
    }

    @AfterEach
    public void tearDown() {
        takeScreenshot("Тест завершен");
        driver.quit();  // Закрытие браузера
    }

    @Step("Добавление информации о среде в отчет")
    private void addEnvironmentInfo() {
        StringBuilder environmentInfo = new StringBuilder();
        environmentInfo.append("Операционная система: ").append(System.getProperty("os.name")).append("\n");
        environmentInfo.append("Версия ОС: ").append(System.getProperty("os.version")).append("\n");
        environmentInfo.append("Архитектура: ").append(System.getProperty("os.arch")).append("\n");
        environmentInfo.append("Java версия: ").append(System.getProperty("java.version")).append("\n");
        environmentInfo.append("Java vendor: ").append(System.getProperty("java.vendor")).append("\n");
        environmentInfo.append("Браузер: Google Chrome ").append(getChromeVersion()).append("\n");

        Allure.addAttachment("Environment", environmentInfo.toString());
    }

    @Step("Добавление информации о исполнителях в отчет")
    private void addExecutorInfo() {
        String executorInfo = "Локальный: ChromeDriver на локальной машине\n";
        Allure.addAttachment("Executors", executorInfo);
    }

    private String getChromeVersion() {
        // Получение версии Chrome через Capabilities
        if (driver instanceof ChromeDriver) {
            Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
            return capabilities.getBrowserVersion(); // Возвращает версию браузера
        }
        return "Неизвестная версия"; // Если драйвер не Chrome
    }


    @Step("Выбор услуги: {serviceType}")
    private void selectService(String serviceType) {
        mainPageMts.selectServiceType(serviceType, driver);
    }

    @Step("Сделать скриншот: {description}")
    private void takeScreenshot(String description) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(description, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @DisplayName("Проверка названия указанного блока")
    @Description("Тест проверяет, что блок 'Онлайн пополнение без комиссии' отображается на главной странице.")
    @Order(1)
    @Test
    void testBlockTitle() {
        assertTrue(mainPageMts.isBlockTitleDisplayed(), "Блок 'Онлайн пополнение без комиссии' не найден");
    }

    @DisplayName("Проверка наличия логотипов платёжных систем")
    @Description("Тест проверяет, что логотипы платёжных систем отображаются на странице.")
    @Order(2)
    @Test
    void testPaymentLogos() {
        assertTrue(mainPageMts.arePaymentLogosDisplayed(), "Логотипы платёжных систем не найдены");
    }

    @DisplayName("Проверка работы ссылки «Подробнее о сервисе»")
    @Description("Тест проверяет, что ссылка 'Подробнее о сервисе' работает корректно и ведет на нужную страницу.")
    @Order(3)
    @Test
    void testMoreInfoLink() {
        mainPageMts.clickMoreInfoLink();
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"),
                "Ссылка 'Подробнее о сервисе' не работает");
    }

    @DisplayName("Заполнение полей и проверка работы кнопки «Продолжить»")
    @Description("Тест проверяет, что после заполнения полей и нажатия кнопки 'Продолжить' отображается модальное окно.")
    @Order(4)
    @Test
    void testContinueButton() {
        selectService("Услуги связи");
        mainPageMts.enterPhoneNumber("297777777");
        mainPageMts.enterAmount("5");
        mainPageMts.clickContinueButton();
        WebElement element = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'bepaid-app__container')]")));
        assertTrue(element.isDisplayed(), "Модальное окно не отображается");
    }

    @DisplayName("Проверка надписей в незаполненных полях для услуг связи")
    @Description("Тест проверяет, что в поле номера телефона отображается правильная подсказка для услуги связи.")
    @ParameterizedTest
    @CsvSource({
            "Номер телефона"
    })
    @Order(5)
    void shouldDisplayCorrectPhonePlaceholderForServiceConnection(String expectedPhonePlaceholder) {
        selectService("Услуги связи"); // Выбор услуги

        String actualPhonePlaceholder = mainPageMts.getPhoneNumberPlaceholder();

        assertEquals(expectedPhonePlaceholder, actualPhonePlaceholder, "Неверная надпись в поле телефона");
    }

    @DisplayName("Проверка надписей в незаполненных полях для услуг связи")
    @Description("Тест проверяет, что в поле суммы отображается правильная подсказка для услуги связи.")
    @ParameterizedTest
    @CsvSource({
            "Сумма"
    })
    @Order(6)
    void shouldDisplayCorrectAmountPlaceholderForServiceConnection(String expectedAmountPlaceholder) {
        selectService("Услуги связи"); // Выбор услуги

        String actualAmountPlaceholder = mainPageMts.getAmountPlaceholder();

        assertEquals(expectedAmountPlaceholder, actualAmountPlaceholder, "Неверная надпись в поле суммы");
    }

    @DisplayName("Проверка надписей в незаполненных полях для услуг связи")
    @Description("Тест проверяет, что в поле email отображается правильная подсказка для услуги связи.")
    @ParameterizedTest
    @CsvSource({
            "E-mail для отправки чека"
    })
    @Order(7)
    void shouldDisplayCorrectEmailPlaceholderForServiceConnection(String expectedEmailPlaceholder) {
        selectService("Услуги связи"); // Выбор услуги

        String actualEmailPlaceholder = mainPageMts.getEmailPlaceholder();

        assertEquals(expectedEmailPlaceholder, actualEmailPlaceholder, "Неверная надпись в поле email");
    }

    @DisplayName("Проверка надписей в незаполненных полях для домашнего интернета")
    @Description("Тест проверяет, что в поле номера абонента отображается правильная подсказка для услуги домашнего интернета.")
    @ParameterizedTest
    @CsvSource({
            "Номер абонента"
    })
    @Order(8)
    void shouldDisplayCorrectSubscriberPlaceholderForHomeInternet(String expectedSubscriberPlaceholder) {
        selectService("Домашний интернет"); // Выбор услуги

        String actualSubscriberPlaceholder = mainPageMts.getSubscriberNumberPlaceholder();

        assertEquals(expectedSubscriberPlaceholder, actualSubscriberPlaceholder, "Неверная надпись в поле номера абонента");
    }

    @DisplayName("Проверка надписей в незаполненных полях для домашнего интернета")
    @Description("Тест проверяет, что в поле суммы отображается правильная подсказка для услуги домашнего интернета.")
    @ParameterizedTest
    @CsvSource({
            "Сумма"
    })
    @Order(9)
    void shouldDisplayCorrectAmountPlaceholderForHomeInternet(String expectedAmountPlaceholder) {
        selectService("Домашний интернет"); // Выбор услуги

        String actualAmountPlaceholder = mainPageMts.getSubscriberSumPlaceholder();

        assertEquals(expectedAmountPlaceholder, actualAmountPlaceholder, "Неверная надпись в поле суммы");
    }

    @DisplayName("Проверка надписей в незаполненных полях для домашнего интернета")
    @Description("Тест проверяет, что в поле email отображается правильная подсказка для услуги домашнего интернета.")
    @ParameterizedTest
    @CsvSource({
            "E-mail для отправки чека"
    })
    @Order(10)
    void shouldDisplayCorrectEmailPlaceholderForHomeInternet(String expectedEmailPlaceholder) {
        selectService("Домашний интернет"); // Выбор услуги

        String actualEmailPlaceholder = mainPageMts.getSubscriberEmailPlaceholder();

        assertEquals(expectedEmailPlaceholder, actualEmailPlaceholder, "Неверная надпись в поле email");
    }

    @DisplayName("Проверка надписей в незаполненных полях для рассрочки")
    @Description("Тест проверяет, что в поле номера счета отображается правильная подсказка для услуги рассрочки.")
    @ParameterizedTest
    @CsvSource({
            "Номер счета на 44"
    })
    @Order(11)
    void shouldDisplayCorrectAccountPlaceholderForInstallment(String expectedAccountPlaceholder) {
        selectService("Рассрочка"); // Выбор услуги

        String actualAccountPlaceholder = mainPageMts.getAccountNumberPlaceholder();

        assertEquals(expectedAccountPlaceholder, actualAccountPlaceholder,
                "Неверная надпись в поле номера счета на 44");
    }

    @DisplayName("Проверка надписей в незаполненных полях для рассрочки")
    @Description("Тест проверяет, что в поле суммы отображается правильная подсказка для услуги рассрочки.")
    @ParameterizedTest
    @CsvSource({
            "Сумма"
    })
    @Order(12)
    void shouldDisplayCorrectAmountPlaceholderForInstallment(String expectedAmountPlaceholder) {
        selectService("Рассрочка"); // Выбор услуги

        String actualAmountPlaceholder = mainPageMts.getAccountNumberSumPlaceholder();

        assertEquals(expectedAmountPlaceholder, actualAmountPlaceholder, "Неверная надпись в поле суммы");
    }

    @DisplayName("Проверка надписей в незаполненных полях для рассрочки")
    @Description("Тест проверяет, что в поле email отображается правильная подсказка для услуги рассрочки.")
    @ParameterizedTest
    @CsvSource({
            "E-mail для отправки чека"
    })
    @Order(13)
    void shouldDisplayCorrectEmailPlaceholderForInstallment(String expectedEmailPlaceholder) {
        selectService("Рассрочка"); // Выбор услуги

        String actualEmailPlaceholder = mainPageMts.getAccountNumberEmailPlaceholder();

        assertEquals(expectedEmailPlaceholder, actualEmailPlaceholder, "Неверная надпись в поле email");
    }

    @DisplayName("Проверка надписей в незаполненных полях для задолженности")
    @Description("Тест проверяет, что в поле номера счета отображается правильная подсказка для услуги задолженности.")
    @ParameterizedTest
    @CsvSource({
            "Номер счета на 2073"
    })
    @Order(14)
    void shouldDisplayCorrectAccountPlaceholderForDebtService(String expectedAccountPlaceholder) {
        selectService("Задолженность"); // Выбор услуги

        String actualAccountPlaceholder = mainPageMts.getAccountNumber2073Placeholder();

        assertEquals(expectedAccountPlaceholder, actualAccountPlaceholder,
                "Неверная надпись в поле номера счета на 2073");
    }

    @DisplayName("Проверка надписей в незаполненных полях для задолженности")
    @Description("Тест проверяет, что в поле суммы отображается правильная подсказка для услуги задолженности.")
    @ParameterizedTest
    @CsvSource({
            "Сумма"
    })
    @Order(15)
    void shouldDisplayCorrectAmountPlaceholderForDebtService(String expectedAmountPlaceholder) {
        selectService("Задолженность"); // Выбор услуги

        String actualAmountPlaceholder = mainPageMts.getAccountNumber2073SumPlaceholder();

        assertEquals(expectedAmountPlaceholder, actualAmountPlaceholder, "Неверная надпись в поле суммы");
    }

    @DisplayName("Проверка надписей в незаполненных полях для задолженности")
    @Description("Тест проверяет, что в поле email отображается правильная подсказка для услуги задолженности.")
    @ParameterizedTest
    @CsvSource({
            "E-mail для отправки чека"
    })
    @Order(16)
    void shouldDisplayCorrectEmailPlaceholderForDebtService(String expectedEmailPlaceholder) {
        selectService("Задолженность"); // Выбор услуги

        String actualEmailPlaceholder = mainPageMts.getAccountNumber2073EmailPlaceholder();

        assertEquals(expectedEmailPlaceholder, actualEmailPlaceholder, "Неверная надпись в поле email");
    }

    @Step("Заполнение полей платежа: номер телефона = {phoneNumber}, сумма = {amount}")
    private void fillPaymentFields(String phoneNumber, String amount) {
        mainPageMts.enterPhoneNumber(phoneNumber);
        mainPageMts.enterAmount(amount);
        mainPageMts.clickContinueButton();
    }

    @Step("Форматирование суммы: {amount}")
    private String formatAmount(String amount) {
        double amountDouble = Double.parseDouble(amount);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#.00", symbols);
        return decimalFormat.format(amountDouble);
    }

    @Step("Подготовка платежа: номер телефона = {phoneNumber}, сумма = {amount}")
    private void preparePayment(String phoneNumber, String amount) {
        selectService("Услуги связи");
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

    @DisplayName("Проверка корректности отображения номера телефона")
    @Description("Тест проверяет, что номер телефона отображается корректно после заполнения полей.")
    @ParameterizedTest
    @CsvSource({"297777777, 5"})
    @Order(17)
    void testDisplayedPhoneNumber(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount); // Вызов перед конкретным тестом

        String displayedPhoneNumber
                = wait
                .until(ExpectedConditions
                        .visibilityOfElementLocated
                                (By.xpath("//div[contains(@class, 'pay-description__text')]//span")))
                .getText();
        String actualPhoneNumber = displayedPhoneNumber.replaceAll("[^0-9]", "");

        assertEquals("375" + phoneNumber, actualPhoneNumber, "Номер телефона отображается неверно");
    }

    @DisplayName("Проверка корректности отображения суммы")
    @Description("Тест проверяет, что сумма отображается корректно после заполнения полей.")
    @ParameterizedTest
    @CsvSource({"297777777, 5"})
    @Order(18)
    void testDisplayedAmount(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount); // Вызов перед конкретным тестом

        String displayedAmount
                = wait
                .until(ExpectedConditions.
                        visibilityOfElementLocated
                                (By.xpath("//div[contains(@class, 'pay-description__cost')]//span")))
                .getText();
        String actualAmount = displayedAmount.replaceAll("[^0-9.]", "");

        assertEquals(formatAmount(amount), actualAmount, "Сумма отображается неверно");
    }

    @DisplayName("Проверка корректности отображения суммы на кнопке")
    @Description("Тест проверяет, что сумма на кнопке отображается корректно после заполнения полей.")
    @ParameterizedTest
    @CsvSource({"297777777, 5"})
    @Order(19)
    void testAmountDisplayedOnButton(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        WebElement amountButton =
                wait.until(ExpectedConditions.
                        visibilityOfElementLocated
                                (By.xpath("//button[contains(@class, 'colored disabled')]")));
        String buttonText = amountButton.getText().replaceAll("[^0-9.]", ""); // Удаляем все, кроме цифр и точки

        assertEquals(formatAmount(amount), buttonText, "Сумма на кнопке отображается неверно");
    }

    @DisplayName("Тест для проверки наличия иконок платежных систем")
    @Description("Тест проверяет, что на странице отображается достаточное количество иконок платежных систем.")
    @ParameterizedTest
    @CsvSource({"297777777, 5"})
    @Order(20)
    void testPaymentIconsCount(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        List<WebElement> paymentIcons =
                driver.findElements
                        (By.xpath("//div[contains(@class, 'icons-container ng-tns-c46-1')]//img"));
        assertTrue(paymentIcons.size() >= 5, "Количество иконок платежных систем меньше 5");
    }

    @DisplayName("Проверка надписей в незаполненных полях для ввода реквизитов карты")
    @Description("Тест проверяет, что в поле номера карты отображается правильная подсказка.")
    @ParameterizedTest
    @CsvSource({"297777777777, 5"})
    @Order(21)
    void shouldDisplayCorrectCardNumberLabel(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        assertEquals("Номер карты", mainPageMts.getCardNumberLabel(),
                "Неверная надпись в поле номера карты");
    }

    @DisplayName("Проверка надписей в незаполненных полях для ввода реквизитов карты")
    @Description("Тест проверяет, что в поле срока действия отображается правильная подсказка.")
    @ParameterizedTest
    @CsvSource({"297777777777, 5"})
    @Order(22)
    void shouldDisplayCorrectCardExpiryLabel(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        assertEquals("Срок действия", mainPageMts.getCardExpiryLabel(),
                "Неверная надпись в поле срока действия");
    }

    @DisplayName("Проверка надписей в незаполненных полях для ввода реквизитов карты")
    @Description("Тест проверяет, что в поле CVV отображается правильная подсказка.")
    @ParameterizedTest
    @CsvSource({"297777777777, 5"})
    @Order(23)
    void shouldDisplayCorrectCardCVVLabel(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        assertEquals("CVC", mainPageMts.getCardCVVLabel(), "Неверная надпись в поле CVV");
    }

    @DisplayName("Проверка надписей в незаполненных полях для ввода реквизитов карты")
    @Description("Тест проверяет, что в поле имени держателя карты отображается правильная подсказка.")
    @ParameterizedTest
    @CsvSource({"297777777777, 5"})
    @Order(24)
    void shouldDisplayCorrectCardHolderLabel(String phoneNumber, String amount) {
        preparePayment(phoneNumber, amount);

        assertEquals("Имя держателя (как на карте)", mainPageMts.getCardHolderLabel(),
                "Неверная надпись в поле имени держателя карты");
    }
}