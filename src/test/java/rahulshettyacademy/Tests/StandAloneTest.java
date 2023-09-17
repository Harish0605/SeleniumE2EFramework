package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

		String item = "IPHONE 13 PRO";
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.xpath(".//b")).getText().equals(item)).findFirst()
				.orElse(null);
		WebElement addToCartButton = prod.findElement(By.xpath(".//div/button[last()]"));
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
		addToCartButton.click();

		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class,'ng-animating')]"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@role='alert' and contains(text(),'Product Added To Cart')]")));

		WebElement cartBtn = driver
				.findElement(By.xpath("//button[contains(@class,'btn btn-custom') and contains(text(),'Cart')]"));
		wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
		cartBtn.click();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement CheckOutbtn = driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", CheckOutbtn);
		wait.until(ExpectedConditions.elementToBeClickable(CheckOutbtn));
		jse.executeScript("arguments[0].click();", CheckOutbtn);

		// Retry mechanism to run the same code which is giving
		// ElementClickInterceptedException
//		int attempts = 0;
//		while (attempts < 3) {
//			try {
//				WebElement CheckOutbtn = driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"));
//				jse.executeScript("arguments[0].scrollIntoView(true);", CheckOutbtn);
//				wait.until(ExpectedConditions.elementToBeClickable(CheckOutbtn));
//				CheckOutbtn.click();
//				break;
//			} catch (ElementClickInterceptedException e) {
//				// Handle the exception if needed e.printStackTrace();
//			}
//			attempts++;
//			// Thread.sleep(1000); // Wait for a second before retrying
//		}

		String country = "India";
		WebElement selectCountry = driver.findElement(By.xpath("//input[contains(@placeholder,'Select Country')]"));
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
		jse.executeScript("arguments[0].click();", placeOrderbtn);

		WebElement orderConfimed = driver.findElement(By.xpath("//h1"));
		wait.until(ExpectedConditions.visibilityOf(orderConfimed));
		System.out.println(orderConfimed.getText());
		String orderID = driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText().split(" ")[1];
		System.out.println(orderID);
		driver.close();

	}

}
