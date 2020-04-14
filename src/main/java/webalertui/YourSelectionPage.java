package webalertui;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourSelectionPage {
    private static final String checkMarkBtnResId = "me.webalert:id/selection_menu_accept";

    public static void clickCheckMarkButton(WebDriver driver) {
        MobileElement checkMarkButton = driver.findElement(By.id(checkMarkBtnResId));
        checkMarkButton.click();
    }
}
