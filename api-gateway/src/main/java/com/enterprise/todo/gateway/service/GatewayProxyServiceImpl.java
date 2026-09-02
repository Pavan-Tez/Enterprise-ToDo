package com.enterprise.todo.gateway.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;

public class GatewayProxyServiceImpl implements GatewayProxyService {

    @Override
    public void forward(
            HttpServletRequest request,
            HttpServletResponse response,
            String targetBaseUrl,
            Long authenticatedUserId,
            String authenticatedUsername) {

        try {
            String targetUrl =
                    targetBaseUrl + request.getRequestURI();

            if (request.getQueryString() != null) {
                targetUrl += "?" + request.getQueryString();
            }

            URL url = new URL(targetUrl);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setInstanceFollowRedirects(false);
            
            connection.setRequestMethod(
                    request.getMethod()
            );

            connection.setDoInput(true);

            Enumeration<String> headerNames =
                    request.getHeaderNames();
            
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                if ("X-User-Id".equalsIgnoreCase(headerName)
                        || (authenticatedUserId != null
                        && "Cookie".equalsIgnoreCase(headerName))) {
                    continue;
                }
                String headerValue = request.getHeader(headerName);
                connection.setRequestProperty(headerName, headerValue);
            }

            if (authenticatedUserId != null) {
                connection.setRequestProperty(
                        "X-User-Id",
                        String.valueOf(authenticatedUserId));
                connection.setRequestProperty(
                        "X-User-Name",
                        authenticatedUsername == null ? "" : authenticatedUsername);
            }

            if("POST".equalsIgnoreCase(request.getMethod())
                || "PUT".equalsIgnoreCase(request.getMethod())
                || "PATCH".equalsIgnoreCase(request.getMethod())) {

                connection.setDoOutput(true);

                try(InputStream inputStream = request.getInputStream();
                    OutputStream outputStream = connection.getOutputStream()) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }

            // response.setStatus(
            //         connection.getResponseCode()
            // );

            int responseCode = connection.getResponseCode();
            response.setStatus(responseCode);

            Iterator<String> responseHeaderNames = connection.getHeaderFields().keySet().iterator();

            while (responseHeaderNames.hasNext()) {
                String headerName = responseHeaderNames.next();
                if (headerName != null
                        && !(authenticatedUserId != null
                        && "Set-Cookie".equalsIgnoreCase(headerName))) {
                    String headerValue = connection.getHeaderField(headerName);
                    response.setHeader(headerName, headerValue);
                }
            }

            try(InputStream inputStream = responseCode >= 400 ? connection.getErrorStream() : connection.getInputStream();
                OutputStream outputStream = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to forward request",
                    e
            );
        }
    }
}
