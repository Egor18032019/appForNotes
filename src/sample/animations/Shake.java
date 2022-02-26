package sample.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;

    public Shake(Node node) {
        tt = new TranslateTransition(Duration.millis(77), node);
        //отступ
        tt.setFromX(10f);
        //насколько передвиниться
        tt.setByX(10f);
        //кол-во движений
        tt.setCycleCount(3);
        //авто возврат
        tt.setAutoReverse(true);
    }

    public void playAnim(){
        tt.playFromStart();
    }
}
