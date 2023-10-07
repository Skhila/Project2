package POM.Steps;

import POM.Data.Dao.UserDao;
import POM.Pages.Navigation_AdditionalPages.HeaderContainer;
import POM.Pages.RegistrationPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.sql.SQLOutput;

public class RegistrationPageSteps {
    RegistrationPage registrationPage = new RegistrationPage();

    HeaderContainer headerContainer = new HeaderContainer();

    @Step("Go To Swoop Login Window")
    public RegistrationPageSteps goToSwoopLogin(){
        headerContainer.swoopLoginBtn.scrollTo().click();
        registrationPage.swoopLoginContainer.should(Condition.appear);
        return this;
    }

    @Step("Go To Registration Form")
    public RegistrationPageSteps goToRegistration(){
        registrationPage.registerButton.click();
        registrationPage.registrationContainer.should(Condition.appear);
        return this;
    }

    @Step("Fill FirstName")
    public RegistrationPageSteps fillFirstName(String firstName){
        registrationPage.firstNameInput.sendKeys(firstName);
        return this;
    }

    @Step("Fill LastName")
    public RegistrationPageSteps fillLastName(String lastName){
        registrationPage.lastNameInput.sendKeys(lastName);
        return this;
    }

    @Step("Fill Phone")
    public RegistrationPageSteps fillPhone(String phone){
        registrationPage.phoneInput.sendKeys(phone);
        return this;
    }

    @Step("Fill Email")
    public RegistrationPageSteps fillEmail(String email){
        registrationPage.emailInput.sendKeys(email);
        return this;
    }

    @Step("Fill Date Of Birth")
    public RegistrationPageSteps fillDateOfBirth(String dateOfBirth){
        Selenide.executeJavaScript("arguments[0].valueAsDate= new Date(arguments[1]);", registrationPage.dateOfBirthInput, dateOfBirth);
        return this;
    }

    @Step("Fill Password")
    public RegistrationPageSteps fillPassword(String password){
        registrationPage.passwordInput.sendKeys(password);
        return this;
    }

    @Step("Fill Confirm Password")
    public RegistrationPageSteps fillConfirmPassword(String confirmPassword){
        registrationPage.confirmPasswordInput.sendKeys(confirmPassword);
        return this;
    }

    @Step("Submit Registration")
    public RegistrationPageSteps submitRegistration(int userId) throws SQLException {
        registrationPage.submitButton.scrollTo().click();
        UserDao.deleteUserById(userId);
        return this;
    }

    @Step("Validate Error Message")
    public RegistrationPageSteps validateErrorMessage(){
        String expectedErrorMessage = "გთხოვთ აირჩიოთ სქესი!";
        String actualErrorMessage = registrationPage.errorMessageElement.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Wrong Error Message!!!");
        System.out.println("Error Message Validated Successfully!!!");
        return this;
    }
}
