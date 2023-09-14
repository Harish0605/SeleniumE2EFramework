package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.PageClasses.CartPage;
import rahulshettyacademy.PageClasses.CheckOutPage;
import rahulshettyacademy.PageClasses.ConfirmationPage;
import rahulshettyacademy.PageClasses.OrderPage;
import rahulshettyacademy.PageClasses.ProductCataloguePage;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = ("getData"), groups = { "Purchase" }, retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> inputData) throws IOException, InterruptedException {

		ProductCataloguePage productCataloguePage = landingPage.loginApplication(inputData.get("username"),
				inputData.get("password"));
		productCataloguePage.addProduct(inputData.get("item"));
		CartPage cartPage = productCataloguePage.goToCart();
		String country = "India";
		CheckOutPage checkOutPage = cartPage.checkOutOrder();
		ConfirmationPage confirmationPage = checkOutPage.selectCountryAndPlaceOrder(country);
		confirmationPage.getOrderID();

	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = ("getData"))
	public void orderHistoryTest(String username, String password, String item) {

		ProductCataloguePage productCataloguePage = landingPage.loginApplication(username, password);
		OrderPage ordersPage = productCataloguePage.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(item));

	}

	// sending data from Json
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getDataFromJson(
				System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//TestData//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	// sending data as Strings
//	@DataProvider
//	public Object[][] getdata() {
//
//		return new Object[][] { { "harishbanavatu@gmail.com", "Harish@0605", "ADIDAS ORIGINAL" },
//				{ "anshika@gmail.com", "Iamking@000", "IPHONE 13 PRO" } };
//	}

// sending data as HashMap
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("username", "harishbanavatu@gmail.com");
//	map1.put("password", "Harish@0605");
//	map1.put("item", "ADIDAS ORIGINAL");
//
//	HashMap<String, String> map2 = new HashMap<String, String>();
//	map2.put("username", "anshika@gmail.com");
//	map2.put("password", "Iamking@000");
//	map2.put("item", "IPHONE 13 PRO");
}
