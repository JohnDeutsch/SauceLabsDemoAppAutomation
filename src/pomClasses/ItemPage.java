package pomClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class ItemPage {

	AppiumDriver driver;
	
	final String reviewStarButtonXpath = "//android.view.ViewGroup[@content-desc=\"review star 5\"]/android.widget.TextView"; 
	final String closeModalButtonXpath = "//android.view.ViewGroup[@content-desc=\"Close Modal button\"]";
	final String reviewMessageElementXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
			+ ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
			+ "/android.view.ViewGroup/android.widget.TextView";
	final String catalogMenuItemXpath = "//android.view.ViewGroup[@content-desc=\"menu item catalog\"]";
	
	@FindBy(xpath = reviewStarButtonXpath)
	private WebElement reviewStarButton;
	@FindBy(xpath = closeModalButtonXpath)
	private WebElement closeModalButton;
	@FindBy(xpath = reviewMessageElementXpath)
	private WebElement reviewMessageElement;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"open menu\"]/android.widget.ImageView")
	private WebElement openMenuButton;
	@FindBy(xpath = catalogMenuItemXpath)
	private WebElement catalogMenuItem;
	
	public ItemPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ItemPage tapReviewStarButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(reviewStarButtonXpath)));
		reviewStarButton.click();
		return new ItemPage(driver);
	}
	
	public ItemPage tapCloseModalButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(closeModalButtonXpath)));
		closeModalButton.click();
		return new ItemPage(driver);
	}
	
	public String getReviewMessage() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(reviewMessageElementXpath)));
		return reviewMessageElement.getText();
	}
	
	public ItemPage tapOpenMenuButton() {
		openMenuButton.click();
		return new ItemPage(driver);
	}
	
	public CatalogPage tapCatalogMenuItem() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(catalogMenuItemXpath)));
		catalogMenuItem.click();
		return new CatalogPage(driver);
	}
}
