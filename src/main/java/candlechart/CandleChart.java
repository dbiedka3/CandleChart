package candlechart;

import lombok.Getter;
import shared.Price;

import java.util.ArrayList;

@Getter
public class CandleChart {

    public static double MIN_CHART_MARGIN_PIX = 35;
    public static double FIXED_CHART_MARGIN_PERCENT = 0.05;
    public static double MIN_CANDLE_WIDTH_PIX = 3;
    public static double MIN_CANDLE_GAP_PIX = 3;

    private ArrayList<Price> prices;

    private double chartWidth;
    private double chartHeight;

    private double candleWidth;
    private double candleGapWidth;


    public CandleChart(double width, double height, ArrayList<Price> prices) {
        this.prices = prices;
        balanceChart(width, height, this.prices.size());

    }

    private void balanceChart(double width, double height, int dataSetSize) {

        calculateChartDimensions(width, height);
        calculateCandleWidthAndGap(dataSetSize);


    }

    public void generateCandles() {

        double distance = findMax() - findMin();



    }


    public void calculateChartDimensions(double width, double height) {
        //calculate available width and height
        if (width * FIXED_CHART_MARGIN_PERCENT < MIN_CHART_MARGIN_PIX) {
            this.chartWidth = width - 2 * MIN_CHART_MARGIN_PIX;
        } else {
            this.chartWidth = width - 2 * FIXED_CHART_MARGIN_PERCENT * width;
        }

        if (height * FIXED_CHART_MARGIN_PERCENT < MIN_CHART_MARGIN_PIX) {
            this.chartHeight = height - 2 * MIN_CHART_MARGIN_PIX;
        } else {
            this.chartHeight = height - 2 * FIXED_CHART_MARGIN_PERCENT * height;

        }

    }

    public void calculateCandleWidthAndGap(int dataSetSize) {

        double slots = dataSetSize * 2 - 1; //n-candles + (n-1)-gaps
        int caclulatedSize = (int) (this.chartWidth / slots);

        //if calculatedSize lower than MIN (dataset is too large to display all) trim dataset by 1 and recursively repeat
        //if caluclatedSize geq MIN set both width and gap as odd numbers
        if (caclulatedSize < MIN_CANDLE_WIDTH_PIX) {
            this.prices.remove(0);
            calculateCandleWidthAndGap(this.prices.size());
        }
        if (caclulatedSize >= MIN_CANDLE_WIDTH_PIX) {
            if (caclulatedSize % 2 == 0) {
                caclulatedSize--;
            }
            this.candleWidth = caclulatedSize;
            this.candleGapWidth = caclulatedSize;

        }
    }

    public double findMin() {
        double min = this.prices.get(0).getLow();
        for (Price p : this.prices) {
            if (p.getLow() < min) min = p.getLow();

        }
        return min;
    }

    public double findMax() {
        double max = this.prices.get(0).getHigh();
        for (Price p : this.prices) {
            if (p.getHigh() > max) max = p.getHigh();

        }
        return max;

    }
}
