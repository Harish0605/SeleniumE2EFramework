package rahulshettyacademy.PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		//Initialize the driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	private WebElement userEmail;
	
	@FindBy(id="userPassword")
	private WebElement userPassword;
	
	@FindBy(id="login")
	private WebElement loginBtn;
	
	@FindBy(xpath="//div[@role='alert' and contains(text(),'Incorrect email or password.')]")
	private WebElement errorMsg;
	
	public ProductCataloguePage loginApplication(String username,String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		loginBtn.click();
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
		return productCataloguePage;
	}
	
	public String getErrorMsg() {
		waitForElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	
	

}
