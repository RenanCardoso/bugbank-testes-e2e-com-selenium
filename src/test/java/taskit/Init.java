package taskit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Init {


    @Test
    @DisplayName("Quando abrir o site principal, então devo ver o título O banco com bugs e falhas do seu jeito")
    public void initialization(){
        WebDriver driver = new ChromeDriver(); //Objeto para poder fazer ações no chrome
        driver.manage().window().maximize();
        String basePage = "https://bugbank.netlify.app";
        driver.get(basePage);

        WebElement title = driver.findElement(By.cssSelector("#__next > div > div.pages__TitleBackground-sc-1ee1f2s-1.dEBVGQ > h1"));
        Assertions.assertEquals("O banco com bugs e falhas do seu jeito", title.getText());

        driver.quit();
    }
}
