package taskit.pages;

import org.example.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import taskit.BasePage;
import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".card__login input[name='email']")
    private WebElement email;
    @FindBy(css = ".card__login input[name='password']")
    private WebElement password;
    @FindBy(css = ".login__buttons button[type='submit']")
    private WebElement buttonAccess;
    @FindBy(xpath = "//*[contains(text(), 'Registrar')]")
    private WebElement registerButton;
    @FindBy(id = "modalText")
    private WebElement afterSubmitMessage;
    @FindBy(id = "btnCloseModal")
    private WebElement buttonCloseModal;

    public LoginPage enterLoginPage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(BASE_URL + "/");
        return new LoginPage(driver);
    }

    public RegisterPage enterRegisterPage() {
        this.registerButton.click();
        return new RegisterPage(driver);
    }

    public HomePage fillLogin(User user) {
        this.email.sendKeys(user.getEmail());
        this.password.sendKeys(user.getPassword());
        this.buttonAccess.click();
        return new HomePage(driver);
    }
    public LoginPage fillLoginUserNotExists(User user) {
        this.email.sendKeys(user.getEmail());
        this.password.sendKeys(user.getPassword());
        this.buttonAccess.click();
        return new LoginPage(driver);
    }

    public String getAfterSubmitMessage() {
        // Espera explícita para garantir que o elemento esteja visível
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(afterSubmitMessage));
        String message = afterSubmitMessage.getText();
        buttonCloseModal.click();
        return message;
    }
}