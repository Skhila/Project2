package Tests;

import POM.Data.Model.User;
import POM.Steps.*;
import TestsConfig.BaseConfigSelenide;
import TestsConfig.TestListeners.SelenideListener;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.SQLException;

@Listeners(SelenideListener.class)
@Epic("Swoop.ge Functionality Tests (Regression)")
public class SwoopTest extends BaseConfigSelenide {
    HomePageSteps homePageSteps;
    HotelsPageSteps hotelsPageSteps;
    SushiOffersPageSteps sushiOffersPageSteps;
    SushiPageSteps sushiPageSteps;
    DataBaseSteps dataBaseSteps;
    RegistrationPageSteps registrationPageSteps;
    SoftAssert softAssert;

    @BeforeClass(alwaysRun = true)
    public void initializePageSteps(){
        softAssert = new SoftAssert();

        hotelsPageSteps = new HotelsPageSteps();
        sushiOffersPageSteps = new SushiOffersPageSteps();
        sushiPageSteps = new SushiPageSteps();
        dataBaseSteps = new DataBaseSteps();
        registrationPageSteps = new RegistrationPageSteps();
    }

    @BeforeMethod(alwaysRun = true)
    public void loadHomePage(){
        homePageSteps = new HomePageSteps();
    }

    @Test(groups = "Regression1", description = "Hotels Test")
    @Feature("Price Range Functionality Tests")
    @Severity(SeverityLevel.NORMAL)
    @Story("Test Hotels In Given Price Range")
    @Description("This test opens the swoop.ge, then goes to 'დასვენება', then filters hotels from 200 to 230 Lari price range. " +
            "Finally, check if all returned elements ara from selected range.")
    public void hotelsTest(){
        homePageSteps.navigateToHotelsPage();
        hotelsPageSteps.fillMinPrice().fillMaxPrice().searchFilteredHotels().validateHotelsPrices();
    }

    @Test(groups = "Regression1", description = "Sushi Vouchers Test")
    @Feature("Voucher Count and Add To Favorites Button Functionality Tests")
    @Severity(SeverityLevel.NORMAL)
    @Story("Test Add To Favorites Button and Vouchers Count Validity")
    @Description("This test opens the swoop.ge, then chooses 'სუში' from 'კატეგორიები' dropdown, then validates that vouchers of the first" +
            "offer are not sold out. Finally, adds offer to the favorites and validates the Login Window appearance")
    public void sushiVouchersTest(){
        sushiOffersPageSteps.openCategories().chooseFoodCategory().chooseSushi().validateVouchers(softAssert).addSushiToFavorites().validateLoginWindow(softAssert);
        softAssert.assertAll();
    }

    @Test(groups = "Regression2", description = "Sushi Descending Prices Test")
    @Feature("Sorting Functionality Tests")
    @Severity(SeverityLevel.NORMAL)
    @Story("Test Descending Order On Sushi Offers")
    @Description("This test opens the swoop.ge, then chooses 'სუში' from 'კატეგორიები' dropdown, then sorts elements in descending order. " +
            "Finally, checks if the price of the items are in descending order")
    public void sushiPricesTest(){
        sushiOffersPageSteps.openCategories().chooseFoodCategory().chooseSushi().sortOffers().validateSushiPrices();
    }

    @Test(groups = "Regression2", description = "Share Function Test")
    @Feature("Special Offer's Share Functionality Tests")
    @Severity(SeverityLevel.NORMAL)
    @Story("Test Share Function In Sushi Offer")
    @Description("This test opens the swoop.ge, then chooses 'სუში' from 'კატეგორიები' dropdown, then opens the first sushi offer and " +
            "clicks on the 'გაზიარება' button. Finally, validates that window with Facebook login page has appeared")
    public void sushiShareTest(){
        sushiOffersPageSteps.openCategories().chooseFoodCategory().chooseSushi().goToTheFirstOffer();
        sushiPageSteps.clickOnShare().validateLoginWindow();
    }

    @Test(groups = "Regression2", description = "User Registration Using DataBase Test")
    @Feature("Registration Functionality Tests (Using Data Driven Testing Method)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Test User Registration With Database User")
    @Description("This test opens the swoop.ge, then clicks 'შესვლა' button, then chooses registration, fills all mandatory fields (except Gender)" +
            "with user information from database and submits the form. Finally, checks if the correct error message has appeared")
    public void registrationTest() throws SQLException{
        dataBaseSteps.insertNewUser().getUserFromDb();
        User currentUser = dataBaseSteps.getCurrentUser();
        registrationPageSteps.goToSwoopLogin().goToRegistration().fillFirstName(currentUser.getFirstName())
                .fillLastName(currentUser.getLastName()).fillEmail(currentUser.getEmail()).fillPhone(currentUser.getPhoneNumber())
                .fillDateOfBirth(currentUser.getDateOfBirth()).fillPassword(currentUser.getPassword()).fillConfirmPassword(currentUser.getPassword())
                .submitRegistration(currentUser.getId()).validateErrorMessage();
    }
}
