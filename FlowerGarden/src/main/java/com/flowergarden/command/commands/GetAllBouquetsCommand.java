package com.flowergarden.command.commands;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.command.Command;
import com.flowergarden.config.ApplicationConfig;
import com.flowergarden.config.ApplicationContextSingleton;
import com.flowergarden.service.GetAllBouquetsService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class GetAllBouquetsCommand implements Command {

    private static Logger log = Logger.getLogger(GetAllBouquetsCommand.class.getName());


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GetAllBouquetsService getAllBouquetsService = ApplicationContextSingleton.getContext().getBean(GetAllBouquetsService.class);

        log.debug("GetAllBouquetsCommand execution started");

        ArrayList<Bouquet> bouquets = getAllBouquetsService.getBouquets();
        request.setAttribute("bouquets", bouquets);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
