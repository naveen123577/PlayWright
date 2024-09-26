package com.automation;

import com.microsoft.playwright.*;

public class EbayClass {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
//        Page page = browser.newPage();
        BrowserContext context = browser.newContext(); // Create a new browser context
        Page page = context.newPage();
        page.navigate("https://www.ebay.com/");

        Locator advancedLink = page.locator("#gh-as-a");
        advancedLink.click();

        Locator itemName = page.locator("#_nkw");
        itemName.fill("Java");

        page.selectOption("//select[@id ='s0-1-17-4[0]-7[3]-_sacat']","267");

        Locator minPrice = page.locator("//input[@id = 's0-1-17-5[2]-@range-comp[]-@range-textbox[]-textbox']");
        minPrice.fill("0");

        Locator maxPrice = page.locator("//input[@id = 's0-1-17-5[2]-@range-comp[]-@range-textbox[]_1-textbox']");
        maxPrice.fill("100");

        Locator newRadioCondition = page.locator("//input[@id = 's0-1-17-6[4]-[0]-LH_ItemCondition']");
        newRadioCondition.click();

        page.selectOption("//select[@id='s0-1-17-6[7]-5[@field[]]_1-_saact']","95");
        Locator searchBtn = page.locator("(//button[@class='btn btn--primary'])[2]");
        searchBtn.click();

        Locator firstItem = page.locator("(//div[@class='s-item__title']/span)[3]").first();
//        firstItem.click();

        // This triggers the opening of the new tab
        Page newPage = context.waitForPage(firstItem::click);

        Locator priceInCart = newPage.locator("//div[@class='x-price-primary']/span");
        String price = priceInCart.textContent();
        System.out.println("Price is :" + price);
        playwright.close();
    }
}
