package com.play.playkk.service;

import java.io.IOException;

public interface HttpClientService {

    String sendPostRequest(String url, String jsonBody) throws IOException, InterruptedException;
}
