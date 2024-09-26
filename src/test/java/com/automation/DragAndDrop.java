package com.automation;

import com.microsoft.playwright.*;

public class DragAndDrop {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        BrowserContext context = browser.newContext(); // Create a new browser context
        Page page = context.newPage();
        page.navigate("https://www.globalsqa.com/demo-site/draganddrop/");

        FrameLocator iframe = page.frameLocator("//iframe[@class='demo-frame lazyloaded']");

        for(int i =0 ; i<4 ; i++){
            Locator source = iframe.locator("//li[@class='ui-widget-content ui-corner-tr ui-draggable ui-draggable-handle']").first();
            Locator target = iframe.locator("#trash");
            source.dragTo(target);
            page.waitForTimeout(2000);
        }

    }

}
