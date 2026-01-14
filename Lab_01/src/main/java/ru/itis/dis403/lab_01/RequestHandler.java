package ru.itis.dis403.lab_01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    final static Logger logger = LogManager.getLogger(RequestHandler.class);

    public void handle(Socket clientSocket) {
        try {
            // Поток для чтения данных от клиента
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            // Читаем пакет от клиента
            String lineOne = reader.readLine();
            System.out.println(lineOne);
            logger.debug(lineOne);
            String[] components = lineOne.split(" ");

            //GET /resource HTTP/1.1
            String method = components[0];
            String resource = components[1];

            String path = resource;
            Map<String, String> params = new HashMap<>();

            // http://localhost:8080/resource/part?name=tat&region=16
            // URI /resource/part
            int queryIndex = resource.indexOf('?');
            if (queryIndex != -1) {
              path = resource.substring(0, queryIndex);
              String query = resource.substring(queryIndex + 1);
              String[] queryParams = query.split("&");
              for (String queryParam : queryParams) {
                  String[] keyValue = queryParam.split("=", 2);
                  String key = keyValue[0];
                  String value = keyValue.length > 1 ? keyValue[1] : "";
                  params.put(key, value);
              }
            }

            if (path.equals("/shutdown")) {
                logger.info("server stopped by client");
                clientSocket.close();
                return;
            }

            while (true) {
                // Читаем пакет от клиента
                String message = reader.readLine();
                System.out.println(message);
                logger.debug(message);

                if (message.isEmpty()) {
                    logger.debug("end of request header");
                    OutputStream os = clientSocket.getOutputStream();
                    logger.debug("outputStream" + os);
                    IResourceService resourceService = Application.resourceMap.get(path);
                    if (resourceService != null) {
                        resourceService.service(method, params, os);
                    } else {
                        new NotFoundService().service(method, params, os);
                    }
                    os.flush();
                    clientSocket.close();
                    break;
                }

            }
        } catch (IOException e) {
            logger.error("Error handling client request", e);
        }

    }

}