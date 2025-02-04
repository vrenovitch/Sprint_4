package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.pages.HomePage;
import praktikum.pages.PersonalDataPage;
import praktikum.pages.PopUpPage;
import praktikum.pages.RentDataPage;


@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private HomePage homePage;
    private final String orderButton; // Выбор кнопки "Заказать"
    private final String name; // Имя арендатора
    private final String surname; // Фамилия арендатора
    private final String address; // Адрес арендатора
    private final String station; // Номер станции метро
    private final String telephoneNumber; // Телефон арендатора
    private final String date; // Дата аренды
    private final String period; // Длительность аренды
    private final String color; // Цвет самоката
    private final String comment; // Комментарий от пользователя

    public OrderTest(String orderButton, String name, String surname, String address, String station, String telephoneNumber,
                     String date, String period, String color, String comment) {
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getAnswerData() {
        return new Object[][]{
                {"Верхняя кнопка", "Михаил", "Зубенко", "ул. Косой переулок", "Лубянка", "88888888888", "01.02.2025", "пятеро суток", "чёрный жемчуг", "я хачу питсу"},
                {"Нижняя кнопка", "Валерий", "Жмышенко", "Самара", "Киевская", "88888888228", "02.02.2025", "сутки", "серая безысходность", "я снова хачу питсу"},
                {"Верхняя кнопка", "Глэк", "Абобус", "Махачкала", "Фили", "88881488228", "02.01.2025", "семеро суток", "чёрный жемчуг и серая безысходность", "эх, чичас бы питсы"}
        };
    }

    @Before
    public void openPage() {
        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        homePage = new HomePage(driver);
        // открываем страницу и сразу нажимаем на куки
        homePage.startPage();
    }

    @Test
    public void createOrder() {

        // нажимаем на кнопку Заказать
        homePage.clickOrderButton(orderButton);

        // создаем объект класса страницы с персональными данными
        PersonalDataPage personalDataPage = new PersonalDataPage(driver);
        // ждем загрузки страницы с персональными данными
        personalDataPage.waitForLoadPersonalDataPage();
        // заполняем поля
        personalDataPage.inputName(name);
        personalDataPage.inputSurname(surname);
        personalDataPage.inputAddress(address);
        personalDataPage.inputStation(station);
        personalDataPage.inputTelephoneNumber(telephoneNumber);
        // кликаем на кнопку Далее
        personalDataPage.clickNextButton();

        // создаем объект класса страницы с информацией об аренде
        RentDataPage rentDataPage = new RentDataPage(driver);
        // ждем загрузки страницы с информацией об аренде
        rentDataPage.waitForLoadRentDataPage();
        // заполняем поля
        rentDataPage.inputDateOfArrive(date);
        rentDataPage.inputRentalPeriod(period);
        rentDataPage.chooseColor(color);
        rentDataPage.inputComment(comment);
        // кликаем на кнопку Заказать
        rentDataPage.makeOrderButton();

        // создаем объект класса модальной страницы
        PopUpPage popUpPage = new PopUpPage(driver);
        // ждем загрузки страницы с информацией об аренде
        popUpPage.waitForLoadPopUpPage();
        // кликаем на кнопку Да
        popUpPage.clickYesButton();
        // проверяем, что появилось всплывающее окно с сообщением об успешном создании заказа
        popUpPage.waitForMessageSuccessfulOrder();
    }

    @After
    public void teardown() {
        // закрываем браузер
        driver.quit();
    }
}