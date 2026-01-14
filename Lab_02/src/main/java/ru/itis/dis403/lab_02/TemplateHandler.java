package ru.itis.dis403.lab_02;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TemplateHandler {

    // 1. Найти файл по имени templateName
    // 2. Прочитать файл в строку
    // 3. Найти в файле ${param_name} и заменить на значения параметра
    // 4. Передать строку во writer

    public void handle(String templateName, Map<String, String> params, Writer writer) throws IOException {

        InputStream is = getClass().getClassLoader().getResourceAsStream("templates/" + templateName);

        if (is == null) {
            throw new FileNotFoundException("Template not found: " + templateName);
        }

        String template = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String placeholder = "${" + entry.getKey() + "}";
            String value = entry.getValue();
            template = template.replace(placeholder, value);
        }

        writer.write(template);
    }
}
