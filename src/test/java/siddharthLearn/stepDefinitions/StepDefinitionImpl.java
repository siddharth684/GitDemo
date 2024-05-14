package siddharthLearn.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import siddharthLearn.TestComponents.BaseTest;
import siddharthLearn.pageobjects.CartPage;
import siddharthLearn.pageobjects.CheckoutPage;
import siddharthLearn.pageobjects.ConfirmationPage;
import siddharthLearn.pageobjects.LandingPage;
import siddharthLearn.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	public CheckoutPage checkOutPage;
	public CartPage cartPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_ecommerce_page() throws IOException {
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username,String password) {
		productCatalogue=landingPage.loginApplication(username,password);	
	}
	
	@When("^I add the product (.+) to cart$")
	public void I_add_product_to_cart(String Product) {
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(Product);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String Product) {
		cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.match(Product);
		Assert.assertTrue(match);
		checkOutPage=cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		confirmationPage=checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmationpage(String string) {
		String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
   
    	Assert.assertEquals(strArg1, landingPage.getErrorMessage());
    	driver.close();
    }

}
