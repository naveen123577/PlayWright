package com.automation;

import com.microsoft.playwright.*;

public class AlertHandling {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        BrowserContext context = browser.newContext(); // Create a new browser context
        Page page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

        page.onDialog(dialog -> {
            System.out.println("Dialog message: " + dialog.message());
            if (dialog.type().equals("alert")) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                dialog.accept();
                // Accept an alert dialog
            }
            else if (dialog.type().equals("confirm")) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                dialog.accept();
            }
            else if (dialog.type().equals("prompt")) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                dialog.accept("Hello theree"); // Provide input text for the prompt
            }
        });
        page.locator("//button[text()='Click for JS Confirm']").click();
        page.waitForTimeout(2000);
        page.locator("//button[text()='Click for JS Alert']").click();
        page.waitForTimeout(2000);
        page.locator("//button[text()='Click for JS Prompt']").click();
        page.waitForTimeout(2000);
        browser.close();
    }
}


