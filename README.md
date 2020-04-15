# Conveyer for WebAlert
This is a tool enveloping the WebAlert Android app which does not support data imports.  The WebAlert app has the ability to track changes between different instances of a web page.  Each page has to be added to the app one by one because adding a page requires completing a lengthy configuration process.  If many pages need to be added, it may take a long time to add them all because it takes about 1 minute to add a page; the more configuration required, the longer it takes.   

Note: the items listed on the Home page will be referred to as either *jobs*, *configurations*, *pages*, *alerts*, or any combination of those words.  An item is composed of all the configuration options and difference calculations on page source instances.  

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

### Getting the code
Clone this repository.

### First Run
Before compiling any code, configurations need to be setup.  A configuration file is required by the program on every run.  The configuration file will be in a JSON format with the following structure: 
```json
{
  "pages": [
    {
      "name":"name of the website (defaults to domain name)",
      "address":"URL of website (include https)",
      "userAgent":"Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162"
    }
  ]
}
```

## CLI Options

**--allowDuplicates**
Allowing duplicate configurations/pages to be added to the app will speed up the import process because logic does not have to be implemented for detecting duplicate content.  Not allowing it though would guarantee the app user that every tracking job is unique.  

## Tasks
- [x] Create a class per page/view in the app
- [x] Create CLI UI
- [x] Create CLI option parsing method
- [ ] Create feature flag procedure called *allowDuplicates* to avoid adding a page that already exists
- [x] Create a feature flag procedure called *configFile* for adding many pages consecutively
- [ ] Create configuration setting for submitting login information with the addition of a page job
- [x] Create configuration settings validator program