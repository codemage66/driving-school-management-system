/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.main;


import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Sandman
 */
public class Main extends Application {
    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/StartUp.fxml"));
        
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png")));
        
        Parent loginRoot=FXMLLoader.load(getClass().getResource("/edu/ijse/gdse41/drivingschool/view/Login.fxml"));
        Stage logStage= new Stage();
        logStage.initStyle(StageStyle.UNDECORATED);
        logStage.setResizable(false);
        logStage.setScene(new Scene(loginRoot,1200,700));
        this.mainStage=logStage;
        
        logStage.getIcons().add(new Image(getClass().getResourceAsStream("/edu/ijse/gdse41/drivingschool/image/iconDrivingProject.png")));
        
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
        

        PauseTransition delayLog = new PauseTransition(Duration.seconds(2));
        delayLog.setOnFinished( event -> logStage.show() );
        delayLog.play();
        
        ///Getting Simple Date format
        String date = new SimpleDateFormat("YYMMdd").format(new Date());
        System.out.println(date);
//        stage.getIcons().add(new Image(getClass().getResourceAsStream("bal.png")));
           }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
