package rahulshettyacademy.PageClasses;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		// send the driver object to Parent class
		super(driver);
		// Initialize the driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	private WebElement CheckOutBtn;

	@FindBy(xpath = "//div[contains(@class,'ng-animating')]")
	private WebElement spinner;

	public CheckOutPage checkOutOrder() {
		int attempts = 0;
		while (attempts < 3) {
			try {
				scrollToElement(CheckOutBtn);
				waitForElementToBeClickable(CheckOutBtn);
				CheckOutBtn.click();
				break;
			} catch (ElementClickInterceptedException e) {
				// Handle the exception if needed e.printStackTrace();
			}
			attempts++;
			// Thread.sleep(1000); // Wait for a second before retrying
		}
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}

}
