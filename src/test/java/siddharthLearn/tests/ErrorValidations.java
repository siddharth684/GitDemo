package siddharthLearn.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import siddharthLearn.TestComponents.BaseTest;


public class ErrorValidations extends BaseTest{
	@Test(retryAnalyzer = siddharthLearn.TestComponents.Retry.class)
	public void incorrectUser() {
		landingPage.loginApplication("ascsd@few.fwe", "S!dpractice123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
}
