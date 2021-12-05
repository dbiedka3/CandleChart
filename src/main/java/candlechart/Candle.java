package candlechart;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import shared.Price;

@Getter
public class Candle {


    private Color lineColor = Color.BLACK;
    private Color upColor = Color.GREEN;
    private Color dnColor = Color.RED;

    private enum type {UP, DN;}


    private Price price;
    private Rectangle body;
    private Line botch;
    private Candle.type candleType;

    public Candle(Price price, Rectangle body, Line botch, CandleColorSet ccs) {
        this.price = price;
        this.body = body;
        this.botch = botch;

        if (ccs != null) {
            this.lineColor = ccs.getLineColor();
            this.upColor = ccs.getBodyColorUp();
            this.dnColor = ccs.getBodyColorDn();

        }

        setCandleType();
        setCandleColors();
    }

    private void setCandleType() {
        if (this.price.getOpen() < this.price.getClose()) {
            this.candleType = Candle.type.UP;
        } else {
            this.candleType = Candle.type.DN;
        }

    }

    private void setCandleColors() {
        this.body.setStroke(this.lineColor);

        if (this.candleType == type.UP) {
            this.body.setFill(this.upColor);
        } else {
            this.body.setFill(this.dnColor);
        }

        this.botch.setStroke(this.lineColor);

    }

    private void setCustomColors() {


    }
}
