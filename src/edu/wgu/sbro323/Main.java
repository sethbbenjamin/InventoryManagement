/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wgu.sbro323;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author elitebook
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       
        
        Parent root = FXMLLoader.load(getClass().getResource("InventoryManagement.fxml"));
        
        Scene scene = new Scene(root, 800, 450);
        
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
