package Utlis;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.WebElement;

public class Utils {
    public static void performScrollAndClick(WebElement target){
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);" +
                "arguments[0].click()", target);
    }
}
