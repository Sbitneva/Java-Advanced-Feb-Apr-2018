package com.flowergarden.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    /**
     * Execute a command.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException Servlet exceptions
     * @throws IOException IO errors
     */
    void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

}
