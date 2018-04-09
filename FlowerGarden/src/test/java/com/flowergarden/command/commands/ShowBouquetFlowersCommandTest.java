package com.flowergarden.command.commands;

import com.flowergarden.TestConfiguration;
import com.flowergarden.dao.sqlite.SqliteFlowerDao;
import com.flowergarden.flowers.FlowersBuilder;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.exceptions.FlowerException;
import com.flowergarden.service.GetBouquetFlowersService;
import com.flowergarden.servlet.ServletDispatcher;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.GenericFilter;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ShowBouquetFlowersCommandTest {

    private static Logger log = Logger.getLogger(ShowBouquetFlowersCommandTest.class.getName());

    @InjectMocks
    private ShowBouquetFlowersCommand showBouquetFlowersCommand;

    private HttpServletRequest request = mock(HttpServletRequest.class, CALLS_REAL_METHODS);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

    @Autowired
    private DataSource dataSource;

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
        when(request.getParameter("command")).thenReturn("bouquet_info");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @After
    public void tearDown() {
        log.debug("after test");
    }


    @Test
    public void executeWithCorrectParametersTest(){
        String id = "1";
        try {
            ServletDispatcher dispatcher = new ServletDispatcher();
            dispatcher.init();
            when(request.getParameter("id")).thenReturn(id);
            dispatcher.processRequest(request, response);
            verify(request,  times(1)).setAttribute("flowers", any());
            dispatcher.destroy();
        }catch(Exception e){
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }

    @Test
    public void executeTestWithWrongId(){
        String id = "kj";
        try {
            ServletDispatcher dispatcher = new ServletDispatcher();
            dispatcher.init();
            when(request.getParameter("id")).thenReturn(null);
            dispatcher.processRequest(request, response);
            verify(request,  times(1)).setAttribute("error", anyString());
            verify(request,  times(0)).setAttribute("flowers", any());
            dispatcher.destroy();
        }catch(Exception e){
            log.error(e.getClass() + " : " + e.getMessage());
        }
    }
}
