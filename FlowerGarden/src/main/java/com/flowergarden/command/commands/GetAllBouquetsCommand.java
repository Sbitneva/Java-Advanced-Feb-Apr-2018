package com.flowergarden.command.commands;

import com.flowergarden.ApplicationContextWrapper;
import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.command.Command;
import com.flowergarden.service.GetAllBouquetsService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class GetAllBouquetsCommand implements Command {

    private static Logger log = Logger.getLogger(GetAllBouquetsCommand.class.getName());

    private GetAllBouquetsService getAllBouquetsService =
            ApplicationContextWrapper.getContext().getBean(GetAllBouquetsService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("GetAllBouquetsCommand execution started");

        ArrayList<Bouquet> bouquets = getAllBouquetsService.getBouquets();
        request.setAttribute("bouquets", bouquets);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
