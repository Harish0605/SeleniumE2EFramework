package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("harishbanavatu@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Harish@0605");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

		String item = "ADIDAS ORIGINAL";
		List<WebElement> products = driver.findElements(By.xpath("//div/h5"));
		List<WebElement> addToCartBtn = driver.findElements(By.xpath("//button[contains(text(),'Add To Cart')]"));
		for (int i = 0; i < products.size(); i++) {
			WebElement product = products.get(i);
			if (product.getText().equalsIgnoreCase(item)) {
				wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn.get(i)));
				addToCartBtn.get(i).click();
				break;

			}
		}

		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class,'ng-animating')]"))));
		Thread.sleep(3000);
		WebElement cartBtn = driver
				.findElement(By.xpath("//button[contains(@class,'btn btn-custom') and contains(text(),'Cart')]"));
		wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
		cartBtn.click();

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		int attempts = 0;
		while (attempts < 3) {
			try {
				WebElement CheckOutbtn = driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"));
				jse.executeScript("arguments[0].scrollIntoView(true);", CheckOutbtn);
				wait.until(ExpectedConditions.elementToBeClickable(CheckOutbtn));
				CheckOutbtn.click();
				break;
			} catch (ElementClickInterceptedException e) {
				// Handle the exception if needed e.printStackTrace();
			}
			attempts++;
			// Thread.sleep(1000); // Wait for a second before retrying
		}

		String country = "India";
		WebElement selectCountry = driver.findElement(By.xpath("//input[contains(@placeholder,'Select Country')]"));
		wait.until(ExpectedConditions.visibilityOf(selectCountry));
		selectCountry.sendKeys(country);
		List<WebElement> countries = driver.findElements(By.xpath("//section[contains(@class,'ta-results')]/button"));
		for (WebElement ele : countries) {
			if (ele.getText().equals(country)) {
				WebElement countryToBeSelected = driver.findElement(By.xpath("//span[text()=' India']"));
				wait.until(ExpectedConditions.visibilityOf(countryToBeSelected)).click();
				break;
			}
		}

		WebElement placeOrderbtn = driver.findElement(By.xpath("//a[contains(text(),'Place Order')]"));
		wait.until(ExpectedConditions.elementToBeClickable(placeOrderbtn));
		jse.executeScript("arguments[0].scrollIntoView(true);", placeOrderbtn);
		wait.until(ExpectedConditions.elementToBeClickable(placeOrderbtn));
		Thread.sleep(1000);
		placeOrderbtn.click();

		WebElement orderConfimed = driver.findElement(By.xpath("//h1"));
		wait.until(ExpectedConditions.visibilityOf(orderConfimed));
		System.out.println(orderConfimed.getText());
		String orderID = driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText().split(" ")[1];
		System.out.println(orderID);
		driver.close();

	}

}
