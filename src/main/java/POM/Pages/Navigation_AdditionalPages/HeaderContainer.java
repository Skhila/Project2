package POM.Pages.Navigation_AdditionalPages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HeaderContainer {
    private final SelenideElement header = $("header.NewHeader");

    public SelenideElement categoriesMenuButton = header.$("p.categoriesTitle");

    public SelenideElement sushiCategory = header.$("li.catId-3");

    private final SelenideElement openMenu = header.$("div.mobileSubCategories.openedMenu");

    public SelenideElement sushiElement = openMenu.$(By.xpath(".//a[contains(normalize-space(), 'სუში')]"));

    public SelenideElement swoopLoginBtn = header.$("div.swoop-login");

    public SelenideElement hotelsButton = header.$(By.xpath(".//li[contains(@class,'MoreCategories')]/a[contains(normalize-space(), 'დასვენება')]"));


}
