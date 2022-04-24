package ru.kpfu.itis.gnt.util;

import java.io.BufferedInputStream;

import java.io.IOException;
import java.net.URL;

public class UrlResponse {
    public static String getHttpResponse(URL url) {
        StringBuilder responseBody = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(url.openStream())) {
            int b;
            while ((b = in.read()) != -1) {
                responseBody.append((char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody.toString();
    }

}
