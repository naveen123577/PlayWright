package com.automation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Paths;
import java.util.Collections;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class OrangeHrm {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
//        Page page = browser.newPage();
        BrowserContext context = browser.newContext(); // Create a new browser context
        Page page = context.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com");

        Locator username = page.locator("//input[@class='oxd-input oxd-input--focus']");
        username.fill("Admin");

        Locator password = page.locator("//input[@class='oxd-input oxd-input--active']");
        password.fill("admin123");

        Locator loginBtn = page.locator("//button[contains(@class,'orangehrm-login-button')]");

//        Page newPage = context.waitForPage(loginBtn::click);

        loginBtn.click();

        page.waitForLoadState(LoadState.LOAD);
        assertThat(page.locator(".oxd-userdropdown")).isVisible();
        page.screenshot(new Page
                .ScreenshotOptions()
                .setPath(Paths.get("C:\\Users\\280664\\UST_Java\\PlaywriteAutomation\\target\\screenshot.png"))
                .setFullPage(true));


        Page newPage = context.newPage();
        newPage.navigate("https://opensource-demo.orangehrmlive.com");

        BrowserContext context2 = browser.newContext(); // Create a new browser context
        Page page2 = context2.newPage();
        page2.navigate("https://opensource-demo.orangehrmlive.com");

        page2.waitForLoadState(LoadState.LOAD);
        Locator userInput=page2.locator("//input[@placeholder = 'Username']");
        assertThat(userInput).isVisible();
        page2.screenshot(new Page
                .ScreenshotOptions()
                .setMask(Collections.singletonList(userInput))
                .setMaskColor("Blue")
                .setPath(Paths.get("C:\\Users\\280664\\UST_Java\\PlaywriteAutomation\\target\\screenshot2.png"))
                .setFullPage(true));

        playwright.close();
    }
}
