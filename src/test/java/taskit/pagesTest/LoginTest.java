package taskit.pagesTest;

import org.example.model.User;
import org.junit.jupiter.api.*;
import taskit.pages.HomePage;
import taskit.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static taskit.BasePage.BASE_URL;

public class LoginTest {

    WebDriver driver;
    User user = new User().generateRandomUser();

    @Test
    @DisplayName("realizar login com sucesso")
    public void signIn() {
        String responseMessage =
                new LoginPage(driver)
                        .enterLoginPage()
                        .enterRegisterPage()
                        .registerUser(user,true)
                        .getAfterSubmitMessage();
        Assertions.assertTrue(responseMessage.matches("A conta \\d{3}-\\d foi criada com sucesso"), responseMessage);

        String homeWelcomeText =
                new LoginPage(driver)
                        .fillLogin(user)
                        .getHomePageWelcomeText();
        Assertions.assertEquals("Olá " + user.getName() + ",", homeWelcomeText);
        String urlHomePage =
                new HomePage(driver)
                        .getUrlHomePage();
        Assertions.assertEquals(BASE_URL + "/home", urlHomePage);
    }

    @Test
    @DisplayName("realizar login com usuário que não existe")
    public void signInWithUserNotExists() {
        String validationMessage =
                new LoginPage(driver)
                        .enterLoginPage()
                        .fillLoginUserNotExists(user)
                        .getAfterSubmitMessage();
        Assertions.assertEquals("Usuário ou senha inválido.\n" +
                "Tente novamente ou verifique suas informações!", validationMessage);
        String urlLoginPage =
                new HomePage(driver)
                        .getUrlHomePage();
        Assertions.assertEquals(BASE_URL + "/", urlLoginPage);
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
