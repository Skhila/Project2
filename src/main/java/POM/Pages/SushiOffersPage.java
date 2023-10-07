package POM.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SushiOffersPage {
    public SelenideElement loadingAnimation = $("div.freeze");
    private final SelenideElement sushiOffer = $("div.special-offer");
    public SelenideElement addToFavoritesButton = sushiOffer.$("div.deal-box-wishlist a");
    public SelenideElement voucherCount = sushiOffer.$("div.voucher-diagram div");
    public SelenideElement loginWindow = $("section.login-register-outer div.login");
    public SelenideElement sortSelect = $("select#sort");
    public ElementsCollection sushiOffers = $$("div.special-offer");
}
