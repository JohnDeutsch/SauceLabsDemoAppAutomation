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

public class PaymentPage {

	AppiumDriver driver;
	
	final String topOfPageElementXpath = "//android.widget.ScrollView[@content-desc=\"checkout payment screen\"]"
			+ "/android.view.ViewGroup/android.widget.TextView[1]";
	final String nameInputFieldXpath = "//android.widget.EditText[@content-desc=\"Full Name* input field\"]";
	final String cardNumberInputFieldXpath = "//android.widget.EditText[@content-desc=\"Card Number* input field\"]";
	final String expDateInputFieldXpath = "//android.widget.EditText[@content-desc=\"Expiration Date* input field\"]";
	final String securityCodeInputFieldXpath = "//android.widget.EditText[@content-desc=\"Security Code* input field\"]";
	final String reviewOrderButtonXpath = "//android.view.ViewGroup[@content-desc=\"Review Order button\"]";
	
	@FindBy(xpath = topOfPageElementXpath)
	private List<WebElement> topOfPageElement;
	@FindBy(xpath = nameInputFieldXpath)
	private WebElement nameInputField;
	@FindBy(xpath = cardNumberInputFieldXpath)
	private WebElement cardNumberInputField;
	@FindBy(xpath = expDateInputFieldXpath)
	private WebElement expDateInputField;
	@FindBy(xpath = securityCodeInputFieldXpath)
	private WebElement securityCodeInputField;
	@FindBy(xpath = reviewOrderButtonXpath)
	private WebElement reviewOrderButton;
	
	public PaymentPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean topOfPageElementExists() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(topOfPageElementXpath)));
		return topOfPageElement.size() > 0;
	}
	
	public void setNameField(String name) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(nameInputFieldXpath)));
		nameInputField.sendKeys(name);
	}
	
	public void setCardNumberField(String cardNumber) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cardNumberInputFieldXpath)));
		cardNumberInputField.sendKeys(cardNumber);
	}
	
	public void setExpDateField(String expDate) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(expDateInputFieldXpath)));
		expDateInputField.sendKeys(expDate);
	}
	
	public void setSecurityCodeField(String securityCode) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(securityCodeInputFieldXpath)));
		securityCodeInputField.sendKeys(securityCode);
	}
	
	public OrderReviewPage tapReviewOrderButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(reviewOrderButtonXpath)));
		reviewOrderButton.click();
		return new OrderReviewPage(driver);
	}
}
