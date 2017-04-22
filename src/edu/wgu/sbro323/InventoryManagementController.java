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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author elitebook
 */
public class InventoryManagementController implements Initializable {
    
    //Reference to main app
    private Main main;
    
    @FXML
    private BorderPane rootPane;
    
    @FXML
    private Button btnExit;
    @FXML
    private Button btnAddPart;
    @FXML
    private Button btnModifyPart;
    
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partIDColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> inventoryColumn;
    @FXML
    private TableColumn<Part, Double>priceColumn;

    
    

    
    @FXML
    private void exitButtonAction(ActionEvent event){

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void addPartButtonAction(ActionEvent event) throws IOException{

        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Add Part");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(rootPane.getScene().getWindow());
        stage.showAndWait();
        
        
    }
      
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("instock"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    } 
    
    public void setMain(Main main) {
        this.main = main;

        // Add data to the table
        partsTable.setItems(main.getPartInventory());
    }
    
}
