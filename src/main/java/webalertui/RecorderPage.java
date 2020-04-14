package webalertui;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecorderPage {
    private static final String addrTextFieldResId = "me.webalert:id/navigate_addressTextField";
    private static final String submitButtonResId = "me.webalert:id/navigate_goButton";
    private static final String checkMarkBtnResId = "me.webalert:id/navigate_menu_chooseItem";

    public static void clickSubmitUrlButton(WebDriver driver) {
        MobileElement submitButton = driver.findElement(By.id(submitButtonResId));
        submitButton.click();
    }

    public static void clickCheckMarkButton(WebDriver driver) {
        MobileElement checkMarkButton = driver.findElement(By.id(checkMarkBtnResId));
        checkMarkButton.click();
    }

    public static void setTextFieldValue(WebDriver driver, String val) {
        MobileElement textField = driver.findElement(By.id(addrTextFieldResId));
        textField.setValue(val);
    }


    /* Sub classes */

    public static class DialogBox extends HelpDialogBox { }
}
