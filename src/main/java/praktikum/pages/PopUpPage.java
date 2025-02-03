package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopUpPage {
    private WebDriver driver;

    public PopUpPage(WebDriver driver) {
        this.driver = driver;
    }

    // Список локаторов
    private final By header = By.xpath(".//div[text()='Хотите оформить заказ?']");
    private final By yesButton = By.xpath(".//button[text()='Да']");
    private final By successfulOrder = By.xpath(".//div[text()='Заказ оформлен']");

    public void waitForLoadPopUpPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    public void waitForMessageSuccessfulOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(successfulOrder));
    }

}
