package taskit.pagesTest;

import org.example.model.User;
import org.junit.jupiter.api.*;
import taskit.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class RegisterTest {

    WebDriver driver;

    @Test
    @DisplayName("criar conta que possui saldo com sucesso")
    public void registerUserWithBalance() {
        String responseMessage =
                new LoginPage(driver)
                .enterLoginPage()
                .enterRegisterPage()
                .registerRandomUser(true)
                .getAfterSubmitMessage();
        Assertions.assertTrue(responseMessage.matches("A conta \\d{3}-\\d foi criada com sucesso"), responseMessage);
    }

    @Test
    @DisplayName("criar conta com sucesso e que não possui saldo")
    public void registerUserWithNoBalance() {
        String responseMessage =
                new LoginPage(driver)
                        .enterLoginPage()
                        .enterRegisterPage()
                        .registerRandomUser(false)
                        .getAfterSubmitMessage();
        Assertions.assertTrue(responseMessage.matches("A conta \\d{3}-\\d foi criada com sucesso"), responseMessage);
    }

    /* cenário abaixo está com erro no ambiente de produção, pois não está validando se o usuário já possui conta */
    @Test
    @DisplayName("criar conta com saldo e com dados de usuário já existente")
    public void registerUserWithBalanceWithExistingUser() {
        String responseMessage = "";
        User user = new User().generateRandomUser();
        System.out.println("Antes: " + user.getEmail());
        responseMessage =
                new LoginPage(driver)
                        .enterLoginPage()
                        .enterRegisterPage()
                        .registerUser(user, true)
                        .getAfterSubmitMessage();
        Assertions.assertTrue(responseMessage.matches("A conta \\d{3}-\\d foi criada com sucesso"), responseMessage);
        System.out.println("Depoius: " + user.getEmail());

        responseMessage =
                new LoginPage(driver)
                        .enterRegisterPage()
                        .registerUser(user, true)
                        .getAfterSubmitMessage();
        Assertions.assertEquals("E-mail informado já existe", responseMessage);
    }

    /* cenário abaixo está com erro no ambiente de produção, pois não está validando se o usuário já possui conta */
    @Test
    @DisplayName("criar conta sem saldo e com dados de usuário já existente")
    public void registerUserWithoutBalanceWithExistingUser() {
        String responseMessage = "";
        User user = new User().generateRandomUser();

        responseMessage =
                new LoginPage(driver)
                        .enterLoginPage()
                        .enterRegisterPage()
                        .registerUser(user, true)
                        .getAfterSubmitMessage();
        Assertions.assertTrue(responseMessage.matches("A conta \\d{3}-\\d foi criada com sucesso"), responseMessage);

        responseMessage =
                new LoginPage(driver)
                        .enterRegisterPage()
                        .registerUser(user, true)
                        .getAfterSubmitMessage();
        Assertions.assertEquals("E-mail informado já existe", responseMessage);
    }

    @BeforeEach
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
