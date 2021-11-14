package shared;

import lombok.Getter;

@Getter
public class Price {

    private String label;
    private double open;
    private double high;
    private double low;
    private double close;


    public Price(String label, double open, double high, double low, double close) {
        this.label = label;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }
}
