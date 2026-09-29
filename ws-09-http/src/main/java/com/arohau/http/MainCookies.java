package com.arohau.http;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.List;

public class MainCookies {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest postRequest_5 = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofFile(
                        Paths.get("ws-09-http/src/main/resources/sample.txt")))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_NONE))
                .build();

        httpClient.send(postRequest_5, HttpResponse.BodyHandlers.ofString());

        CookieStore cookieStore = ((CookieManager) httpClient.cookieHandler().get()).getCookieStore();
        List<HttpCookie> cookies = cookieStore.getCookies();
        for (HttpCookie cookie : cookies) {
            System.out.println(cookie.getName() + " ===>>> " + cookie.getValue());
        }
    }
}
