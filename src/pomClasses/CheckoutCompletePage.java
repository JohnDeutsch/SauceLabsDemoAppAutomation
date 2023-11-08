package pomClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class CheckoutCompletePage {

	AppiumDriver driver;
	
	final String topOfPageElementXpath = "//android.view.ViewGroup[@content-desc=\"checkout complete screen\"]"
			+ "/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[1]";
	final String continueShoppingButtonXpath = "//android.view.ViewGroup[@content-desc=\"Continue Shopping button\"]";
	
	@FindBy(xpath = topOfPageElementXpath)
	private List<WebElement> topOfPageElement;
	@FindBy(xpath = continueShoppingButtonXpath)
	private WebElement continueShoppingButton;
	
	public CheckoutCompletePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean topOfPageElementExists() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(topOfPageElementXpath)));
		return topOfPageElement.size() > 0;
	}
	
	public CatalogPage tapContinueShoppingButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(continueShoppingButtonXpath)));
		continueShoppingButton.click();
		return new CatalogPage(driver);
	}
}
