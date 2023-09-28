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
import pomClasses.CatalogPage;

public class DemoAppTest {

	AppiumDriver androidDriver;
	//AppiumDriver iphoneDriver;
	
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
	
	// End every test on the Catalog page (scrolled up)
	
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
		
		catalogPage.scrollToTop();
	}
	
	//public void verifyCatalogSortingIphone()
}
