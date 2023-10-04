package org.example.http;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpStatusChecker {
    private static final String BASE_URL = "https://http.cat/";
    private final OkHttpClient client = new OkHttpClient();

    public String getStatusImage(int code) throws HttpStatusException {

        String url = BASE_URL + code + ".jpg";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new HttpStatusException("HTTP status code not found");
            }
            return url;
        } catch (Exception e) {
            throw new HttpStatusException("Error executing HTTP request: " + e.getMessage());
        }
    }
}
