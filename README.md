# Conveyer for WebAlert
This is a tool enveloping the WebAlert Android app which does not support data imports.  The WebAlert app has the ability to track changes between different instances of a web page.  Each page has to be added to the app one by one because adding a page requires completing a lengthy configuration process.  If many pages need to be added, it may take a long time to add them all because it takes about 1 minute to add a page; the more configuration required, the longer it takes.   

## Getting Started
### Environment Setup
1. Install Appium
2. Install Selenium
3. Install Android Studio with a recent Android SDK and SDK tools
4. (Optional) Install WebStorm

### Getting the apk
1. Download the WebAlert app from the Play Store
2. Pull the apk from the device into a local directory on your computer and remember the local directory's full path.

### First Run
*Coming soon*

### 

## Tasks
-[x] Create a class per page/view in the app
-[ ] Create CLI UI
-[ ] Create CLI option parsing method
-[ ] Create feature flag procedure called *avoidDuplicates* to avoid adding a page that already exists
-[ ] Create a feature flag procedure called *batchUpload* for adding many pages consecutively
