package POM.Steps;

import POM.Pages.Navigation_AdditionalPages.HeaderContainer;
import POM.Pages.SushiOffersPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.lang.model.util.Elements;
import java.util.Objects;

public class SushiOffersPageSteps {
    HeaderContainer headerContainer = new HeaderContainer();
    SushiOffersPage sushiOffersPage = new SushiOffersPage();

    @Step("Open Categories Menu")
    public SushiOffersPageSteps openCategories(){
        headerContainer.categoriesMenuButton.click();
        return this;
    }

    @Step("Choose Food Category")
    public SushiOffersPageSteps chooseFoodCategory(){
        headerContainer.sushiCategory.hover();
        return this;
    }

    @Step("Choose Sushi")
    public SushiOffersPageSteps chooseSushi(){
        headerContainer.sushiElement.click();
        headerContainer.sushiElement.shouldNotBe(Condition.appear);
        return this;
    }

    @Step("Validate Vouchers Count")
    public SushiOffersPageSteps validateVouchers(SoftAssert softAssert){
        int voucherPercentage = Integer.parseInt(Objects.requireNonNull(sushiOffersPage.voucherCount.getAttribute("data-width")));
        softAssert.assertTrue(voucherPercentage < 100, "No Vouchers Available!!");
        System.out.println("There Are Tickets Available!");
        return this;
    }

    @Step("Add The First Sushi Offer To Favorites")
    public SushiOffersPageSteps addSushiToFavorites(){
        sushiOffersPage.addToFavoritesButton.click();
        return this;
    }

    @Step("Validate That Login Window Has Appeared")
    public SushiOffersPageSteps validateLoginWindow(SoftAssert softAssert){
        sushiOffersPage.loginWindow.should(Condition.appear);
        boolean isLoginAppear = sushiOffersPage.loginWindow.isDisplayed();
        softAssert.assertTrue(isLoginAppear);
        if(isLoginAppear){
            System.out.println("Login Window Appeared!");
        }
        return this;
    }

    @Step("Sort Sushi Offers By Price")
    public SushiOffersPageSteps sortOffers(){
        sushiOffersPage.sortSelect.selectOptionContainingText("ფასით კლებადი");
        sushiOffersPage.loadingAnimation.shouldNotBe(Condition.appear);
        return this;
    }

    @Step("Validate That Offers Are Descendingly Ordered")
    public SushiOffersPageSteps validateSushiPrices(){
        ElementsCollection sushiOffers = sushiOffersPage.sushiOffers;

        for (int i = 0; i < sushiOffers.size()-1; i++){
            String currentSushiOffer = sushiOffers.get(i).$("div.discounted-prices p.deal-voucher-price").getText();
            String nextSushiOffer = sushiOffers.get(i+1).$("div.discounted-prices p.deal-voucher-price").getText();

            double currentSushiOfferPrice = Double.parseDouble(currentSushiOffer.substring(0, currentSushiOffer.length()-1));
            double nextSushiOfferPrice = Double.parseDouble(nextSushiOffer.substring(0, nextSushiOffer.length()-1));

            Assert.assertTrue(currentSushiOfferPrice >= nextSushiOfferPrice);
        }
        return this;
    }

    @Step("Navigate To The First Sushi Offer")
    public SushiOffersPageSteps goToTheFirstOffer(){
        sushiOffersPage.sushiOffers.get(0).click();
        return this;
    }
}
