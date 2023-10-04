package pomClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class CartPage {

	AppiumDriver driver;
	
	final String itemLabelXpath = "//android.widget.TextView[@content-desc=\"product label\"]";
	final String itemAmountXpath = "//android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView";
	final String removeItemButtonXpath = "//android.view.ViewGroup[@content-desc=\"remove item\"]/android.widget.TextView";
	final String proceedButtonXpath = "//android.view.ViewGroup[@content-desc=\"Proceed To Checkout button\"]";
	final String openMenuButtonXpath = "//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView";
	final String catalogMenuItemXpath = "//android.view.ViewGroup[@content-desc=\"menu item catalog\"]";
	
	@FindBy(xpath = itemLabelXpath)
	private WebElement itemLabel;
	@FindBy(xpath = itemAmountXpath)
	private WebElement itemAmount;
	@FindBy(xpath = removeItemButtonXpath)
	private WebElement removeItemButton;
	@FindBy(xpath = proceedButtonXpath)
	private WebElement proceedButton;
	@FindBy(xpath = openMenuButtonXpath)
	private WebElement openMenuButton;
	@FindBy(xpath = catalogMenuItemXpath)
	private WebElement catalogMenuItem;
	
	public CartPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getItemLabel() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(itemLabelXpath)));
		return itemLabel.getText();
	}
	
	public int getItemAmount() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(itemAmountXpath)));
		return Integer.parseInt(itemAmount.getText());
	}
	
	public CartPage tapRemoveItemButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(removeItemButtonXpath)));
		removeItemButton.click();
		return new CartPage(driver);
	}
	
	public CheckoutPage tapProceedButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(proceedButtonXpath)));
		proceedButton.click();
		return new CheckoutPage(driver);
	}
	
	public CartPage tapOpenMenuButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(openMenuButtonXpath)));
		openMenuButton.click();
		return new CartPage(driver);
	}
	
	public CatalogPage tapCatalogMenuItem() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(catalogMenuItemXpath)));
		catalogMenuItem.click();
		return new CatalogPage(driver);
	}
}
