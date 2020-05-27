package ru.chaban.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.chaban.web.server.UsersWebServer;
import ru.chaban.web.server.UsersWebServerSimple;
import ru.chaban.web.services.TemplateProcessor;
import ru.chaban.web.services.TemplateProcessorImpl;

import java.io.IOException;

public class StartServer {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public StartServer() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        UsersWebServer usersWebServer = new UsersWebServerSimple(WEB_SERVER_PORT, null, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
