package test;

import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import pomClasses.CartPage;
import pomClasses.CatalogPage;
import pomClasses.ItemPage;

public class DemoAppTest {

	AppiumDriver androidDriver;
	//AppiumDriver iphoneDriver;
	
	final String reviewMessage = "Thank you for submitting your review!";
	final String itemLabel = "Sauce Labs Backpack";
	final int addToCartTestItemAmount = 1;
	final String itemColorContentDesc = "blue circle";
	final int itemAmountTestItemAmount1 = 2;
	final int itemAmountTestItemAmount2 = 2;
	final int itemAmountTestItemAmount3 = 1;
	
	@BeforeTest
	public void setup() {
		try {
			// Android capabilities
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");	
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.saucelabs.mydemoapp.rn");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.saucelabs.mydemoapp.rn.MainActivity");
			androidDriver = new AndroidDriver(URI.create("http://127.0.0.1:4723/").toURL(), capabilities);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// End every test on the Catalog page (scrolled up) with the state reset
	
	@Test
	public void verifyCatalogSortingAndroid() {
		CatalogPage catalogPage = new CatalogPage(androidDriver);
		
		// name descending sort check
		catalogPage = catalogPage.tapSortButton();
		catalogPage = catalogPage.tapNameDescSortButton();
		Set<String> itemTextSet = catalogPage.getItemTextSet();
		// reverse the order of items to make checking order simpler
		List<String> itemTextList = new ArrayList<String>(itemTextSet);
		Collections.reverse(itemTextList);
		
		boolean isSorted = true;
		String previousString = "";	// empty string is guaranteed to be less than or equal to any other
		for (final String current : itemTextList) {
		    if (current.compareTo(previousString) < 0) {
		        isSorted = false;
		        break;
		    }
		    previousString = current;
		}
		assertTrue(isSorted);
		
		// name ascending sort check
		catalogPage.scrollToTop();
		catalogPage = catalogPage.tapSortButton();
		catalogPage = catalogPage.tapNameAscSortButton();
		itemTextSet = catalogPage.getItemTextSet(); 
		
		isSorted = true;
		previousString = "";	
		for (final String current : itemTextSet) {
		    if (current.compareTo(previousString) < 0) {
		        isSorted = false;
		        break;
		    }
		    previousString = current;
		}
		assertTrue(isSorted);
		
		// price ascending sort check
		catalogPage.scrollToTop();
		catalogPage = catalogPage.tapSortButton();
		catalogPage = catalogPage.tapPriceAscSortButton();
		List<Double> itemPriceList = catalogPage.getItemPriceList();
		
		isSorted = true;
		Double previousDouble = 0.0;
		for (final Double current : itemPriceList) {
			if (current < previousDouble) {
				isSorted = false;
				break;
			}
			previousDouble = current;
		}
		assertTrue(isSorted);
		
		// price descending sort check
		catalogPage.scrollToTop();
		catalogPage = catalogPage.tapSortButton();
		catalogPage = catalogPage.tapPriceDescSortButton();
		itemPriceList = catalogPage.getItemPriceList();
		
		isSorted = true;
		previousDouble = Double.POSITIVE_INFINITY;
		for (final Double current : itemPriceList) {
			if (current > previousDouble) {
				isSorted = false;
				break;
			}
			previousDouble = current;
		}
		assertTrue(isSorted);
		
		// reset the app state
		catalogPage.scrollToTop();
		catalogPage = catalogPage.tapSortButton();
		catalogPage = catalogPage.tapNameAscSortButton();
	}
	
	//public void verifyCatalogSortingIphone()
	
	@Test
	public void verifyItemRatingAndroid() {
		CatalogPage catalogPage = new CatalogPage(androidDriver);
		
		// assert that message is displayed after tapping review star button on catalog page
		catalogPage = catalogPage.tapReviewStarButton();
		assertTrue(reviewMessage.compareTo(catalogPage.getReviewMessage()) == 0);
		
		catalogPage = catalogPage.tapCloseModalButton();
		
		// assert the same from the item page
		ItemPage itemPage = catalogPage.tapItem();
		itemPage = itemPage.tapReviewStarButton();
		assertTrue(reviewMessage.compareTo(itemPage.getReviewMessage()) == 0);
		
		itemPage = itemPage.tapCloseModalButton();
		
		// reset the app state
		itemPage = itemPage.tapOpenMenuButton();
		itemPage.tapCatalogMenuItem();
	}
	
	@Test
	public void verifyAddToCartAndroid() {
		CatalogPage catalogPage = new CatalogPage(androidDriver);
		ItemPage itemPage = catalogPage.tapItem();
		itemPage.tapAddToCartButton();
		CartPage cartPage = itemPage.tapCartButton();
		
		// assert that item label and amount are correct
		assertTrue(itemLabel.compareTo(cartPage.getItemLabel()) == 0);
		assertTrue(addToCartTestItemAmount == cartPage.getItemAmount());
		
		// reset the app state
		cartPage = cartPage.tapRemoveItemButton();
		cartPage = cartPage.tapOpenMenuButton();
		cartPage.tapCatalogMenuItem();
	}

	@Test
	public void verifyChangingColorAndroid() {
		// change color to blue in item page and assert that it is reflected on the cart page
		CatalogPage catalogPage = new CatalogPage(androidDriver);
		ItemPage itemPage = catalogPage.tapItem();
		itemPage.tapBlueColorButton();
		itemPage.tapAddToCartButton();
		CartPage cartPage = itemPage.tapCartButton();
		assertTrue(itemColorContentDesc.compareTo(cartPage.getBlueCircleContentDesc()) == 0);
		
		// reset the app state
		cartPage = cartPage.tapRemoveItemButton();
		cartPage = cartPage.tapOpenMenuButton();
		cartPage.tapCatalogMenuItem();
	}
	
	@Test
	public void verifyItemAmountAndroid() {
		// assert that the plus button updates the item amount on the item page
		CatalogPage catalogPage = new CatalogPage(androidDriver);
		ItemPage itemPage = catalogPage.tapItem();
		itemPage.tapPlusButton();
		assertTrue(itemAmountTestItemAmount1 == itemPage.getItemAmount());
		
		// assert that the cart page item amount is updated correctly
		itemPage.tapAddToCartButton();
		CartPage cartPage = itemPage.tapCartButton();
		assertTrue(itemAmountTestItemAmount2 == cartPage.getItemAmount());
		
		// assert that the minus button updates the item amount on the cart page
		cartPage.tapMinusButton();
		assertTrue(itemAmountTestItemAmount3 == cartPage.getItemAmount());
		
		// reset the app state
		cartPage = cartPage.tapRemoveItemButton();
		cartPage = cartPage.tapOpenMenuButton();
		cartPage.tapCatalogMenuItem();
	}
}
