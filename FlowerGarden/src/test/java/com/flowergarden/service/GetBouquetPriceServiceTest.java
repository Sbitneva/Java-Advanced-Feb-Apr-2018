package com.flowergarden.service;

import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.FlowerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetBouquetPriceServiceTest {

    @Mock
    private FlowerDao mockFlowerDao;

    @Mock
    private BouquetDao mockBouquetDao;

    @InjectMocks
    private GetBouquetPriceService getBouquetPriceService;

    @Test
    public void initData() {
        float expectedPrice = 0;
        ArrayList<Float> prices = new ArrayList<>();

        float assemblePrice = 1.5f;

        for (float i = 5.3f; i < 10.0f; i++) {
            prices.add(i);
            expectedPrice += i;
        }
        expectedPrice += assemblePrice;

        when(mockFlowerDao.getFlowersPricesForBouquet(anyInt())).thenReturn(prices);
        when(mockBouquetDao.getAssemblePrice(anyInt())).thenReturn(assemblePrice);

        float realPrice = getBouquetPriceService.getPrice(1);

        assertTrue(expectedPrice == realPrice);
    }
}
