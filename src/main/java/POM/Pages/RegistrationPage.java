package POM.Pages;

import POM.Pages.Navigation_AdditionalPages.HeaderContainer;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    public SelenideElement swoopLoginContainer = $("section.login-register-outer");

    public SelenideElement registerButton = swoopLoginContainer.$("p.register");

    public SelenideElement registrationContainer = $("div#register-content-1[aria-hidden=false]");

    public SelenideElement firstNameInput = registrationContainer.$("#pFirstName");
    public SelenideElement lastNameInput = registrationContainer.$("#pLastName");
    public SelenideElement emailInput = registrationContainer.$("#pEmail");
    public SelenideElement phoneInput = registrationContainer.$("#pPhone");
    public SelenideElement dateOfBirthInput = registrationContainer.$("#pDateBirth");
    public SelenideElement passwordInput = registrationContainer.$("#pPassword");
    public SelenideElement confirmPasswordInput = registrationContainer.$("#pConfirmPassword");
    public SelenideElement submitButton = registrationContainer.$("div.dashbord-registration input[value='რეგისტრაცია']");
    public SelenideElement errorMessageElement = registrationContainer.$("p#physicalInfoMassage");
}
