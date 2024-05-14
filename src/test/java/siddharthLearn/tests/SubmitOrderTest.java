package siddharthLearn.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import siddharthLearn.TestComponents.BaseTest;
import siddharthLearn.pageobjects.CartPage;
import siddharthLearn.pageobjects.CheckoutPage;
import siddharthLearn.pageobjects.ConfirmationPage;
import siddharthLearn.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub
		
//		String productName="ZARA COAT 3";
//		String email="siddharth684@gmail.com";
//		String country="India";
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));	

		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		
		
		Boolean match=cartPage.match(input.get("product"));
		Assert.assertTrue(match);
		
		CheckoutPage checkOutPage=cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
		
		String confirmMessage=confirmationPage.getConfirmationMessage();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String, String> map=new HashMap<String, String>();
//		map.put("email", "siddharth684@gmail.com");
//		map.put("password", "S!dpractice123");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map2=new HashMap<String, String>();
//		map2.put("email", "siddharth685@gmail.com");
//		map2.put("password", "S!drahul123");
//		map2.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//siddharthLearn//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	

}
