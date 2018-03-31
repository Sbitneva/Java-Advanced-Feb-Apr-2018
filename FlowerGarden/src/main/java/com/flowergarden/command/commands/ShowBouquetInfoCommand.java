package com.flowergarden.command.commands;

import com.flowergarden.command.Command;
import com.flowergarden.command.CommandsFactory;
import com.flowergarden.service.GetBouquetInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowBouquetInfoCommand implements Command {

    private static Logger log = Logger.getLogger(ShowBouquetInfoCommand.class.getName());

    @Autowired
    GetBouquetInfoService getBouquetInfoService;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
