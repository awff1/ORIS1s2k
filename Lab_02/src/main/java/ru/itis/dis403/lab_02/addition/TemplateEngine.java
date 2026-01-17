package ru.itis.dis403.lab_02.addition;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab_02.TemplateHandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@WebServlet("*.html")
public class TemplateEngine extends HttpServlet {

    final static Logger logger = LogManager.getLogger(TemplateEngine.class);
    private final TemplateHandler handler = new TemplateHandler();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug(request.getServletPath());

        Map<String, String> params = new HashMap<>();
        Enumeration<String> enumeration = request.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            String attributeName = enumeration.nextElement();
            String attributeValue = request.getAttribute(attributeName).toString();
            logger.debug(attributeName + " : " + attributeValue);
            params.put(attributeName, attributeValue);
        }

        String fileName = request.getServletPath().substring(1);

        handler.handle(fileName, params, response.getWriter());

//        URL url = TemplateEngine.class.getClassLoader().getResource("templates/" + fileName);
//
//        if (url == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Template " + fileName + " не найден");
//            return;
//        }
//
//        Path path;
//
//        try {
//            path = Paths.get(url.toURI());
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//
//        String template = Files.readString(path);
//
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            template = template.replace("${" + entry.getKey() + "}", entry.getValue());
//        }
//
//        response.setContentType("text/html; charset=UTF-8");
//        response.getWriter().write(template);
    }
}