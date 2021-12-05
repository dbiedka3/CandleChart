package candlechart;

import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public class CandleColorSet {

    private Color lineColor;
    private Color bodyColorUp;
    private Color bodyColorDn;

    public CandleColorSet(Color lineColor, Color bodyColorUp, Color bodyColorDn) {
        this.lineColor = lineColor;
        this.bodyColorUp = bodyColorUp;
        this.bodyColorDn = bodyColorDn;
    }


}
