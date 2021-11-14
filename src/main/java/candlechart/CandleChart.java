package candlechart;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import shared.Candle;
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
    private double chartPlotStartingPointX;
    private double chartPlotStartingPointY;

    private double candleWidth;
    private double candleGapWidth;

    ArrayList<Candle> candles = new ArrayList<>();

    public CandleChart(double width, double height, ArrayList<Price> prices) {
        this.prices = prices;
        balanceChart(width, height, this.prices.size());
        createCandles();

    }

    private void balanceChart(double width, double height, int dataSetSize) {
        calculateChartDimensions(width, height);
        calculateCandleWidthAndGap(dataSetSize);
    }

    private void createCandles() {
        for (int i = 0; i < this.prices.size(); i++) {
            this.candles.add(createOneCandle(this.prices.get(i), i));
        }
    }

    public Candle createOneCandle(Price price, int index) {

        //find x-coord (midst)
        double xMid = findCoordinateX(index);

        //find y-coord high and low
        double yHigh = findCoordinateY(price.getHigh());
        double yLow = findCoordinateY(price.getLow());

        //create botch
        Line botch = new Line(xMid, yLow, xMid, yHigh);

        //find y-coord open and close
        double yOpen = findCoordinateY(price.getOpen());
        double yClose = findCoordinateY(price.getClose());
        double bodyHeight = Math.abs(yClose - yOpen);
        double xBodyStart = xMid - 1 - Math.floor(this.candleWidth / 2);
        double yBodyStart = yOpen;
        if (yClose < yOpen) yBodyStart = yClose;

        //create body
        Rectangle body = new Rectangle(xBodyStart, yBodyStart, this.candleWidth, bodyHeight);

        //return candle
        return new Candle(price, body, botch);
    }

    private double findCoordinateY(double value) {
        return this.chartPlotStartingPointY - findCoordinateShiftY(value);
    }

    private double findCoordinateShiftY(double value) {
        double max = findMax();
        double min = findMin();
        double distance = max - min;
        double valDistance = value - min;

        return (valDistance / distance) * this.chartHeight;

    }


    private double findCoordinateX(int index) {
        double zeroPoint = this.chartPlotStartingPointX + Math.floor(this.candleWidth / 2) + 1;

        if (index == 0) {
            return zeroPoint;
        } else {

            return zeroPoint + 2 * this.candleWidth * index;
        }

    }

    private void calculateChartDimensions(double width, double height) {
        //calculate available width and height
        //set chart plot starting points (y-reverted)
        if (width * FIXED_CHART_MARGIN_PERCENT < MIN_CHART_MARGIN_PIX) {
            this.chartWidth = width - 2 * MIN_CHART_MARGIN_PIX;
            this.chartPlotStartingPointX = MIN_CHART_MARGIN_PIX;
        } else {
            this.chartWidth = width - 2 * FIXED_CHART_MARGIN_PERCENT * width;
            this.chartPlotStartingPointX = FIXED_CHART_MARGIN_PERCENT * width;
        }

        if (height * FIXED_CHART_MARGIN_PERCENT < MIN_CHART_MARGIN_PIX) {
            this.chartHeight = height - 2 * MIN_CHART_MARGIN_PIX;
            this.chartPlotStartingPointY = MIN_CHART_MARGIN_PIX + this.chartHeight;
        } else {
            this.chartHeight = height - 2 * FIXED_CHART_MARGIN_PERCENT * height;
            this.chartPlotStartingPointY = FIXED_CHART_MARGIN_PERCENT * height + this.chartHeight;

        }

    }

    private void calculateCandleWidthAndGap(int dataSetSize) {

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

    private double findMin() {
        double min = this.prices.get(0).getLow();
        for (Price p : this.prices) {
            if (p.getLow() < min) min = p.getLow();

        }
        return min;
    }

    private double findMax() {
        double max = this.prices.get(0).getHigh();
        for (Price p : this.prices) {
            if (p.getHigh() > max) max = p.getHigh();

        }
        return max;

    }
}
