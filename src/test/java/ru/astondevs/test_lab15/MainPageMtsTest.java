package ru.astondevs.test_lab15;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.astondevs.lab15.MainPageMts;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Tatiana Futarnaya
 */
class MainPageMtsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPageMts mainPageMts;

    @BeforeEach
    public void setUp() {
        // Настройка WebDriverManager для автоматической загрузки драйвера
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPageMts = PageFactory.initElements(driver, MainPageMts.class);
        driver.get("https://mts.by");

       try {
           WebElement acceptCookiesModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
           acceptCookiesModal.click();
       }catch (Exception ignored){
       }

    }

    @AfterEach
    public void tearDown() {
        driver.quit();  // Закрытие браузера
    }

    // 1. Проверка названия указанного блока
    @Test
    public void testBlockTitle() {
        assertTrue(mainPageMts.isBlockTitleDisplayed(), "Блок 'Онлайн пополнение без комиссии' не найден");
    }

    // 2. Проверка наличия логотипов платёжных систем
    @Test
    public void testPaymentLogos() {
        assertTrue(mainPageMts.arePaymentLogosDisplayed(), "Логотипы платёжных систем не найдены");
    }

    // 3. Проверка работы ссылки «Подробнее о сервисе»
    @Test
    public void testMoreInfoLink() {
        mainPageMts.clickMoreInfoLink();
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));  // Ожидание, пока URL не изменится
        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"),
                "Ссылка 'Подробнее о сервисе' не работает");
    }

    // 4. Заполнение полей и проверка работы кнопки «Продолжить»
    @Test
    public void testContinueButton() {
        mainPageMts.selectServiceType("Услуги связи");
        mainPageMts.enterPhoneNumber("297777777");
        mainPageMts.enterAmount("5");
        mainPageMts.clickContinueButton();
        WebElement element =wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'bepaid-app__container')]")));
        assertTrue(element.isDisplayed(),"Модальное окно не отображается");
    }

}
