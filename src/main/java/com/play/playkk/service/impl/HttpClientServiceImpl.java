package com.play.playkk.service.impl;

import com.play.playkk.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
//import java.net.http.HttpClient;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// serwis klienta http
@Service
public class HttpClientServiceImpl implements HttpClientService {

    private final HttpClient httpClient;

    @Autowired
    public HttpClientServiceImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String sendPostRequest(String url, String jsonBody) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
