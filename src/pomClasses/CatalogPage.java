package pomClasses;


import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import test.Utility;

public class CatalogPage {

	AppiumDriver driver;
	
	final String itemXpath = "(//android.view.ViewGroup[@content-desc=\"store item\"])[1]/android.view.ViewGroup[1]/android.widget.ImageView";
	final String sortButtonXpath = "//android.view.ViewGroup[@content-desc=\"sort button\"]/android.widget.ImageView";
	final String itemTextListXpath = "//android.widget.TextView[@content-desc=\"store item text\"]";
	final String itemPriceListXpath = "(//android.widget.TextView[@content-desc=\"store item price\"])";
	final String nameAscSortButtonXpath = "//android.view.ViewGroup[@content-desc=\"nameAsc\"]/android.widget.TextView";
	final String nameDescSortButtonXpath = "//android.view.ViewGroup[@content-desc=\"nameDesc\"]/android.widget.TextView[2]";
	final String priceAscSortButtonXpath = "//android.view.ViewGroup[@content-desc=\"priceAsc\"]/android.widget.TextView[2]";
	final String priceDescSortButtonXpath = "//android.view.ViewGroup[@content-desc=\"priceDesc\"]/android.widget.TextView[2]";
	final String endOfPageElementXpath = "//android.view.ViewGroup[@content-desc=\"products screen\"]"
			+ "/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.TextView";
	final String reviewStarButtonXpath = "(//android.view.ViewGroup[@content-desc=\"review star 2\"])[1]/android.widget.TextView";
	final String closeModalButtonXpath = "//android.view.ViewGroup[@content-desc=\"Close Modal button\"]";
	final String reviewMessageElementXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
			+ ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup"
			+ "/android.view.ViewGroup/android.widget.TextView";
	
	@FindBy(xpath = itemXpath)
	private WebElement item;
	@FindBy(xpath = sortButtonXpath)
	private WebElement sortButton;
	@FindBy(xpath = nameAscSortButtonXpath)
	private WebElement nameAscSortButton;
	@FindBy(xpath = nameDescSortButtonXpath)
	private WebElement nameDescSortButton;
	@FindBy(xpath = priceAscSortButtonXpath)
	private WebElement priceAscSortButton;
	@FindBy(xpath = priceDescSortButtonXpath)
	private WebElement priceDescSortButton;
	@FindBy(xpath = reviewStarButtonXpath)
	private WebElement reviewStarButton;
	@FindBy(xpath = closeModalButtonXpath)
	private WebElement closeModalButton;
	@FindBy(xpath = reviewMessageElementXpath)
	private WebElement reviewMessageElement;	
	
	public CatalogPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ItemPage tapItem() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(itemXpath)));
		item.click();
		return new ItemPage(driver);
	}
	
	public CatalogPage tapSortButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sortButtonXpath)));
		sortButton.click();
		return new CatalogPage(driver);
	}
	
	public CatalogPage tapNameAscSortButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(nameAscSortButtonXpath)));
		nameAscSortButton.click();
		return new CatalogPage(driver);
	}
	
	public CatalogPage tapNameDescSortButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(nameDescSortButtonXpath)));
		nameDescSortButton.click();
		return new CatalogPage(driver);
	}
	
	public CatalogPage tapPriceAscSortButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(priceAscSortButtonXpath)));
		priceAscSortButton.click();
		return new CatalogPage(driver);
	}
	
	public CatalogPage tapPriceDescSortButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(priceDescSortButtonXpath)));
		priceDescSortButton.click();
		return new CatalogPage(driver);
	}
	
	public CatalogPage tapReviewStarButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(reviewStarButtonXpath)));
		reviewStarButton.click();
		return new CatalogPage(driver);
	}
	
	public CatalogPage tapCloseModalButton() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(closeModalButtonXpath)));
		closeModalButton.click();
		return new CatalogPage(driver);
	}
	
	public String getReviewMessage() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(reviewMessageElementXpath)));
		return reviewMessageElement.getText();
	}
	
	public Set<String> getItemTextSet() {		 
		Set<String> strSet = new LinkedHashSet<String>();		
		boolean endOfPage = false;
		// create a loop finding all itemText elements on screen and scrolling down until the endOfPage has been reached
		// findElements() only finds elements on screen
		while (!endOfPage) {
			// search for end of page element on screen
			List<WebElement> endOfPageElement = driver.findElements(By.xpath(endOfPageElementXpath));
			if (endOfPageElement.size() > 0)
				endOfPage = true;
			
			// grab all itemTexts on screen
			new WebDriverWait(driver, Duration.ofSeconds(15))
			.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemTextListXpath)));
			List<WebElement> itemTextListOnScreen = driver.findElements(By.xpath(itemTextListXpath));
			for (WebElement webElement : itemTextListOnScreen) {
				if (!strSet.contains(webElement.getText()))
					strSet.add(webElement.getText());
			}
			
			if (endOfPage)
				break;
			
			Utility.swipeUp(driver);
		}
		
		return strSet;
	}
	
	public List<Double> getItemPriceList() {
		Set<String> strSet = new LinkedHashSet<String>();	
		List<String> itemPriceStringList = new ArrayList<String>(); 
		boolean endOfPage = false;
		// create a loop finding all itemText elements on screen and scrolling down until the endOfPage has been reached
		while (!endOfPage) {
			List<WebElement> endOfPageElement = driver.findElements(By.xpath(endOfPageElementXpath));
			if (endOfPageElement.size() > 0)
				endOfPage = true;
			
			/* Grab the item texts as well as the item prices in order to associate them with each other. This is 
			 * necessary because since we're grabbing everything on screen in a loop, itemTextList.contains() is
			 * used in order to make sure there are no duplicates. In a real non-demo application, it would be
			 * expected to have duplicate prices in the product list which is why we don't use itemPriceList.contais()
			 */
			new WebDriverWait(driver, Duration.ofSeconds(15))
			.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemTextListXpath)));
			List<WebElement> itemTextListOnScreen = driver.findElements(By.xpath(itemTextListXpath));
			
			new WebDriverWait(driver, Duration.ofSeconds(15))
			.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemPriceListXpath)));
			List<WebElement> itemPriceListOnScreen = driver.findElements(By.xpath(itemPriceListXpath));
						
			for (int i = 0; i < itemTextListOnScreen.size(); i++) {
				String itemText = itemTextListOnScreen.get(i).getText();
				if (!strSet.contains(itemText)) {
					strSet.add(itemText);
					itemPriceStringList.add(itemPriceListOnScreen.get(i).getText());
				}
			}
			
			if (endOfPage)
				break;
			
			Utility.swipeUp(driver);
		}
		
		// parse all string values to be float 
		List<Double> itemPriceDoubleList = new ArrayList<Double>(); 
		NumberFormat nFormat = NumberFormat.getInstance();
		for (int i = 0; i < itemPriceStringList.size(); i++) 
			itemPriceDoubleList.add((Double)nFormat.parse(itemPriceStringList.get(i), new ParsePosition(1)));
		
		return itemPriceDoubleList;
	}
	
	public void scrollToTop() {
		boolean topOfPage = false;
		while (!topOfPage) {
			List<WebElement> topOfPageElement = driver.findElements(By.xpath("//android.view.ViewGroup[@content-desc=\"container header\"]"
					+ "/android.widget.TextView"));
			if (topOfPageElement.size() > 0) {
				topOfPage = true;
				break;
			}			
			Utility.swipeDown(driver);
		}
	}
}
