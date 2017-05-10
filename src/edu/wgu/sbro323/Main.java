/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wgu.sbro323;

import java.io.IOException;
import java.util.ArrayList;
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

//    private Stage primaryStage;
//    private Parent rootLayout;

    private final Inventory inventory = new Inventory();

//    public Main(){
//  
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            inventory.update(new Inhouse("screw", 13.99, 5, 2, 7, 4557321));
            inventory.update(new Inhouse("bolt", 14.99, 8, 3, 12, 007));
            inventory.update(new Inhouse("washer", 13.99, 3, 3, 7, 77779));
            inventory.update(new Outsourced("outsourced", 11.99, 5, 3, 7, "Goog"));

            ArrayList<Part> plist = new ArrayList<>();
            plist.add(new Inhouse("Joka", 13.99, 5, 3, 7, 457321));
            inventory.addProduct(new Product("Prodi", 99.85, 2, 1, 7, plist));
            inventory.addProduct(new Product("Garni", 99.85, 3, 1, 4, plist));
        } catch (IllegalArgumentException | InvalidInventoryException e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        
//        Stage stage = createStage(title, url);
//        T controller = getLoader().getController();
//
//        controller.setData(title, inventory);
//        stage.show();
        
        
        
        primaryStage.setTitle("Inventory Management System");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryManagement.fxml"));
//            rootLayout = loader.load();
            Scene scene = new Scene(loader.load());
            InventoryManagementController controller = loader.getController();
            controller.setData("Inventory Management System", inventory);

            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // FXML file not loaded
            e.printStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
