package com.flowergarden.command.commands;

import com.flowergarden.command.Command;
import com.flowergarden.config.ApplicationContextSingleton;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.service.GetBouquetFlowersService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ShowBouquetFlowersCommand implements Command {

    private static Logger log = Logger.getLogger(ShowBouquetFlowersCommand.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("ShowBouquetFlowersCommand execution started");
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e){
            log.error("wrong id");
        }
        ArrayList<GeneralFlower> flowers = null;

        GetBouquetFlowersService getBouquetsFlowersService =
                ApplicationContextSingleton.getContext().getBean(GetBouquetFlowersService.class);

        if(id > 0) {
            flowers = getBouquetsFlowersService.getFlowers(id);
        }
        request.setAttribute("flowers", flowers);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
