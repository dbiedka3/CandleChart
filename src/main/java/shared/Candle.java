package shared;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import lombok.Getter;

@Getter
public class Candle {


    private Price price;
    private Rectangle body;
    private Line botch;

    public Candle(Price price, Rectangle body, Line botch) {
        this.price = price;
        this.body = body;
        this.botch = botch;
    }
}
