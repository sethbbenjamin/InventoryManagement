package edu.wgu.sbro323;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author elitebook
 */
public class InventoryManagementController implements Initializable {
    
    //Reference to main app
    private Main main;
    
    
    private final ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private final ObservableList<Product> productInventory = FXCollections.observableArrayList();
    
    private FXMLLoader loader;

    
    
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
    private Button btnDeletePart;
    
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

        String title = "Add Part";

        Stage stage = createAddPartStage(title);

        AddPartController addPartController = loader.getController();
        addPartController.setRoot(addPartRoot);
        addPartController.setTitle(title);

        //place at end so application doesn't "wait" before it should
        stage.showAndWait();
              
        if (addPartController.getPart() != null)
        {
            partInventory.add(addPartController.getPart());
        }

    }
    
    @FXML
    private void modifyPartButtonAction(ActionEvent event) throws IOException{
        
        String title = "Modify Part";
        
        Stage stage = createAddPartStage(title);
        Part part = partsTable.getSelectionModel().getSelectedItem();
        
        AddPartController addPartController = loader.getController();
        addPartController.setPart(part);
        addPartController.setRoot(addPartRoot);
        addPartController.setTitle(title);
        
        
        //place at end so application doesn't "wait" before it should
        stage.showAndWait();
        
        if(addPartController.isChanged()){
            int i = partInventory.indexOf(part);
            partInventory.set(i, addPartController.getPart());
        }
        
    }
      
    private Stage createAddPartStage(String title)throws IOException{
        Stage stage = new Stage();
        loader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
        addPartRoot = loader.load();
        stage.setScene(new Scene(addPartRoot));
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(inventoryManagementRoot.getScene().getWindow());
        
        return stage;
    }
    
    @FXML
    private void deletePartButtonAction(){
        
        
        Part part = partsTable.getSelectionModel().getSelectedItem();

        if(part != null){
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Delete Part?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                partInventory.remove(part);
            }
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        //addPartRoot.setCon
        
        //test data
        partInventory.add(new Inhouse("fish", 13.99, 5,3,7,004));
        partInventory.add(new Inhouse("chair", 13.99, 5, 3, 7, 004));
        partInventory.add(new Inhouse("head", 13.99, 5, 3, 7, 004));
        partInventory.add(new Outsourced("head", 13.99, 5, 3, 7, "Goog"));

        
        //Populate / bind table column data
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        partIDColumn.setCellValueFactory(cellData -> cellData.getValue().PartIDProperty().asObject());
        instockColumn.setCellValueFactory(cellData -> cellData.getValue().instockProperty().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        
        //Format table cell to display currency values
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

        priceColumn.setCellFactory(column -> {
            return new TableCell<Part, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Format as currency
                        setText(currencyFormatter.format(item));
                    }
                }
            };
        });
     
    } 
    
    public void setRoot(Main main) {
        this.main = main;

        // Add data to the table
        partsTable.setItems(partInventory);
    }
    
}
