package webalertui;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NotificationConditionsPage {
    private static final String nextBtnResId = "me.webalert:id/trigger_nextButton";

    public static void clickNextButton(WebDriver driver) {
        MobileElement nextButton = driver.findElement(By.id(nextBtnResId));
        nextButton.click();
    }
}
