package com.arohau.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Example {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        example();
    }

    private static void example() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpGetRequest = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("https://www.google.com"))
//                .headers()
//                .version(null)
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> httpResponse = client.send(httpGetRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println(httpResponse.headers());

    }
}
