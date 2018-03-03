/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.animation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author Sandman
 */
@SuppressWarnings("deprecation")
public class FadeUp {
    private static final Interpolator sandman = Interpolator.SPLINE(0.25, 0.1, 0.25, 1);
    private static Timeline timeline;
    private static int LENGTH = 30;
    private static int FEEDBACK = 6;
    
    public FadeUp(final Node myNode) {
        this(myNode,LENGTH,FEEDBACK);
    }
    
    public FadeUp(final Node myNode, int length) {
        this(myNode,LENGTH,FEEDBACK);
    }
    
    public FadeUp(final Node myNode, int length, int feedBack) {
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.millis(0),    
                new KeyValue(myNode.opacityProperty(), 0, sandman),
                new KeyValue(myNode.translateYProperty(), length, sandman)
            ),
            new KeyFrame(Duration.millis(600),    
                new KeyValue(myNode.opacityProperty(), 1, sandman),
                new KeyValue(myNode.translateYProperty(), 0, sandman)
            ),
            new KeyFrame(Duration.millis(800),    
                new KeyValue(myNode.opacityProperty(), 1, sandman),
                new KeyValue(myNode.translateYProperty(), feedBack, sandman)
            )
        );
        timeline.play();
    }
}
