package taskit.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taskit.BasePage;

import java.time.Duration;

public class HomePage extends BasePage {
    @FindBy(id = "textName")
    private WebElement welcomeText;
    @FindBy(id = "btn-TRANSFERÊNCIA")
    private WebElement buttonTransferPage;
    @FindBy(css = "#textAccountNumber > span")
    private WebElement textAccountNumberAndDigit;
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public HomePage enterHomePage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(BASE_URL + "/home");
        return new HomePage(driver);
    }
    public String getHomePageWelcomeText() {
        return welcomeText.getText();
    }
    public String getUrlHomePage() {
        String currentUrl = driver.getCurrentUrl();;
        return currentUrl;
    }
    public String[] getAccountNumberAndDigit() {
        return textAccountNumberAndDigit.getText().split("-");
    }
    public TransferPage enterTransferPage(){
        this.buttonTransferPage.click();
        return new TransferPage(driver);
    }
}
