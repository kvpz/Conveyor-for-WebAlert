package webalertui;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfigurationPage {
    private static final String listViewResId = "android:id/list";
    private static final String listItemsClassName = "android.widget.LinearLayout";
    private static final String toolbarResId = "me.webalert:id/main_toolbar";

    public static void clickBackButton(WebDriver driver) {
        MobileElement toolBar = driver.findElement(By.id(toolbarResId));
        MobileElement backButton = toolBar.findElementByClassName("android.widget.ImageButton");
        backButton.click();
    }

    public static void clickNameButton(WebDriver driver) {
        MobileElement menuItems = driver.findElement(By.id(listViewResId));
        MobileElement nameButton = menuItems.findElementsByClassName(listItemsClassName).get(0);
        nameButton.click();
    }


    public static class NameDialogBox {
        private static final String editBoxResId = "android:id/edit";

        public static void setEditBoxValue(WebDriver driver, String val) {
            MobileElement textBox = driver.findElement(By.id(editBoxResId));
            textBox.clear();
            textBox.setValue(val);
        }
    }
}
