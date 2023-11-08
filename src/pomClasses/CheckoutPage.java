package pomClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class CheckoutPage {

	AppiumDriver driver;
	
	final String nameInputFieldXpath = "//android.widget.EditText[@content-desc=\"Full Name* input field\"]";
	final String address1InputFieldXpath = "//android.widget.EditText[@content-desc=\"Address Line 1* input field\"]";
	final String address2InputFieldXpath = "//android.widget.EditText[@content-desc=\"Address Line 2 input field\"]";
	final String cityInputFieldXpath = "//android.widget.EditText[@content-desc=\"City* input field\"]";
	final String stateInputFieldXpath = "//android.widget.EditText[@content-desc=\"State/Region input field\"]";
	final String zipInputFieldXpath = "//android.widget.EditText[@content-desc=\"Zip Code* input field\"]";
	final String countryInputFieldXpath = "//android.widget.EditText[@content-desc=\"Country* input field\"]";
	final String paymentButtonXpath = "//android.view.ViewGroup[@content-desc=\"To Payment button\"]";
	
	@FindBy(xpath = nameInputFieldXpath)
	private WebElement nameInputField;
	@FindBy(xpath = address1InputFieldXpath)
	private WebElement address1InputField;
	@FindBy(xpath = address2InputFieldXpath)
	private WebElement address2InputField;
	@FindBy(xpath = cityInputFieldXpath)
	private WebElement cityInputField;
	@FindBy(xpath = stateInputFieldXpath)
	private WebElement stateInputField;
	@FindBy(xpath = zipInputFieldXpath)
	private WebElement zipInputField;
	@FindBy(xpath = countryInputFieldXpath)
	private WebElement countryInputField;
	@FindBy(xpath = paymentButtonXpath)
	private WebElement paymentButton;
	
	public CheckoutPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setNameField(String name) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(nameInputFieldXpath)));
		nameInputField.sendKeys(name);
	}
	
	public void setAddress1Field(String address) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(address1InputFieldXpath)));
		address1InputField.sendKeys(address);
	}
	
	public void setAddress2Field(String address) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(address2InputFieldXpath)));
		address2InputField.sendKeys(address);
	}
	
	public void setCityField(String city) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cityInputFieldXpath)));
		cityInputField.sendKeys(city);
	}
	
	public void setStateField(String state) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(stateInputFieldXpath)));
		stateInputField.sendKeys(state);
	}
	
	public void setZipField(String zip) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(zipInputFieldXpath)));
		zipInputField.sendKeys(zip);
	}
	
	public void setCountryField(String country) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(countryInputFieldXpath)));
		countryInputField.sendKeys(country);
	}
	
	public PaymentPage tapPaymentButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(paymentButtonXpath)));
		paymentButton.click();
		return new PaymentPage(driver);
	}
}
