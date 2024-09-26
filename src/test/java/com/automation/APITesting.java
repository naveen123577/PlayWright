package com.automation;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class APITesting {
    public static void main(String[] args) {
        String body ="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        Playwright playwright = Playwright.create();
        APIRequest request = playwright.request();
        APIRequestContext requestContext = request.newContext();

        //Create Token
        RequestOptions options = RequestOptions.create();
        options.setData(body);
        options.setHeader("Content-Type","application/json");
        APIResponse response = requestContext.post("https://restful-booker.herokuapp.com/auth",options);
        System.out.println(response.text());
        System.out.println(response.status());

        //Get
        APIResponse response2 = requestContext.get("https://restful-booker.herokuapp.com/booking/1",options);
        System.out.println(response2.text());
        System.out.println(response2.status());

        //Post
        String body2 ="{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        RequestOptions optionsPost = RequestOptions.create();
        optionsPost.setData(body2);
        optionsPost.setHeader("Content-Type","application/json");
        APIResponse response3 = requestContext.post("https://restful-booker.herokuapp.com/booking",optionsPost);
        System.out.println(response3.text());
        System.out.println(response3.status());
    }
}
