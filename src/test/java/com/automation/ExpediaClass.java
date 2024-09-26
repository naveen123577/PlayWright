package com.automation;

import com.microsoft.playwright.*;

public class ExpediaClass {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
//        Page page = browser.newPage();
        BrowserContext context = browser.newContext(); // Create a new browser context
        Page page = context.newPage();
        page.navigate("https://www.expedia.co.in/");

        Locator flightsLink = page.locator("//span[@class='uitk-tab-text' and text()='Flights']");
        flightsLink.click();

        Locator leavingFrom = page.locator("(//div[@class='uitk-field has-floatedLabel-label has-icon'])[1]");
        leavingFrom.click();

        Locator leavingFromInput = page.locator("#origin_select");
        leavingFromInput.fill("Hyderabad");

        Locator leavingFromFirstOption = page.locator("(//button[contains(@class,'origin_select-result-item-button')])[1]");
        leavingFromFirstOption.click();

        Locator leavingTo = page.locator("(//div[@class='uitk-field has-floatedLabel-label has-icon'])[2]");
        leavingTo.click();

        Locator leavingToInput = page.locator("#destination_select");
        leavingToInput.fill("Delhi");

        Locator leavingToFirstOption = page.locator("(//button[contains(@class,'destination_select-result-item-button')])[1]");
        leavingToFirstOption.click();

        String checkInDate = "18 October 2024";
        String checkOutDate = "13 November 2024";

        page.locator("//button[@name='EGDSDateRange-date-selector-trigger']").click();
        selectDate(checkInDate, page);
        selectDate(checkOutDate, page);

        Locator doneBtn = page.locator("//button[contains(@class,'text uitk-button-primary ')]");
        doneBtn.click();

        doneBtn.click();

        Locator travelerSelect = page.locator("//button[@data-stid='open-room-picker']");
        travelerSelect.click();

        String value = page.locator("traveler_selector_adult_step_input").getAttribute("value");
        int val = Integer.parseInt(value);
//        System.out.println("Value is: " + value);
        while(val!=3){
            Locator addBtn = page.locator("(//span[@class='uitk-step-input-button'])[2]");
            addBtn.click();
            value=page.locator("traveler_selector_adult_step_input").getAttribute("value");
            val = Integer.parseInt(value);
        }

    }

    public static void selectDate(String date, Page page) {
        String monthYear = date.substring(date.indexOf(" ") + 1);
        String day = date.substring(0, date.indexOf(" "));

        Locator monthYearElement = page.locator("//div[contains(@class,'uitk-month-double-left')]//span[contains(@class, 'uitk-month-label')]");

        // Loop until the correct month-year is found
        while (!monthYearElement.textContent().equals(monthYear)) {
            // Click the next button
            page.locator("//button[contains(@data-stid, 'next-button')]").click();
            monthYearElement = page.locator("//div[contains(@class,'uitk-month-double-left')]//span[contains(@class, 'uitk-month-label')]");
        }

        // Select the day
        String xpath = "//div[contains(@class,'uitk-month-double-left')]//div[contains(@class,'uitk-date-number') and text() = '%s']";
        Locator dayElement = page.locator(String.format(xpath, day));
        dayElement.click();
    }
}

