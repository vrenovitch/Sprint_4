package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentDataPage {

    private WebDriver driver;

    public RentDataPage(WebDriver driver) {
        this.driver = driver;
    }

    // Список локаторов
    private final By header = By.xpath(".//div[text()='Про аренду']");
    private final By dateOfArrive = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriod = By.xpath(".//div[text()='* Срок аренды']");
    private final By blackColor = By.id("black");
    private final By greyColor = By.id("grey");
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By makeOrder = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[2]");

    public void waitForLoadRentDataPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    // Заполняем поле Когда привезти самокат и выбираем в календаре введенное значение
    public void inputDateOfArrive(String newDateOfArrive) {
        driver.findElement(dateOfArrive).sendKeys(newDateOfArrive);
        driver.findElement(By.xpath(".//div[@class='react-datepicker__week']/div[@tabindex='0']")).click();
    }

    // Выбираем значение для Срок аренды
    public void inputRentalPeriod(String newRentalPeriod) {
        driver.findElement(rentalPeriod).click();
        driver.findElement(By.xpath(".//div[text()='" + newRentalPeriod + "']")).click();
    }

    // Делаем выбор(ы) в чек-боксе Цвет самоката
    public void chooseColor(String newChooseColor) {
        if (newChooseColor.equals("чёрный жемчуг")) {
            driver.findElement(blackColor).click();
        } else if (newChooseColor.equals("серая безысходность")) {
            driver.findElement(greyColor).click();
        } else {
            driver.findElement(blackColor).click();
            driver.findElement(greyColor).click();
        }
    }

    // Заполняем поле Комментарий для курьера
    public void inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
    }

    // Нажимаем кнопку Заказать
    public void makeOrderButton() {
        driver.findElement(makeOrder).click();
    }
}

