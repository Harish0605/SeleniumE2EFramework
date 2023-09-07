package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.PageClasses.CartPage;
import rahulshettyacademy.PageClasses.OrderPage;

public class AbstractComponents {
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		// Initialize the driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(@class,'btn btn-custom') and contains(text(),'Cart')]")
	private WebElement cartHeader;
	
	@FindBy(xpath = "//button[contains(@class,'btn-custom') and contains(text(),'ORDERS')]")
	private WebElement ordersHeader;

	public CartPage goToCart() throws InterruptedException {
		waitForElementToBeClickable(cartHeader);
		threadSleep(2000);
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() {
		ordersHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		waitForElementToBeClickable(ordersHeader);
		return orderPage;
	}

	public void waitForElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForElementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void threadSleep(long sec) throws InterruptedException {
		Thread.sleep(sec);
	}

	public void scrollToElement(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", ele);
	}

}
