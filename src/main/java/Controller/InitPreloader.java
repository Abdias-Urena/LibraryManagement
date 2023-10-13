package Controller;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class InitPreloader implements Initializable {

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;
    
    @FXML
    private ProgressBar progressBar;
    
    public static ProgressBar statprogressBar;
    public Label lblLoading;
    public static Label lblLoadingg;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        lblLoadingg = lblLoading;
        statprogressBar = progressBar;
        
 
        Rotate(circle1, true, 360, 10);
        Rotate(circle2, true, 180, 18);
    }
    
    private void Rotate(Circle c, boolean reverse, int angle, int duration) {

        RotateTransition rt = new RotateTransition(Duration.seconds(duration), c);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(10);
        rt.setCycleCount(18);
        rt.play();

    }

}
