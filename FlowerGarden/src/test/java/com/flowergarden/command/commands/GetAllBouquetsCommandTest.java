package com.flowergarden.command.commands;

import com.flowergarden.TestConfiguration;
import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import com.flowergarden.dao.sqlite.SqliteFlowerDao;
import com.flowergarden.service.GetAllBouquetsService;
import com.flowergarden.service.GetBouquetFlowersService;
import com.flowergarden.servlet.ServletDispatcher;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class GetAllBouquetsCommandTest {

    private static Logger log = Logger.getLogger(GetAllBouquetsCommandTest.class.getName());

    @InjectMocks
    GetAllBouquetsCommand getAllBouquetsCommand = new GetAllBouquetsCommand();

    private HttpServletRequest request = mock(HttpServletRequest.class, CALLS_REAL_METHODS);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqliteBouquetDao sqliteBouquetDao;

    @Autowired
    GetAllBouquetsService getAllBouquetsService;

    @Before
    public void setUp() {
        initMocks(this);
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("restore from flowergarden.test.db");
        } catch (SQLException e) {
            log.error(e.getClass() + " : " + e.getMessage());
        }
        when(request.getParameter("command")).thenReturn("bouquets");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void executeWithCorrectParameterTest(){
        try {
            ServletDispatcher dispatcher = new ServletDispatcher();
            dispatcher.init();
            dispatcher.processRequest(request, response);
            verify(request, times(1)).setAttribute("bouquets", any());
            dispatcher.destroy();
        }catch(Exception e){
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

}
