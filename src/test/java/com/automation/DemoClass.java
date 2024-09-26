package com.automation;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DemoClass {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.saucedemo.com/");
        Locator usernameInput = page.locator("#user-name");
        Locator passwordInput = page.locator("#password");
        Locator loginBtn = page.locator("#login-button");
        usernameInput.fill("standard_user");
        passwordInput.fill("secret_sauce");
        loginBtn.click();

        assertThat(page.locator("span.title")).hasText("Products");

        Locator items = page.locator(".inventory_item");
        for(Locator item : items.all()){
            Locator firstItemPrice = item.locator(".inventory_item_price");
            String price = firstItemPrice.textContent();
            System.out.println("The price of the item is: " + price);
        }
        Locator firstItem = items.first();
        Locator firstItemAddToCartBtn = firstItem.locator("#add-to-cart-sauce-labs-backpack");
        firstItemAddToCartBtn.click();
        Locator cartIcon = page.locator("#shopping_cart_container");
        cartIcon.click();
        Locator cartPrice = page.locator(".inventory_item_price");
        String Price = cartPrice.textContent();
        System.out.println("Price in the cart :" + Price);
        Locator checkoutBtn = page.locator("#checkout");
        checkoutBtn.click();

        Locator firstName = page.locator("#first-name");
        Locator lastName = page.locator("#last-name");
        Locator zipCode = page.locator("#postal-code");
        firstName.fill("Arjun");
        lastName.fill("Champion");
        zipCode.fill("1234567");
        Locator continueBtn = page.locator("#continue");
        continueBtn.click();

        Locator priceInCheckOut = page.locator(".summary_total_label");
        String priceCheckOut = priceInCheckOut.textContent();
        System.out.println("Total Price After Tax: "+priceCheckOut);
        Locator finishBtn = page.locator("#finish");
        finishBtn.click();
        String thankYouMsg = page.locator("h2.complete-header").textContent();
        System.out.println(thankYouMsg);
        assertThat(page.locator("h2.complete-header")).hasText("Thank you for your order!");

//        playwright.close();
        System.out.println("Execution Done");
    }
}
