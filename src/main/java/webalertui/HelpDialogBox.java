package webalertui;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelpDialogBox {
    private static final String okBtnResId = "android:id/button1";

    public static void clickOkButton(WebDriver driver) {
        try {
            MobileElement dialogOkButtonElement = driver.findElement(By.id(okBtnResId));
            if(dialogOkButtonElement.isDisplayed())  // throws NoSuchElementException
                dialogOkButtonElement.click();
        }
        catch(org.openqa.selenium.NoSuchElementException e) {
            System.out.println("No dialog needs to be shunned.");
        }
    }
}
