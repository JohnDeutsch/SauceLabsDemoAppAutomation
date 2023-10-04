package pomClasses;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class CheckoutPage {

	AppiumDriver driver;
	
	public CheckoutPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
