package POM.Steps;

import POM.Pages.HomePage;
import POM.Pages.Navigation_AdditionalPages.HeaderContainer;
import io.qameta.allure.Step;

public class HomePageSteps {
    HomePage homePage = new HomePage();
    HeaderContainer headerContainer = new HeaderContainer();

    @Step("Navigate To Hotels Page")
    public HomePageSteps navigateToHotelsPage(){
        headerContainer.hotelsButton.scrollTo().click();
        return this;
    }
}
