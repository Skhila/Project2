package POM.Steps;

import POM.Pages.Navigation_AdditionalPages.CategoriesSideMenu;
import POM.Pages.HotelsPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import static Utlis.Utils.performScrollAndClick;

public class HotelsPageSteps {
    HotelsPage hotelsPage = new HotelsPage();
    CategoriesSideMenu categoriesSideMenu = new CategoriesSideMenu();

    @Step("Fill MinPrice")
    public HotelsPageSteps fillMinPrice(){
        categoriesSideMenu.minPriceField.scrollTo().sendKeys("200");
        return this;
    }

    @Step("Fill Max Price")
    public HotelsPageSteps fillMaxPrice(){
        categoriesSideMenu.maxPriceField.scrollTo().sendKeys("230");
        return this;
    }

    @Step("Search Hotels With Filter")
    public HotelsPageSteps searchFilteredHotels(){
        performScrollAndClick(categoriesSideMenu.submitButton);
        return this;
    }

    @Step("Validate Hotels")
    public HotelsPageSteps validateHotelsPrices(){
        hotelsPage.loadingAnimation.shouldBe(Condition.not(Condition.appear));
        ElementsCollection allOffers = hotelsPage.hotelSpecialOffers;
        for (SelenideElement hotel: allOffers){
            String priceString = hotel.$("div.discounted-prices p.deal-voucher-price").getText();
            double price = Double.parseDouble(priceString.substring(0, priceString.length()-1));

            Assert.assertTrue(price >= 200 && price <= 230, "Invalid Price!!!");
        }
        return this;
    }
}
