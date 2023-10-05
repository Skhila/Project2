package POM.Steps;

import POM.Pages.Navigation_AdditionalPages.FacebookLoginPopup;
import POM.Pages.SushiPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class SushiPageSteps {
    SushiPage sushiPage = new SushiPage();
    FacebookLoginPopup facebookLoginPopup = new FacebookLoginPopup();

    @Step("Click On Share Button")
    public SushiPageSteps clickOnShare(){
        sushiPage.shareButton.click();
        return this;
    }

    @Step("Validate Login Window")
    public SushiPageSteps validateLoginWindow(){
        Selenide.switchTo().window(1);
        String expectedPopupText = "Log into your Facebook account to share.";
        facebookLoginPopup.popupLoginElement.should(Condition.appear);
        facebookLoginPopup.popupLoginElement.shouldHave(Condition.text(expectedPopupText));
        System.out.println("Facebook Login Popup Validated Successfully!");
        return this;
    }
}
