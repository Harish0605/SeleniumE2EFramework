package rahulshettyacademy.PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		// send the driver object to Parent class
		super(driver);
		// Initialize the driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1")
	private WebElement orderConfimed;

	@FindBy(xpath = "//label[@class='ng-star-inserted']")
	private WebElement orderInfo;

	public void getOrderID() {
		waitForElementToAppear(orderConfimed);
		String orderID = orderInfo.getText().split(" ")[1];
		System.out.println(orderConfimed.getText()+" and your Order Id is : "+orderID);
	}

}
