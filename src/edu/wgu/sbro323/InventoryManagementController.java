package edu.wgu.sbro323;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author elitebook
 */
public class InventoryManagementController implements Initializable {
    
    @FXML
    private Button btnExit;
    private Button btnAddPart;
    
    

    
    @FXML
    private void exitButtonAction(ActionEvent event){

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void addPartButtonAction(ActionEvent event) throws IOException{
        
        


        
        //System.out.println("AddPart Button.");
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Add Part");
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initOwner(btnAddPart.getScene().getWindow());
        stage.showAndWait();
        
        
    }
    

//    
//    private void addPartButtonAction(ActionEvent event){
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root1));
//            stage.show();
//        }catch(Exception e){
//            
//        }
//    }
//    


    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
