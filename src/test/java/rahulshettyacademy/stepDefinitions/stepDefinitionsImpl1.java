package rahulshettyacademy.stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.PageClasses.CartPage;
import rahulshettyacademy.PageClasses.CheckOutPage;
import rahulshettyacademy.PageClasses.ConfirmationPage;
import rahulshettyacademy.PageClasses.LandingPage;
import rahulshettyacademy.PageClasses.ProductCataloguePage;
import rahulshettyacademy.TestComponents.BaseTest;

public class stepDefinitionsImpl1 extends BaseTest {
	public WebDriver driver;

	public LandingPage landingPage;
	public ProductCataloguePage productCataloguePage;
	public CartPage cartPage;
	public CheckOutPage checkOutPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();

	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCataloguePage = landingPage.loginApplication(username, password);

	}

	@When("^I add the product (.+) to the Cart$")
	public void I_add_product_toCart(String productName) throws IOException {
		productCataloguePage.addProduct(productName);

	}

	@And("^Checkout (.+) and submit the order$")
	public void I_Checkout_product_and_submit_the_order(String productName) throws InterruptedException {
		cartPage = productCataloguePage.goToCart();
		String country = "India";
		checkOutPage = cartPage.checkOutOrder();
		confirmationPage = checkOutPage.selectCountryAndPlaceOrder(country);

	}

	@Then("I verify {string} message is displayed on the confirmation page")
	public void I_verify_the_message(String string) {
		confirmationPage.getOrderID();
		tearDown();
	}
	
	@Then("{string} message is displayed")
	public void Incorrect_message(String string) {
		Assert.assertEquals(string, landingPage.getErrorMsg());
		tearDown();
	}
	
	

}
