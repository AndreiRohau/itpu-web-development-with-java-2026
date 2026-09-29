package com.arohau.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class MainAsync {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, ExecutionException {
        HttpRequest postRequest_5 = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofFile(
                        Paths.get("ws-09-http/src/main/resources/sample.txt")))
                .build();
        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> responseO = client
                .send(postRequest_5, HttpResponse.BodyHandlers.ofString());

        CompletableFuture<HttpResponse<String>> responseA = client
                .sendAsync(postRequest_5, HttpResponse.BodyHandlers.ofString());

        example();
    }

    private static void example() throws URISyntaxException, ExecutionException, InterruptedException {
        List<URI> targets = Arrays.asList(
                new URI("https://postman-echo.com/get?foo1=bar1"),
                new URI("https://postman-echo.com/get?foo2=bar2"));
        HttpClient client = HttpClient.newHttpClient();
        List<CompletableFuture<String>> futures = targets.stream()
                .map(target -> client
                        .sendAsync(
                                HttpRequest.newBuilder(target).GET().build(),
                                HttpResponse.BodyHandlers.ofString())
                        .thenApply(response -> response.body()))
                .collect(Collectors.toList());

        for (CompletableFuture<String> future : futures) {
            String s = future.get();
            System.out.println("s: " + s);
        }
    }
}
