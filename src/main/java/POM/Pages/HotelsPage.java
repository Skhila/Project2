package POM.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HotelsPage{

    public ElementsCollection hotelSpecialOffers = $$("div.special-offer");
    public SelenideElement loadingAnimation = $("div.freeze");

}
