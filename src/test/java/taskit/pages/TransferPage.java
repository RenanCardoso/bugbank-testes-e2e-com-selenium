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

public class TransferPage extends BasePage {

    @FindBy(css = "input[name='accountNumber']")
    private WebElement accountNumber;
    @FindBy(css = "input[name='digit']")
    private WebElement digit;
    @FindBy(css = "input[name='transferValue']")
    private WebElement transferValue;
    @FindBy(css = "input[name='description']")
    private WebElement description;
    @FindBy(css = "button[type='submit']")
    private WebElement submitTransfer;
    @FindBy(id = "modalText")
    private WebElement afterSubmitMessage;
    @FindBy(id = "btnCloseModal")
    private WebElement buttonCloseModal;

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    public TransferPage doTransfer(User user, String valueTransfer, String description){
        this.accountNumber.sendKeys(user.getNumberAccount());
        this.digit.sendKeys(user.getDigitAccount());
        this.transferValue.sendKeys(valueTransfer);
        this.description.sendKeys(description);
        this.submitTransfer.click();
        return new TransferPage(driver);
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
