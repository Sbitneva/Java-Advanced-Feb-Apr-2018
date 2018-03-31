package com.flowergarden.command.commands;

import com.flowergarden.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAllBouquetsCommand implements Command {
    /**
     * Execute a command.
     *
     * @param request  HTTP request
     * @param response HTTP response
     * @throws ServletException Servlet exceptions
     * @throws IOException      IO errors
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
