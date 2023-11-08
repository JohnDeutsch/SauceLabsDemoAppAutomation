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

public class OrderReviewPage {

	AppiumDriver driver;
	
	final String topOfPageElementXpath = "//android.widget.ScrollView[@content-desc=\"checkout review order screen\"]"
			+ "/android.view.ViewGroup/android.widget.TextView";
	final String productLabelXpath = "//android.widget.TextView[@content-desc=\"product label\"]";
	final String deliveryNameXpath = "//android.view.ViewGroup[@content-desc=\"checkout delivery address\"]/android.widget.TextView[2]";
	final String deliveryAddressXpath = "//android.view.ViewGroup[@content-desc=\"checkout delivery address\"]/android.widget.TextView[3]";
	final String deliveryCityStateXpath = "//android.view.ViewGroup[@content-desc=\"checkout delivery address\"]/android.widget.TextView[4]";
	final String deliveryCountryZipXpath = "//android.view.ViewGroup[@content-desc=\"checkout delivery address\"]/android.widget.TextView[5]";
	final String paymentNameXpath = "//android.view.ViewGroup[@content-desc=\"checkout payment info\"]/android.widget.TextView[2]";
	final String paymentCardNumberXpath = "//android.view.ViewGroup[@content-desc=\"checkout payment info\"]/android.widget.TextView[3]";
	final String totalPriceXpath = "//android.widget.TextView[@content-desc=\"total price\"]";
	final String placeOrderButtonXpath = "//android.view.ViewGroup[@content-desc=\"Place Order button\"]";
	
	@FindBy(xpath = topOfPageElementXpath)
	private List<WebElement> topOfPageElement;
	@FindBy(xpath = productLabelXpath)
	private WebElement productLabel;
	@FindBy(xpath = deliveryNameXpath)
	private WebElement deliveryName;
	@FindBy(xpath = deliveryAddressXpath)
	private WebElement deliveryAddress;
	@FindBy(xpath = deliveryCityStateXpath)
	private WebElement deliveryCityState;
	@FindBy(xpath = deliveryCountryZipXpath)
	private WebElement deliveryCountryZip;
	@FindBy(xpath = paymentNameXpath)
	private WebElement paymentName;
	@FindBy(xpath = paymentCardNumberXpath)
	private WebElement paymentCardNumber;
	@FindBy(xpath = totalPriceXpath)
	private WebElement totalPrice;
	@FindBy(xpath = placeOrderButtonXpath)
	private WebElement placeOrderButton;
	
	public OrderReviewPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean topOfPageElementExists() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(topOfPageElementXpath)));
		return topOfPageElement.size() > 0;
	}
	
	public String getProductLabel() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(productLabelXpath)));
		return productLabel.getText();
	}
	
	public String getDeliveryName() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deliveryNameXpath)));
		return deliveryName.getText();
	}
	
	public String getDeliveryAddress() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deliveryAddressXpath)));
		return deliveryAddress.getText();
	}
	
	public String getDeliveryCityState() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deliveryCityStateXpath)));
		return deliveryCityState.getText();
	}
	
	public String getDeliveryCountryZip() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deliveryCountryZipXpath)));
		return deliveryCountryZip.getText();
	}
	
	public String getPaymentName() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(paymentNameXpath)));
		return paymentName.getText();
	}
	
	public String getPaymentCardNumber() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(paymentCardNumberXpath)));
		return paymentCardNumber.getText();
	}
	
	public String getTotalPrice() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(totalPriceXpath)));
		return totalPrice.getText();
	}
	
	public CheckoutCompletePage tapPlaceOrderButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(placeOrderButtonXpath)));
		placeOrderButton.click();
		return new CheckoutCompletePage(driver);
	}
}
