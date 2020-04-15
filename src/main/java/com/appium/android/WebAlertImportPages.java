package com.appium.android;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.Test;
import webalertmisc.PageConfiguration;
import webalertui.*;
import org.apache.commons.*;
import org.apache.commons.cli.*;

import static webalertmisc.Utility.getConfigFile;
import static webalertmisc.Utility.jsonFileToMap;

public class WebAlertImportPages {
    private static WebDriver driver;
    public static AppiumDriverLocalService appiumService;
    public static String appiumServiceUrl;
    public static String name;
    public static String address;
    public static String configFilePath;

    private static CommandLine cmdline;
    private static Options cmdOptions;

    public static void main(String[] args) {

        setupCLIOptions();

        parseCLIOptions(args);

        setupAppiumServer();

        // iterate through all page chron job configurations provided by json file
        File configFile = getConfigFile(configFilePath);
        Map<?,?> configFileMap = jsonFileToMap(configFile);
        ArrayList<LinkedTreeMap<?,?>> pageConfigs = (ArrayList<LinkedTreeMap<?,?>>)configFileMap.get("pages");
        for(LinkedTreeMap<?,?> pageConfigLTM : pageConfigs) {
            String pageConfigJson = new Gson().toJson(pageConfigLTM);
            PageConfiguration pageConfig = new Gson().fromJson(pageConfigJson, PageConfiguration.class);
            address = pageConfig.getAddress();
            name = pageConfig.getName();
            AddNewPage();
        }

        End();
    }

    public static void setupCLIOptions() {
        cmdOptions = new Options();

        Option allowDuplicatesOpt = new Option("allowDuplicates", false, "Allow duplicate page job configurations.");
        cmdOptions.addOption(allowDuplicatesOpt);

        Option configFileOpt = new Option("configFile", true, "Path to the config file");
        configFileOpt.setRequired(true);
        configFileOpt.setLongOpt("configFile");
        cmdOptions.addOption(configFileOpt);
    }

    public static void parseCLIOptions(String[] args) {
        try {
            cmdline = new DefaultParser().parse(cmdOptions, args);
        }
        catch (ParseException e) {
            System.out.println(e);
            System.exit(-1);
        }

        configFilePath = cmdline.getOptionValue("configFile");
    }

    public static void setupAppiumServer() {

        appiumService = AppiumDriverLocalService.buildDefaultService();
        appiumService.start();
        appiumServiceUrl = appiumService.getUrl().toString();
        System.out.println("Appium Service Address : - " + appiumServiceUrl);

        // set capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, "/home/kevin/Desktop/base.apk");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("appPackage", "me.webalert");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        capabilities.setCapability("skipServerInstallation", true);

        try {
            driver = new AndroidDriver<>(new URL(appiumServiceUrl), capabilities);
        }
        catch(MalformedURLException e) {
            System.out.println(e);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Add a new page.
     */
    public static void AddNewPage() {
        // Home page - Click the button with addition icon on the Home page
        HomePage.clickAddPageButton(driver);

        // Recorder page - Click the OK button in the Help dialog box (if present)
        By dialogOkButton = By.id("android:id/button1");
        RecorderPage.DialogBox.clickOkButton(driver);

        // Recorder page - type in a valid URL into the text search box
        RecorderPage.setTextFieldValue(driver, address);

        // Recorder page - Press button to submit request to URL
        RecorderPage.clickSubmitUrlButton(driver);

        // wait until the page has loaded (implicit action)
        // Recorder - Press the checkmark icon in the page nav
        RecorderPage.clickCheckMarkButton(driver);

        // Selection page - Click the OK button in the Help dialog box (if present)
        SelectionPage.Dialogbox.clickOkButton(driver);

        // Selection page - Press the checkmark icon in the page nav
        SelectionPage.clickCheckMarkButton(driver);

        // Your Selection page - Press the checkmark icon in the page nav
        YourSelectionPage.clickCheckMarkButton(driver);

        // Filters page - Press Next button
        FiltersPage.clickNextButton(driver);

        // Notification Conditions page - Press Finish button
        NotificationConditionsPage.clickNextButton(driver);

        // Home page - verify that the page has been added to the app pages list
        // change the name of the page to the website URL without protocol
        MobileElement lastPageAddedListElement = HomePage.getLastElementInList(driver);

        // Home page - open options dialog for a page item
        HomePage.PageDialogBox.openDialogBoxForPageItem(driver, lastPageAddedListElement);

        // Page options dialog - tap on Edit menu item
        HomePage.PageDialogBox.clickEditButton(driver);

        // Edit page - Tap on General > Name button
        ConfigurationPage.clickNameButton(driver);

        // Name dialog - erase all in dialog box text area
        ConfigurationPage.NameDialogBox.setEditBoxValue(driver, name);

        driver.findElement(dialogOkButton).click();

        // press (system/UI) back button
        ConfigurationPage.clickBackButton(driver);

    }

    public static void End() {
        System.out.println("Stop driver");
        driver.quit(); // backgrounds and defaults app; adb process for socket connections will not be ended
        System.out.println("Stop appium service");
        appiumService.stop();
    }

}