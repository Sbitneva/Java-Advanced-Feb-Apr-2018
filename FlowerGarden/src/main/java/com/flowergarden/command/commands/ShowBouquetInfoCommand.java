package com.flowergarden.command.commands;

import com.flowergarden.command.Command;
import com.flowergarden.command.CommandsFactory;
import com.flowergarden.config.ApplicationConfig;
import com.flowergarden.config.ApplicationContextSingleton;
import com.flowergarden.service.GetAllBouquetsService;
import com.flowergarden.service.GetBouquetInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowBouquetInfoCommand implements Command {

    private static Logger log = Logger.getLogger(ShowBouquetInfoCommand.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GetBouquetInfoService getAllBouquetsService = ApplicationContextSingleton.getContext().getBean(GetBouquetInfoService.class);
        log.debug("ShowBouquetInfoCommand execution started");
    }
}
