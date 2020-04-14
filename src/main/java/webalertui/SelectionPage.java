package webalertui;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectionPage {
    private static final String checkMarkBtnResId = "me.webalert:id/css_menu_done";

    public static void clickCheckMarkButton(WebDriver driver) {
        MobileElement checkMarkButton = driver.findElement(By.id(checkMarkBtnResId));
        checkMarkButton.click();
    }

    public static class Dialogbox extends HelpDialogBox { }
}
