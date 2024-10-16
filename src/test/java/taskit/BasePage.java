package taskit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;
    public static final String BASE_URL = "https://bugbank.netlify.app";

    public BasePage(WebDriver driver){
        PageFactory.initElements(driver, this); //Necessário para utilizar o page factory

        this.driver = driver;
    }
}
