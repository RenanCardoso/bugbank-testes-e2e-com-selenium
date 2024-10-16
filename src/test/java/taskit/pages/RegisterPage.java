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


public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".card__register input[name='email']")
    private WebElement email;
    @FindBy(css = ".card__register input[name='name']")
    private WebElement name;
    @FindBy(css = ".card__register input[name='password']")
    private WebElement password;
    @FindBy(css = ".card__register input[name='passwordConfirmation']")
    private WebElement passwordConfirmation;
    @FindBy(id = "toggleAddBalance")
    private WebElement haveBalance;
    @FindBy(css = ".card__register button[type='submit']")
    private WebElement submitRegister;
    @FindBy(id = "modalText")
    private WebElement afterSubmitMessage;
    @FindBy(id = "btnCloseModal")
    private WebElement buttonCloseModal;

    public RegisterPage registerRandomUser(boolean hasBalance){
        User user = new User().generateRandomUser();
        this.email.sendKeys(user.getEmail());
        this.name.sendKeys(user.getName());
        this.password.sendKeys(user.getPassword());
        this.passwordConfirmation.sendKeys(user.getPassword());

        if (hasBalance) {
            this.haveBalance.click();
        }
        this.submitRegister.click();
        return new RegisterPage(driver);
    }

    public RegisterPage registerUser(User user, boolean hasBalance){
        this.email.clear();
        this.name.clear();
        this.password.clear();
        this.passwordConfirmation.clear();

        this.email.sendKeys(user.getEmail());
        this.name.sendKeys(user.getName());
        this.password.sendKeys(user.getPassword());
        this.passwordConfirmation.sendKeys(user.getPassword());

        if (hasBalance) {
            this.haveBalance.click();
        }
        this.submitRegister.click();
        return new RegisterPage(driver);
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
