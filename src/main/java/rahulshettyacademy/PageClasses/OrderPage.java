package rahulshettyacademy.PageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		// send the driver object to Parent class
		super(driver);
		// Initialize the driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1")
	private WebElement yourOrdersHeader;

	@FindBy(xpath = "//table[contains(@class,'table')]/tbody/tr/td[2]")
	private List<WebElement> productNames;

	public boolean VerifyOrderDisplay(String Order) {
		boolean itemFound = false;
		waitForElementToAppear(yourOrdersHeader);
		for (WebElement e : productNames) {
			if (e.getText().equalsIgnoreCase(Order)) {
				itemFound = true;
			}
		}
		return itemFound;

	}

}