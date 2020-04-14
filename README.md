# Conveyer for WebAlert
This is a tool enveloping the WebAlert Android app which does not support data imports.  The WebAlert app has the ability to track changes between different instances of a web page.  Each page has to be added to the app one by one because adding a page requires completing a lengthy configuration process.  If many pages need to be added, it may take a long time to add them all because it takes about 1 minute to add a page; the more configuration required, the longer it takes.   

Note: the items listed on the Home page will be referred to as either *jobs*, *configurations*, *pages*, or any combination of those words.  An item is composed of all the configuration options and difference calculations on page source instances.  

## Getting Started
### Requirements
* Physical Android device
* A computer

### Environment Setup
1. Install Appium
2. Install Selenium
3. Install Android Studio with a recent Android SDK and SDK tools
4. (Optional) Install WebStorm

### Getting the apk
1. Download the WebAlert app from the Play Store
2. Pull the apk from the device into a local directory on your computer and remember the local directory's full path.

### First Run
*coming soon*

## CLI Options

**--allowDuplicates**
Allowing duplicate configurations/pages to be added to the app will speed up the import process because logic does not have to be implemented for detecting duplicate content.  Not allowing it though would guarantee the app user that every tracking job is unique.  

## Tasks
-[x] Create a class per page/view in the app
-[ ] Create CLI UI
-[ ] Create CLI option parsing method
-[ ] Create feature flag procedure called *allowDuplicates* to avoid adding a page that already exists
-[ ] Create a feature flag procedure called *batchUpload* for adding many pages consecutively
-[ ] Create configuration setting for submitting login information with the addition of a page job
-[ ] Create configuration setting for giving each page a name