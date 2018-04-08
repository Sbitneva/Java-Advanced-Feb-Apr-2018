package com.flowergarden.service;

import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import com.flowergarden.dao.sqlite.SqliteFlowerDao;
import com.flowergarden.flowers.FlowersBuilder;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.exceptions.FlowerException;
import com.flowergarden.properties.FreshnessInteger;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetBouquetFlowersServiceTest {

    private static Logger log = Logger.getLogger(GetBouquetFlowersServiceTest.class.getName());

    @Mock
    private SqliteFlowerDao mockFlowerDao;

    @InjectMocks
    private GetBouquetFlowersService getBouquetFlowersService;

    @Test
    public void getFlowersTest(){
        ArrayList<GeneralFlower> flowers = new ArrayList<>();
        try {
            flowers.add(new FlowersBuilder().setName("chamomile").setLength(15).setPetals(6).buildFlower());
            flowers.add(new FlowersBuilder().setName("rose").setLength(15).buildFlower());
            flowers.add(new FlowersBuilder().setName("tulip").setLength(15).buildFlower());
        } catch (FlowerException e) {
            log.error(e.getMessage() + " : " + e.getClass());
        }

        when(mockFlowerDao.getBouquetFlowers(anyInt())).thenReturn(flowers);

        assertEquals(flowers.size(), getBouquetFlowersService.getFlowers(1).size());
    }

}
