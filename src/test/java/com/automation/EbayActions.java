package com.automation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EbayActions {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        BrowserContext context = browser.newContext(); // Create a new browser context
        Page page = context.newPage();
        page.navigate("https://www.ebay.com/");

//        page.getByLabel("Search for anything").fill("java");
//
//        Locator searchBtn = page.locator("#gh-btn");
//        searchBtn.click();

        //click on advanced
        Locator advancedLink = page.locator("#gh-as-a");
        advancedLink.click();

        //input text
        page.locator("input#_nkw").fill("laptop");

        //dropdown
        page.getByLabel("In this category").selectOption("Art");

        //checkbox
        page.locator("[name='LH_TitleDesc']").check();

        //radio button
        page.locator("//input[@id='s0-1-17-6[4]-[0]-LH_ItemCondition']").check();

        //select multiple options
        page.locator("//input[@id='s0-1-17-5[5]-[0]-LH_FR']").check();
        page.locator("//input[@id='s0-1-17-5[5]-[1]-LH_RPA']").check();
        page.locator("//input[@id='s0-1-17-5[5]-[2]-LH_AS']").check();

        // Assert the checked state
        assertThat(page.locator("//input[@id='s0-1-17-5[5]-[0]-LH_FR']")).isChecked();

        //scrolling
        page.locator("(//button[text()='Search'])[2]").scrollIntoViewIfNeeded();

        Locator searchBtn = page.locator("(//button[text()='Search'])[2]");
        searchBtn.click();

        //go to home Btn
        page.locator("#gh-logo").click();

        //hovering
        Locator hoverItem = page.locator("//li[@class='vl-flyout-nav__js-tab']").first();
        hoverItem.hover();


//        //first item click
//        Locator firstItem = page.locator("//div[@class='s-item__title']").first();
//        firstItem.click();

        //playwright.close();
    }
}

