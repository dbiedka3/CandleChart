package candlechart;

import org.junit.jupiter.api.Test;
import shared.Price;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestCandlechart {


    @Test
    public void testIfChartDimensionCalculationIsCorrectForLowValues() {
        //given
        ArrayList<Price> testList = generatePrices(10);

        //when
        CandleChart candleChart = new CandleChart(200, 100, testList);

        //then
        double w = candleChart.getChartWidth();
        double h = candleChart.getChartHeight();

        //w=200-2*(200*0.05)=180 -> w=200-2*35 (fixed)=130
        assertEquals(130, w);
        //w=100-2*(100*0.05)=90 -> w=100-2*35 (fixed)=30
        assertEquals(30, h);
    }


    @Test
    public void testIfChartDimensionCalculationIsCorrectForHighValues() {
        //given
        ArrayList<Price> testList = generatePrices(10);

        //when
        CandleChart candleChart = new CandleChart(1000, 700, testList);

        //then
        double w = candleChart.getChartWidth();
        double h = candleChart.getChartHeight();

        //w=1000-2*(1000*0.05)=900
        assertEquals(900, w);
        //w=700-2*(700*0.05)=630
        assertEquals(630, h);
    }
    @Test
    public void testIfCandleDimensionCalculatesCorrectlyForMinChartWidth() {
        //given
        ArrayList<Price> testList = generatePrices(10);

        //when
        CandleChart candleChart = new CandleChart(100, 100, testList);
            //expected chart width = 30 -> 5 candles

        //then
        double cWidth = candleChart.getCandleWidth();
        double cGap = candleChart.getCandleGapWidth();
        assertEquals(3, cWidth);
        assertEquals(3, cGap);
        assertEquals(5,candleChart.getPrices().size());
    }

    @Test
    public void testIfCandleDimensionCalculatesCorrectlyForHigherChartWidth() {
        //given
        ArrayList<Price> testList = generatePrices(10);

        //when
        CandleChart candleChart = new CandleChart(1000, 100, testList);
        //expected chart width = 30 -> 5 candles

        //then
        double cWidth = candleChart.getCandleWidth();
        double cGap = candleChart.getCandleGapWidth();
        assertEquals(47, cWidth);
        assertEquals(47, cGap);
        assertEquals(10,candleChart.getPrices().size());
    }


    public ArrayList<Price> generatePrices(int size) {
        ArrayList<Price> generatedPrices = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            double d = Math.random();
            Price temp = new Price("rand", d, d, d, d);
            generatedPrices.add(temp);
        }
        return generatedPrices;
    }

}
