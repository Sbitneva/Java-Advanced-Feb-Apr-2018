package com.flowergarden.command;

import com.flowergarden.command.commands.GetAllBouquetsCommand;
import com.flowergarden.command.commands.ShowBouquetInfoCommand;
import com.flowergarden.config.ApplicationConfig;
import com.flowergarden.service.Services;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
public class CommandsFactory {

    private static Logger log = Logger.getLogger(CommandsFactory.class.getName());

    public static final String SERVLET_NAME = "/FlowerGarden";

    private static final String PARAM_NAME_COMMAND = "command";

    private static final String SHOW_BOUQUET_INFO = "bouquet_info";
    private static final String SHOW_BOUQUET = "bouquets";


    private Map<String, Command> commandMap = new HashMap<>();

    private static final CommandsFactory commandsFactory = new CommandsFactory();

    private CommandsFactory() {
        commandMap.put(SHOW_BOUQUET_INFO, new ShowBouquetInfoCommand());
        commandMap.put(SHOW_BOUQUET, new GetAllBouquetsCommand());
    }

    public static CommandsFactory getCommandsFactory(){
        return commandsFactory;
    }

    /**
     * Get command from request.
     *
     * @param request HTTP request
     * @return Command
     */
    public Command getCommand(final HttpServletRequest request) {
        String requestCommand = request.getParameter(PARAM_NAME_COMMAND);
        log.debug(request.getRequestURI());
        if (requestCommand == null) {
            return null;
        } else {
            log.debug(commandMap.get(requestCommand));
        }

        return commandMap.get(requestCommand);
    }
}
