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
    
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();

    public Main(){
        
        partInventory.add(new Inhouse("fish", 111, 13.99, 5,3,7,004));
        partInventory.add(new Inhouse("chair", 112, 13.99, 5, 3, 7, 004));
        partInventory.add(new Inhouse("head", 113, 13.99, 5, 3, 7, 004));

    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       
        
//        Parent root = FXMLLoader.load(getClass().getResource("InventoryManagement.fxml"));
//        Scene scene = new Scene(root); //Scene(root, 950, 700);
//        primaryStage.setTitle("Inventory Management System");
//        primaryStage.setScene(scene);
//        primaryStage.show();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Inventory Management System");

        try {
            // Load the root layout from the fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryManagement.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            InventoryManagementController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }


    }
    
    
    public ObservableList<Part> getPartInventory() {
        return partInventory;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
