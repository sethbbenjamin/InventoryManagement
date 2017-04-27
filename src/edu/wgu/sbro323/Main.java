/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wgu.sbro323;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author elitebook
 */
public class Main extends Application {
    
    private Stage primaryStage;
    private Parent rootLayout;
    
    

    public Main(){
  
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Inventory Management System");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryManagement.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            //InventoryManagementController controller = loader.getController();
        } catch (IOException e) {
            // FXML file not loaded
            e.printStackTrace();
        }


    }
    
    
//    public ObservableList<Part> getPartInventory() {
//        return partInventory;
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
