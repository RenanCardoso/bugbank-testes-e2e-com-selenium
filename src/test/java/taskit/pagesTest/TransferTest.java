package taskit.pagesTest;

import org.example.model.User;
import org.example.utils.GeradorDeDados;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import taskit.pages.HomePage;
import taskit.pages.LoginPage;

import java.time.Duration;

public class TransferTest {

    WebDriver driver;
    User user = new User().generateRandomUser();
    String localStorage = "";

    @Test
    @DisplayName("realizar transferência com sucesso")
    public void doTransferSuccesfully() {
        String responseMessage = "";
        User newUser = new User().generateRandomUser();

        responseMessage =
                new LoginPage(driver)
                        .enterLoginPage()
                        .enterRegisterPage()
                        .registerUser(newUser,true)
                        .getAfterSubmitMessage();
        Assertions.assertTrue(responseMessage.matches("A conta \\d{3}-\\d foi criada com sucesso"), responseMessage);

        responseMessage =
                new LoginPage(driver)
                .fillLogin(newUser)
                .enterTransferPage()
                .doTransfer(user, GeradorDeDados.gerarNumCentena(), GeradorDeDados.gerarTextoNota())
                .getAfterSubmitMessage();
        Assertions.assertEquals("Transferencia realizada com sucesso", responseMessage);
    }
    @Test
    @DisplayName("saldo insuficiente para realizar transferência")
    public void doTransferInsufficientBalance() {
        String responseMessage = "";
        User newUser = new User().generateRandomUser();

        responseMessage =
                new LoginPage(driver)
                        .enterLoginPage()
                        .enterRegisterPage()
                        .registerUser(newUser,false)
                        .getAfterSubmitMessage();
        Assertions.assertTrue(responseMessage.matches("A conta \\d{3}-\\d foi criada com sucesso"), responseMessage);

        responseMessage =
                new LoginPage(driver)
                        .fillLogin(newUser)
                        .enterTransferPage()
                        .doTransfer(user, GeradorDeDados.gerarNumCentena(), GeradorDeDados.gerarTextoNota())
                        .getAfterSubmitMessage();
        Assertions.assertEquals("Você não tem saldo suficiente para essa transação", responseMessage);
    }
    @Test
    @DisplayName("realizar transferência para si mesmo")
    public void doTransferDoYourSelf() {
        String responseMessage = "";

        responseMessage =
                new HomePage(driver)
                .enterTransferPage()
                .doTransfer(user, GeradorDeDados.gerarNumCentena(), GeradorDeDados.gerarTextoNota())
                .getAfterSubmitMessage();
        Assertions.assertTrue(responseMessage.matches("Nao pode transferir pra mesmo conta"), responseMessage);
    }
    @Test
    @DisplayName("realizar transferência para conta que não existe")
    public void doTransferAccountNotExists() {
        String responseMessage = "";
        user.setNumberAccount("99999");
        user.setDigitAccount("99");

        responseMessage =
                new HomePage(driver)
                        .enterTransferPage()
                        .doTransfer(user, GeradorDeDados.gerarNumCentena(), GeradorDeDados.gerarTextoNota())
                        .getAfterSubmitMessage();
        Assertions.assertTrue(responseMessage.matches("Conta inválida ou inexistente"), responseMessage);
    }

    @BeforeEach
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        new LoginPage(driver)
                .enterLoginPage()
                .enterRegisterPage()
                .registerUser(user,true)
                .getAfterSubmitMessage();

        String[] arrTextAccountNumberAndDigit =
                new LoginPage(driver)
                        .fillLogin(user)
                        .getAccountNumberAndDigit();

        user.setNumberAccount(arrTextAccountNumberAndDigit[0]);
        user.setDigitAccount(arrTextAccountNumberAndDigit[1]);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
