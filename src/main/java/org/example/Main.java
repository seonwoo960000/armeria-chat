package org.example;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = Server.builder()
                              .http(8080)
                              .service("/hello", (ctx, req) -> HttpResponse.of("hello world"))
                              .build();

        server.closeOnJvmShutdown();

        server.start().join();
    }
}
