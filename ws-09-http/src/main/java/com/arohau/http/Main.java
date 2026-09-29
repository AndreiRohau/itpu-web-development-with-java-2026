package com.arohau.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        List<HttpRequest> requests = new ArrayList<>();

        HttpRequest getRequest_1 = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("https://postman-echo.com/get"))
                .version(HttpClient.Version.HTTP_2)
                .headers("k1", "v1", "k2", "v2")
                .header("key3", "value3")
                .timeout(Duration.of(10, SECONDS))
                .build();
        requests.add(getRequest_1);

        HttpRequest postRequest_1 = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        requests.add(postRequest_1);

        HttpRequest postRequest_2 = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString("Sample request body"))
                .build();
        requests.add(postRequest_2);

        byte[] sampleData = "Sample request body".getBytes();
        HttpRequest postRequest_3 = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers
                        .ofInputStream(() -> new ByteArrayInputStream(sampleData)))
                .build();
        requests.add(postRequest_3);

        HttpRequest postRequest_4 = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofByteArray(sampleData))
                .build();
        requests.add(postRequest_4);

        HttpRequest postRequest_5 = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofFile(
                        Paths.get("ws-09-http/src/main/resources/sample.txt")))
                .build();
        requests.add(postRequest_5);


        // INIT CLIENT
        HttpClient client = HttpClient.newHttpClient();

        for (HttpRequest request : requests) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(getResponse(response));
        }

        HttpResponse<String> proxyResponse = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(postRequest_5, HttpResponse.BodyHandlers.ofString());
        System.out.println("Proxy: " + getResponse(proxyResponse));

        HttpResponse<String> response = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build()
                .send(postRequest_5, HttpResponse.BodyHandlers.ofString());

    }

    private static String getResponse(HttpResponse<String> response) {
        String toStr = response.uri() + "\n" +
        response.version() + "\n" +
        response.headers() + "\n" +
        response.body() + "\n" + "";
        return toStr;
    }
}
