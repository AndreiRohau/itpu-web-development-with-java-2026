package com.arohau.http;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class MainPushPromise {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        HttpRequest postRequest_5 = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofFile(
                        Paths.get("ws-09-http/src/main/resources/sample.txt")))
                .build();
        HttpClient httpClient = HttpClient.newBuilder().build();

        Void join = httpClient.sendAsync(postRequest_5, HttpResponse.BodyHandlers.ofString(), pushPromiseHandler())
                .thenAccept(pageResponse -> {
                    System.out.println("Page response status code: " + pageResponse.statusCode());
                    System.out.println("Page response headers: " + pageResponse.headers());
                    String responseBody = pageResponse.body();
                    System.out.println(responseBody);
                })
                .join();
    }

    private static HttpResponse.PushPromiseHandler<String> pushPromiseHandler() {
        return (HttpRequest initiatingRequest,
                HttpRequest pushPromiseRequest,
                Function<HttpResponse.BodyHandler<String>,
                        CompletableFuture<HttpResponse<String>>> acceptor) -> {
            acceptor.apply(HttpResponse.BodyHandlers.ofString())
                    .thenAccept(resp -> {
                        System.out.println(" Pushed response: " + resp.uri() + ", headers: " + resp.headers());
                    });
            System.out.println("Promise request: " + pushPromiseRequest.uri());
            System.out.println("Promise request: " + pushPromiseRequest.headers());
        };
    }
}
