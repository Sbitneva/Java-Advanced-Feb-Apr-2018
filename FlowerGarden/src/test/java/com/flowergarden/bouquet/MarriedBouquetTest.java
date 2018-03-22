package com.flowergarden.bouquet;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarriedBouquetTest {

    @InjectMocks
    private MarriedBouquet marriedBouquet;

    @Mock
    private Chamomile chamomile;

    @Mock
    private Rose rose;

    @Mock
    private Tulip tulip;

    @Before
    public void setUp() {

        marriedBouquet = new MarriedBouquet();
        marriedBouquet.addFlower(chamomile);
        marriedBouquet.addFlower(rose);
        marriedBouquet.addFlower(tulip);
    }

    @Test
    public void chamomileShouldBeFoundedByLength() {

        when(chamomile.getLength()).thenReturn(20);
        when(rose.getLength()).thenReturn(150);
        when(tulip.getLength()).thenReturn(99);

        Collection<GeneralFlower> generalFlowerList = marriedBouquet.searchFlowersByLength(15, 40);
        assertEquals(generalFlowerList.size(), 1);
        assertTrue(generalFlowerList.contains(chamomile));

    }

    @Test
    public void chamomileAndTulipShouldBeFoundedByLength() {

        when(chamomile.getLength()).thenReturn(20);
        when(rose.getLength()).thenReturn(150);
        when(tulip.getLength()).thenReturn(99);

        Collection<GeneralFlower> generalFlowerList = marriedBouquet.searchFlowersByLength(15, 99);
        assertEquals(generalFlowerList.size(), 2);
        assertTrue(generalFlowerList.contains(chamomile));
        assertTrue(generalFlowerList.contains(tulip));
    }

    @Test
    public void bouquetPriceShouldBe720() {

        when(chamomile.getPrice()).thenReturn(100f);
        when(rose.getPrice()).thenReturn(200f);
        when(tulip.getPrice()).thenReturn(300f);

        float price = 0;
        for (GeneralFlower flower : marriedBouquet.getFlowers()) {
            price += flower.getPrice();
        }
        price += marriedBouquet.getAssemblePrice();

        assertTrue(marriedBouquet.getPrice() == price);
    }

    @Test
    public void bouquetPriceShouldBe() {

        when(chamomile.getPrice()).thenReturn(100f);
        when(rose.getPrice()).thenReturn(200f);
        when(tulip.getPrice()).thenReturn(-100f);

        float price = 0;
        for (GeneralFlower flower : marriedBouquet.getFlowers()) {
            price += flower.getPrice();
        }
        price += marriedBouquet.getAssemblePrice();

        assertTrue(marriedBouquet.getPrice() == price);
    }
}
