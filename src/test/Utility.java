package test;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumDriver;

public class Utility {

	public static void swipeUp(AppiumDriver driver) {
		Dimension dimension = driver.manage().window().getSize();
		int startY = (int) (dimension.height * 0.70);
		int endY = (int) (dimension.height * 0.30);
		int centerX = dimension.width / 2;
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), centerX, startY));
		swipe.addAction(finger.createPointerDown(0));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
				PointerInput.Origin.viewport(), centerX, endY));
		swipe.addAction(finger.createPointerUp(0));
		driver.perform(Arrays.asList(swipe));
	}
	
	public static void swipeDown(AppiumDriver driver) {
		Dimension dimension = driver.manage().window().getSize();
		int endY = (int) (dimension.height * 0.70);
		int startY = (int) (dimension.height * 0.30);
		int centerX = dimension.width / 2;
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), centerX, startY));
		swipe.addAction(finger.createPointerDown(0));
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
				PointerInput.Origin.viewport(), centerX, endY));
		swipe.addAction(finger.createPointerUp(0));
		driver.perform(Arrays.asList(swipe));
	}
}
