package POM.Pages.Navigation_AdditionalPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CategoriesSideMenu {

    private SelenideElement categoriesMenu = $("div.category-filter-desk");
    private SelenideElement pricesContainer = categoriesMenu.$(" div.price-inputs");
    public SelenideElement minPriceField = pricesContainer.$("#minprice");
    public SelenideElement maxPriceField = pricesContainer.$("#maxprice");
    public SelenideElement submitButton = categoriesMenu.$("div.submit-button");
}
