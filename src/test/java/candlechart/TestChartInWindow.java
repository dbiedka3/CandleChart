package candlechart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import shared.Candle;
import shared.Price;

import java.util.ArrayList;


public class TestChartInWindow extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        ArrayList<Price> prices= new TestCandlechartDimensions().generatePrices(5);
        CandleChart cc=new CandleChart(1200,800,prices);

        AnchorPane root=new AnchorPane();

        for(Candle c: cc.getCandles()){
            root.getChildren().add(c.getBody());
            root.getChildren().add(c.getBotch());

        }



        Scene scene = new Scene(root,1200,800);
        stage.setScene(scene);
        stage.show();

    }
}
