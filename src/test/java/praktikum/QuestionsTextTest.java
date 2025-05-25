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

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsTextTest {
    private WebDriver driver;
    private HomePage homePage;
    private final String question;
    private final String expectedAnswer;
    private final String locatorQuestion;

    public QuestionsTextTest(String question, String expectedAnswer, String locatorQuestion) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
        this.locatorQuestion = locatorQuestion;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getAnswerData() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "accordion__panel-0"},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "accordion__panel-1"},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "accordion__panel-2"},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "accordion__panel-3"},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "accordion__panel-4"},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "accordion__panel-5"},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "accordion__panel-6"},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "accordion__panel-7"}
        };
    }

    @Before
    public void openPage() {
        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        homePage = new HomePage(driver);
        // открываем страницу и сразу нажимаем на куки
        homePage.startPage();
    }

    @Test
    public void shouldBeEqual() {
        // скроллим и нажимаем на вопрос
        homePage.clickQuestion(question);
        // получаем текст ответа
        String actualAnswer = homePage.getAnswerTextById(locatorQuestion);
        // сравниваем и в случае несоответствия сообщаем об ошибке
        assertEquals("В пункте '" + question + "' ответ на вопрос не совпадает:", expectedAnswer, actualAnswer);
    }


    @After
    public void teardown() {
        // закрываем браузер
        driver.quit();
    }
}