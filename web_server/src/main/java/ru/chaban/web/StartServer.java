package ru.chaban.web;

import ru.chaban.web.server.UsersWebServer;
import ru.chaban.web.server.UsersWebServerSimple;

public class StartServer {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        UsersWebServer usersWebServer = new UsersWebServerSimple(WEB_SERVER_PORT, null, null, null);

        usersWebServer.start();
        usersWebServer.join();
    }
}
