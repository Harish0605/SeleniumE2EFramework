package rahulshettyacademy.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void incorrectLogin() throws IOException, InterruptedException {

		landingPage.loginApplication("harishbanavatu@gmail.com", "balabla");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
	}

}
