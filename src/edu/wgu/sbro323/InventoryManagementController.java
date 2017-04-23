package edu.wgu.sbro323;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Product> productInventory = FXCollections.observableArrayList();
    
    
    @FXML
    private Parent inventoryManagementRoot;
    
    @FXML
    private Parent addPartRoot;
    
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
    private TableColumn<Part, Integer> instockColumn;
    @FXML
    private TableColumn<Part, Double>priceColumn;

    
    

    
    @FXML
    private void exitButtonAction(ActionEvent event){

        //final Node source = (Node) event.getSource();
        final Stage stage = (Stage) inventoryManagementRoot.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void addPartButtonAction(ActionEvent event) throws IOException{

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
        addPartRoot = loader.load();
        stage.setScene(new Scene(addPartRoot));
        stage.setTitle("Add Part");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(inventoryManagementRoot.getScene().getWindow());

        Part part = new Inhouse();
        partInventory.add(part);

        AddPartController addPartController = loader.getController();
        addPartController.setPart(part);
        addPartController.setRoot(addPartRoot);

        //place at end so application doesn't "wait" before it should
        stage.showAndWait();
     
    }
    
    @FXML
    private void modifyPartButtonAction(ActionEvent event) throws IOException{
        //System.out.println("modify part!");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
        addPartRoot = loader.load();
        stage.setScene(new Scene(addPartRoot));
        stage.setTitle("Modify Part");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(inventoryManagementRoot.getScene().getWindow());
        
        
        Part part = partsTable.getSelectionModel().getSelectedItem();

        AddPartController addPartController = loader.getController();
        addPartController.setPart(part);
        addPartController.setRoot(addPartRoot);
        
        //place at end so application doesn't "wait" before it should
        stage.showAndWait();
    }
      
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //test data
        partInventory.add(new Inhouse("fish", 111, 13.99, 5,3,7,004));
        partInventory.add(new Inhouse("chair", 112, 13.99, 5, 3, 7, 004));
        partInventory.add(new Inhouse("head", 113, 13.99, 5, 3, 7, 004));
        partInventory.add(new Outsourced("head", 113, 13.99, 5, 3, 7, "Goog"));
        
        
        
        //partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        //partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        //inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("instock"));
        //priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        partIDColumn.setCellValueFactory(cellData -> cellData.getValue().PartIDProperty().asObject());
        instockColumn.setCellValueFactory(cellData -> cellData.getValue().instockProperty().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        
    } 
    
    public void setRoot(Main main) {
        this.main = main;

        // Add data to the table
        partsTable.setItems(partInventory);
    }
    
}
