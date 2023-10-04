package org.example.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.FileOutputStream;
import java.io.IOException;

public class HttpStatusImageDownloader {
    private final HttpStatusChecker checker = new HttpStatusChecker();
    private final OkHttpClient client = new OkHttpClient();

    public void downloadStatusImage(int code) throws HttpStatusException {

        String imageUrl;

        try {
            imageUrl = checker.getStatusImage(code);
        } catch (Exception e) {
            throw new HttpStatusException("Image not found for HTTP status ");
        }

        Request request = new Request.Builder()
                .url(imageUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new HttpStatusException("Error downloading image. HTTP status code: ");
            }

            ResponseBody body = response.body();
            if (body == null) {
                throw new HttpStatusException("Error downloading image. Response body is null.");
            }

            String fileName = "cat " + code + ".jpg";

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                fos.write(body.bytes());
                System.out.println("Image downloaded and saved as " + fileName);
            } catch (IOException e) {
                throw new HttpStatusException("Error saving image: " + e.getMessage());
            }
        } catch (IOException e) {
            throw new HttpStatusException("Error downloading image: " + e.getMessage());
        }
    }
}
