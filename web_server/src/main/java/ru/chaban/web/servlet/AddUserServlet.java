package ru.chaban.web.servlet;



import ru.chaban.core.dao.UserDao;
import ru.chaban.web.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AddUserServlet extends HttpServlet {

    private static final String USERS_PAGE_TEMPLATE = "add_user.html";
    private static final String TEMPLATE_ATTR_RANDOM_USER = "randomUser";

    private final TemplateProcessor templateProcessor;

    public AddUserServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
//        userDao.findRandomUser().ifPresent(randomUser -> paramsMap.put(TEMPLATE_ATTR_RANDOM_USER, randomUser));

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }

}
