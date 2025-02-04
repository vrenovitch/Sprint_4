package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalDataPage {

    private WebDriver driver;

    public PersonalDataPage(WebDriver driver) {
        this.driver = driver;
    }

    // Список локаторов
    private final By header = By.xpath(".//div[text()='Для кого самокат']");
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By station = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By telephoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // метод ожидания загрузки страницы
    public void waitForLoadPersonalDataPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    // Заполняем поле Имя
    public void inputName(String newName) {
        driver.findElement(name).sendKeys(newName);
    }

    // Заполняем поле Фамилия
    public void inputSurname(String newSurname) {
        driver.findElement(surname).sendKeys(newSurname);
    }

    // Заполняем поле Адрес
    public void inputAddress(String newAddress) {
        driver.findElement(address).sendKeys(newAddress);
    }

    // Заполняем поле Станция метро
    public void inputStation(String newStation) {
        driver.findElement(station).click();
        driver.findElement(By.xpath(".//div[text()='" + newStation + "']")).click();
    }

    // Заполняем поле Телефон
    public void inputTelephoneNumber(String newTelephoneNumber) {
        driver.findElement(telephoneNumber).sendKeys(newTelephoneNumber);
    }

    // Нажимаем кнопку Далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

}