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

public class LoginPage {
	
	AppiumDriver driver;
	
	final String validAutofillXpath = "//android.view.ViewGroup[@content-desc=\"bob@example.com-autofill\"]/android.widget.TextView";
	final String invalidAutofillXpath = "//android.view.ViewGroup[@content-desc=\"alice@example.com (locked out)-autofill\"]/android.widget.TextView";
	final String loginButtonXpath = "//android.view.ViewGroup[@content-desc=\"Login button\"]";
	final String errorMessageXpath = "//android.view.ViewGroup[@content-desc=\"generic-error-message\"]/android.widget.TextView";
	final String logoutSuccessMessageXpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/"
			+ "android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView";
	final String logoutSuccessAcceptButtonXpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/"
			+ "android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button";
	final String openMenuButtonXpath = "//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView";
	final String catalogMenuItemXpath = "//android.view.ViewGroup[@content-desc=\"menu item catalog\"]";
	
	@FindBy(xpath = validAutofillXpath)
	private WebElement validAutofill;
	@FindBy(xpath = invalidAutofillXpath)
	private WebElement invalidAutofill;
	@FindBy(xpath = loginButtonXpath)
	private WebElement loginButton;
	@FindBy(xpath = errorMessageXpath)
	private List<WebElement> errorMessage;
	@FindBy(xpath = logoutSuccessMessageXpath)
	private List<WebElement> logoutSuccessMessage;
	@FindBy(xpath = logoutSuccessAcceptButtonXpath)
	private WebElement logoutSuccessAcceptButton;
	@FindBy(xpath = openMenuButtonXpath)
	private WebElement openMenuButton;
	@FindBy(xpath = catalogMenuItemXpath)
	private WebElement catalogMenuItem;
	
	public LoginPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void tapValidAutofill() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(validAutofillXpath)));
		validAutofill.click();
	}
	
	public LoginPage tapInvalidAutofill() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(invalidAutofillXpath)));
		invalidAutofill.click();
		return new LoginPage(driver);
	}
	
	public CatalogPage tapLoginButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loginButtonXpath)));
		loginButton.click();
		return new CatalogPage(driver);
	}
	
	public boolean errorMessageExists() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(errorMessageXpath)));
		return errorMessage.size() > 0;
	}
	
	public boolean logoutSuccessMessageExists() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(logoutSuccessMessageXpath)));
		return logoutSuccessMessage.size() > 0;
	}
	
	public LoginPage tapLogoutSuccessAcceptButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(logoutSuccessAcceptButtonXpath)));
		logoutSuccessAcceptButton.click();
		return new LoginPage(driver);
	}
	
	public LoginPage tapOpenMenuButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(openMenuButtonXpath)));
		openMenuButton.click();
		return new LoginPage(driver);
	}
	
	public CatalogPage tapCatalogMenuItem() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(catalogMenuItemXpath)));
		catalogMenuItem.click();
		return new CatalogPage(driver);
	}
}
