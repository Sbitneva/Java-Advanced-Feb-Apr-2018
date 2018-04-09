package com.flowergarden.servlet;

import com.flowergarden.ApplicationConfig;
import com.flowergarden.ApplicationContextWrapper;
import com.flowergarden.command.Command;
import com.flowergarden.command.CommandsFactory;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FlowerGarden")
public class ServletDispatcher extends HttpServlet {

    private static Logger log = Logger.getLogger(ServletDispatcher.class.getName());

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        log.debug("Get");
        log.debug(req.getRequestURI());
        processRequest(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        log.debug("Post");
        log.debug(req.getAttributeNames().toString());
        processRequest(req, resp);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
        log.debug("Servlet initialization");
        ApplicationContextWrapper.getContext();
    }


    /**
     * Requests processor.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException Servlet exceptions
     * @throws IOException IO exceptions
     */
    public void processRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("Servlet process request");
        CommandsFactory factoryCommand = CommandsFactory.getCommandsFactory();
        Command command = factoryCommand.getCommand(request);
        if (command != null) {
            command.execute(request, response);
        }
    }
}