package POM.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomePage {
    public static final String URL = "https://www.swoop.ge/";
    public HomePage(){
        open(URL);
    }
}
