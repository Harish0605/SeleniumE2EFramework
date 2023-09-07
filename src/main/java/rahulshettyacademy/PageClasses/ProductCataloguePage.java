package rahulshettyacademy.PageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCataloguePage extends AbstractComponents {

	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		// send the driver object to Parent class
		super(driver);
		// Initialize the driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div/h5")
	private List<WebElement> products;

	@FindBy(xpath = "//button[contains(text(),'Add To Cart')]")
	private List<WebElement> AddToCartBtns;

	@FindBy(xpath = "//div[contains(@class,'ng-animating')]")
	private WebElement spinner;

	public void addProduct(String item) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getText().equalsIgnoreCase(item)) {
				waitForElementToBeClickable(AddToCartBtns.get(i));
				AddToCartBtns.get(i).click();
				break;
			}
		}
		waitForElementToDisappear(spinner);
	}

}
