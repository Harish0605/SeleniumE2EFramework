package rahulshettyacademy.PageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		// send the driver object to Parent class
		super(driver);
		// Initialize the driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[contains(@placeholder,'Select Country')]")
	private WebElement selectCountry;

	@FindBy(xpath = "//section[contains(@class,'ta-results')]/button")
	private List<WebElement> countries;

	@FindBy(xpath = "//span[text()=' India']")
	private WebElement India;

	@FindBy(xpath = "//a[contains(text(),'Place Order')]")
	private WebElement placeOrderbtn;

	public ConfirmationPage selectCountryAndPlaceOrder(String country) throws InterruptedException {
		waitForElementToAppear(selectCountry);
		selectCountry.sendKeys(country);
		for (WebElement ele : countries) {
			if (ele.getText().equals(country)) {
				waitForElementToAppear(ele);
				ele.click();
				break;
			}
		}
		scrollToElement(placeOrderbtn);
		waitForElementToBeClickable(placeOrderbtn);
		threadSleep(2000);
		placeOrderbtn.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
