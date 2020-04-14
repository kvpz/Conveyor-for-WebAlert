package webalertui;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class HomePage {
    private static final String newPageBtnResId = "me.webalert:id/jobs_newJobButton";
    private static final String pagesListViewResId = "me.webalert:id/jobs_jobsView";

    public static void clickAddPageButton(WebDriver driver) {
        MobileElement addpageBtn = driver.findElement(By.id(newPageBtnResId));
        addpageBtn.click();
    }

    public static MobileElement getLastElementInList(WebDriver driver) {
        MobileElement pagesList = driver.findElement(By.id(pagesListViewResId));
        List<MobileElement> pages = pagesList.findElementsByClassName("android.view.ViewGroup");

        return pages.get(pages.size() - 1);
    }

    public static class PageDialogBox {
        private static final String dialogBoxResId = "android:id/contentPanel";

        public static void openDialogBoxForPageItem(WebDriver driver, MobileElement pageItem) {
            Actions actions = new Actions(driver);
            actions.clickAndHold(pageItem);
            actions.perform();
        }

        public static void clickEditButton(WebDriver driver) {
            MobileElement dialogBox = driver.findElement(By.id(dialogBoxResId));
            MobileElement dialogBoxEditButton = dialogBox.findElementsByClassName("android.widget.TextView").get(5);
            dialogBoxEditButton.click();
        }
    }
}
