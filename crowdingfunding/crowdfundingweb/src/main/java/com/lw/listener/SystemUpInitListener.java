package com.lw.listener;

import com.lw.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author liwu
 * @create 2020-03-03 14:43
 */
public class SystemUpInitListener implements ServletContextListener {

    Logger log = LoggerFactory.getLogger(SystemUpInitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext application = servletContextEvent.getServletContext();
        String contextPath = application.getContextPath();

        application.setAttribute(Const.PATH,contextPath);

        log.debug("当前路径:{}",contextPath);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
