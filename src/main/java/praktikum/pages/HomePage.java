package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final String urlOfPage = "https://qa-scooter.praktikum-services.ru/";
    private final By cookieButton = By.id("rcc-confirm-button");

    // открываем страницу и сразу нажимаем на кнопку куки
    public void startPage() {
        driver.get(urlOfPage);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(cookieButton)).click();
    }

    // нажимаем на вопрос
    public void clickQuestion(String question) {
        WebElement questionButton = driver.findElement(By.xpath(".//div[contains(text(), '" + question + "')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionButton);
        questionButton.click();
    }

    // забираем текст из ответов
    public String getAnswerTextById(String locatorAccordion) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorAccordion)));
        return answerElement.getText();
    }

    // цикл, в котором определяем, какую кнопку нажать
    public void clickOrderButton(String orderButton) {
        if (orderButton.equals("Верхняя кнопка")) {
            driver.findElement(By.xpath(".//button[@class='Button_Button__ra12g']")).click();
        } else {
            WebElement button = driver.findElement(By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
            button.click();
        }
    }
}