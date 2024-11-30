package ru.astondevs.lab15;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//span[contains(text(),'Услуги связи')]")
    private WebElement serviceTypeConnection;

    public boolean isBlockTitleDisplayed() {
        return blockTitle.isDisplayed();
    }

    public boolean arePaymentLogosDisplayed() {
        return !paymentLogos.isEmpty();
    }

    public void clickMoreInfoLink() {
        moreInfoLink.click();
    }

    public void selectServiceType(String serviceType) {
        if (serviceType.equals("Услуги связи")) {
            serviceTypeConnection.click();
        }
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

}
