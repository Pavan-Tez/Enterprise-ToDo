package com.enterprise.todo.gateway.service;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class GatewayProxyServiceImplTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private GatewayProxyServiceImpl proxyService;

    private HttpServer server;

    private String baseUrl;

    private ByteArrayOutputStream responseOutput;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        proxyService = new GatewayProxyServiceImpl();

        responseOutput = new ByteArrayOutputStream();

        when(response.getOutputStream())
                .thenReturn(new ServletOutputStreamStub(responseOutput));

        server = HttpServer.create(
                new InetSocketAddress("localhost", 0),
                0
        );

        baseUrl = "http://localhost:" +
                server.getAddress().getPort();

        server.start();
    }

    @After
    public void tearDown() {

        if (server != null) {
            server.stop(0);
        }
    }

    // =========================================================
    // GET
    // =========================================================

    @Test
    public void forward_getRequest_shouldForwardPathAndQuery()
            throws Exception {

        server.createContext("/todos/1", new HttpHandler() {

            @Override
            public void handle(HttpExchange exchange)
                    throws IOException {

                assertEquals("GET", exchange.getRequestMethod());
                assertEquals(
                        "/todos/1?status=TODO",
                        exchange.getRequestURI().toString()
                );

                byte[] body = "todo-response".getBytes(StandardCharsets.UTF_8);

                exchange.sendResponseHeaders(200, body.length);

                try (OutputStream output =
                             exchange.getResponseBody()) {

                    output.write(body);
                }
            }
        });

        when(request.getRequestURI())
                .thenReturn("/todos/1");

        when(request.getQueryString())
                .thenReturn("status=TODO");

        when(request.getMethod())
                .thenReturn("GET");

        when(request.getHeaderNames())
                .thenReturn(java.util.Collections.emptyEnumeration());

        proxyService.forward(
                request,
                response,
                baseUrl,
                null,
                null
        );

        verify(response).setStatus(200);

        assertEquals(
                "todo-response",
                responseOutput.toString("UTF-8")
        );
    }

    // =========================================================
    // AUTHENTICATED USER HEADERS
    // =========================================================

    @Test
    public void forward_authenticatedUser_shouldAddUserHeaders()
            throws Exception {

        server.createContext("/todos", new HttpHandler() {

            @Override
            public void handle(HttpExchange exchange)
                    throws IOException {

                assertEquals(
                        "10",
                        exchange.getRequestHeaders()
                                .getFirst("X-User-Id")
                );

                assertEquals(
                        "testuser",
                        exchange.getRequestHeaders()
                                .getFirst("X-User-Name")
                );

                byte[] body = "ok".getBytes(StandardCharsets.UTF_8);

                exchange.sendResponseHeaders(200, body.length);

                try (OutputStream output =
                             exchange.getResponseBody()) {

                    output.write(body);
                }
            }
        });

        when(request.getRequestURI())
                .thenReturn("/todos");

        when(request.getMethod())
                .thenReturn("GET");

        when(request.getHeaderNames())
                .thenReturn(java.util.Collections.emptyEnumeration());

        proxyService.forward(
                request,
                response,
                baseUrl,
                10L,
                "testuser"
        );

        verify(response).setStatus(200);
    }

    // =========================================================
    // SPOOFED USER ID
    // =========================================================

    @Test
    public void forward_shouldIgnoreClientUserId()
            throws Exception {

        server.createContext("/todos", new HttpHandler() {

            @Override
            public void handle(HttpExchange exchange)
                    throws IOException {

                assertEquals(
                        "10",
                        exchange.getRequestHeaders()
                                .getFirst("X-User-Id")
                );

                byte[] body = "ok".getBytes(StandardCharsets.UTF_8);

                exchange.sendResponseHeaders(200, body.length);

                try (OutputStream output =
                             exchange.getResponseBody()) {

                    output.write(body);
                }
            }
        });

        when(request.getRequestURI())
                .thenReturn("/todos");

        when(request.getMethod())
                .thenReturn("GET");

        when(request.getHeaderNames())
                .thenReturn(
                        java.util.Collections.enumeration(
                                java.util.Arrays.asList("X-User-Id")
                        )
                );

        when(request.getHeader("X-User-Id"))
                .thenReturn("999");

        proxyService.forward(
                request,
                response,
                baseUrl,
                10L,
                "testuser"
        );

        verify(response).setStatus(200);
    }

    // =========================================================
    // POST BODY
    // =========================================================

        @Test
        public void forward_postRequest_shouldForwardBody()
                throws Exception {

        final String requestBody =
                "{\"title\":\"Learn testing\"}";

        server.createContext("/todos", new HttpHandler() {

                @Override
                public void handle(HttpExchange exchange)
                        throws IOException {

                assertEquals("POST", exchange.getRequestMethod());

                InputStream inputStream =
                        exchange.getRequestBody();

                ByteArrayOutputStream outputStream =
                        new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                }

                byte[] received =
                        outputStream.toByteArray();

                assertEquals(
                        requestBody,
                        new String(
                                received,
                                StandardCharsets.UTF_8
                        )
                );

                byte[] body =
                        "created".getBytes(StandardCharsets.UTF_8);

                exchange.sendResponseHeaders(
                        201,
                        body.length
                );

                try (OutputStream output =
                                exchange.getResponseBody()) {

                        output.write(body);
                }
                }
        });

        when(request.getRequestURI())
                .thenReturn("/todos");

        when(request.getMethod())
                .thenReturn("POST");

        when(request.getHeaderNames())
                .thenReturn(
                        java.util.Collections.emptyEnumeration()
                );

        when(request.getInputStream())
                .thenReturn(
                        new ServletInputStreamStub(
                                new ByteArrayInputStream(
                                        requestBody.getBytes(
                                                StandardCharsets.UTF_8
                                        )
                                )
                        )
                );

        proxyService.forward(
                request,
                response,
                baseUrl,
                null,
                null
        );

        verify(response).setStatus(201);

        assertEquals(
                "created",
                responseOutput.toString("UTF-8")
        );
        }
    // =========================================================
    // BACKEND ERROR
    // =========================================================

    @Test
    public void forward_backendError_shouldForwardStatusAndBody()
            throws Exception {

        server.createContext("/todos/999", new HttpHandler() {

            @Override
            public void handle(HttpExchange exchange)
                    throws IOException {

                byte[] body =
                        "Todo not found".getBytes(StandardCharsets.UTF_8);

                exchange.sendResponseHeaders(404, body.length);

                try (OutputStream output =
                             exchange.getResponseBody()) {

                    output.write(body);
                }
            }
        });

        when(request.getRequestURI())
                .thenReturn("/todos/999");

        when(request.getMethod())
                .thenReturn("GET");

        when(request.getHeaderNames())
                .thenReturn(java.util.Collections.emptyEnumeration());

        proxyService.forward(
                request,
                response,
                baseUrl,
                null,
                null
        );

        verify(response).setStatus(404);

        assertEquals(
                "Todo not found",
                responseOutput.toString("UTF-8")
        );
    }

    // =========================================================
    // IO FAILURE
    // =========================================================

    @Test
    public void forward_connectionFailure_shouldThrowRuntimeException() {

        when(request.getRequestURI())
                .thenReturn("/todos");

        when(request.getMethod())
                .thenReturn("GET");

        when(request.getHeaderNames())
                .thenReturn(java.util.Collections.emptyEnumeration());

        try {
            proxyService.forward(
                    request,
                    response,
                    "http://localhost:1",
                    null,
                    null
            );

            fail("Expected RuntimeException");

        } catch (RuntimeException ex) {

            assertEquals(
                    "Failed to forward request",
                    ex.getMessage()
            );
        }
    }

    // =========================================================
    // SIMPLE SERVLET OUTPUT STREAM
    // =========================================================

    private static class ServletOutputStreamStub
        extends javax.servlet.ServletOutputStream {

        private final OutputStream outputStream;

        ServletOutputStreamStub(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(
                javax.servlet.WriteListener writeListener) {
            // Not required for this test
        }
    }

    // =========================================================
    // SIMPLE SERVLET INPUT STREAM
    // =========================================================

    private static class ServletInputStreamStub
        extends javax.servlet.ServletInputStream {

        private final InputStream inputStream;

        ServletInputStreamStub(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public int read() throws IOException {
            return inputStream.read();
        }

        @Override
        public boolean isFinished() {
            try {
                return inputStream.available() == 0;
            } catch (IOException e) {
                return true;
            }
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(
                javax.servlet.ReadListener readListener) {
            // Not required for this test
        }
    }
}
