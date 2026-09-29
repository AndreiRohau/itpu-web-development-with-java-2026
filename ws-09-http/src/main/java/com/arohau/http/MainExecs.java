package com.arohau.http;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainExecs {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException, ExecutionException, InterruptedException {
        HttpRequest postRequest_5 = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofFile(
                        Paths.get("ws-09-http/src/main/resources/sample.txt")))
                .build();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println("1");
        CompletableFuture<HttpResponse<String>> response1 = HttpClient.newBuilder()
                .executor(executorService)
                .build()
                .sendAsync(postRequest_5, HttpResponse.BodyHandlers.ofString());
        System.out.println("2");

        CompletableFuture<HttpResponse<String>> response2 = HttpClient.newBuilder()
                .executor(executorService)
                .build()
                .sendAsync(postRequest_5, HttpResponse.BodyHandlers.ofString());
        System.out.println("3");

        HttpResponse<String> stringHttpResponse1 = response1.get();
        System.out.println("4");
        System.out.println(stringHttpResponse1);
        System.out.println("5");
        HttpResponse<String> stringHttpResponse2 = response2.get();
        System.out.println("6");
        System.out.println(stringHttpResponse2);
        System.out.println("7");

        executorService.close();
    }
}
