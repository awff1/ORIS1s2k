package ru.itis.dis403.lab_07.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab_07.repositories.*;

@WebListener
public class DBContextListener implements ServletContextListener {

    final static Logger logger = LogManager.getLogger(DBContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("contextInitialized");
        try {
            DBConnection.init();

            CityRepository cityRepository = new CityRepository();
            StreetRepository streetRepository = new StreetRepository();

            ServletContext servletContext = sce.getServletContext();

            servletContext.setAttribute("cityRepository", cityRepository);
            servletContext.setAttribute("streetRepository", streetRepository);

        } catch (ClassNotFoundException e) {
            logger.atError().withThrowable(e).log();
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("contextDestroyed");
        DBConnection.destroy();
    }
}
