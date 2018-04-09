package com.flowergarden.service;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.BouquetBuilder;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.sqlite.SqliteBouquetDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class GetAllBouquetsServiceTest {

    @Mock
    private SqliteBouquetDao mockBouquetDao;

    @InjectMocks
    private GetAllBouquetsService getAllBouquetsService;

    @Test
    public void getBouquetsTest(){
        ArrayList<Bouquet> bouquets = new ArrayList<>();
        bouquets.add(new BouquetBuilder().setName("married").setAssemblePrice(12.0f).getBouquet());
        bouquets.add(new BouquetBuilder().setName("fashion").setAssemblePrice(12.0f).getBouquet());
        bouquets.add(new BouquetBuilder().setName("romantic").setAssemblePrice(12.0f).getBouquet());

        when(mockBouquetDao.getAllBouquets()).thenReturn(bouquets);

        ArrayList<Bouquet> expectedBouquets = getAllBouquetsService.getBouquets();

        assertEquals(getAllBouquetsService.getBouquets().size(), bouquets.size());
    }
}
