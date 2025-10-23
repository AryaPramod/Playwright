package com.utilities;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import com.pages.AdminPage;
import com.pages.LoginPage;
import com.pages.SearchUser;

import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;

import org.testng.annotations.AfterMethod;

public class BaseTest {
	protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext context;
    protected static Page page;
    
    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @AfterMethod
    public void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
    public static Page getPage() {
        return page;
    }
    public static String captureScreenshot(String testName) {
        try {
            String folderPath = System.getProperty("user.dir") + "/ExcelReport";
            java.io.File folder = new java.io.File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String screenshotPath = folderPath + "/" + testName + ".png";
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
            System.out.println("Screenshot saved at: " + screenshotPath);
            return screenshotPath;
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
    public static void handleAlert() {
        try {
            page.onDialog(dialog -> {
                System.out.println("Alert text: " + dialog.message());
                dialog.accept();
            });
        } catch (Exception e) {
            System.out.println("No alert present or failed to handle alert: " + e.getMessage());
        }
    }
}