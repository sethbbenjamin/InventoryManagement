package edu.wgu.sbro323;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author elitebook
 */
public class InventoryManagementController extends InventoryController implements Initializable {
    
    
    private final ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private final ObservableList<Product> productInventory = FXCollections.observableArrayList();
    
    private FXMLLoader loader;

    
    
    @FXML
    private Parent inventoryManagementRoot;
    
    @FXML
    private TextField txtSearchPart;
    @FXML
    private TextField txtSearchProduct;
    
    //part table
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partIDColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInstockColumn;
    @FXML
    private TableColumn<Part, Double>partPriceColumn;
    
    //product table
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> productIDColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInstockColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    
    @FXML
    private void exitButtonAction(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void addPartButtonAction(ActionEvent event){

        String title = "Add Part";
        URL url = getClass().getResource("AddPart.fxml");
        Stage stage = createStage(title, url);

        AddPartController addPartController = loader.getController();
        addPartController.setTitle(title);

        //place at end so application doesn't "wait" before it should
        stage.showAndWait();
              
        if (addPartController.getPart() != null)
        {
            partInventory.add(addPartController.getPart());
        }
    }
    
    @FXML
    private void modifyPartButtonAction(ActionEvent event){

        Part part = partsTable.getSelectionModel().getSelectedItem();
        String title = "Modify Part";
        URL url = getClass().getResource("AddPart.fxml");

        
                
        if(itemIsSelected(part)){
            Stage stage = createStage(title, url);
            
            AddPartController addPartController = loader.getController();
            addPartController.setPart(part);
            addPartController.setTitle(title);

            stage.showAndWait();

            if (addPartController.isChanged()) {
                int i = partInventory.indexOf(part);
                partInventory.set(i, addPartController.getPart());
            }
            
        }      
    }
    
    
    private <T> boolean itemIsSelected(T item) {
        
        if (item != null) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notice");
            alert.setHeaderText(null);
            alert.setContentText("No item selected!");
            alert.showAndWait();
        }
        
        return false;
    }
    
      
    private Stage createStage(String title, URL url){
        Stage stage = new Stage();
        loader = new FXMLLoader(url);
        
        try{
            stage.setScene(new Scene(loader.load()));
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(inventoryManagementRoot.getScene().getWindow());

        } catch(IOException e){
            e.printStackTrace();
        }
        
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
    
    
    @FXML
    private void clearPartButtonAction(ActionEvent event){
        txtSearchPart.clear();   
    }
    
    @FXML
    private void clearProdcutButtonAction(ActionEvent event) {
        txtSearchProduct.clear();
    }
    
    private void initializePartsTable(){
        
        //Populate and bind table column data
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        partIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partInstockColumn.setCellValueFactory(cellData -> cellData.getValue().instockProperty().asObject());
        partPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        //Format as currency
        formatTableColumnCurrency(partPriceColumn);
        
        //Bind table data to search field
        SortedList<Part> sortedData = filterTableData(partInventory, txtSearchPart, partsTable);
        partsTable.setItems(sortedData);
    }
    
    
    private void initializeProductsTable() {

        //Populate and bind table column data
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        productIDColumn.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        productInstockColumn.setCellValueFactory(cellData -> cellData.getValue().instockProperty().asObject());
        productPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        //Format price column as currency
        formatTableColumnCurrency(productPriceColumn);

        //Bind table data to search field
        SortedList<Product> sortedData = filterTableData(productInventory, txtSearchProduct, productsTable);
        productsTable.setItems(sortedData);
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //test data
        partInventory.add(new Inhouse("fish", 13.99, 5,3,7,4557321));
        partInventory.add(new Inhouse("chair", 14.99, 8, 3, 7, 007));
        partInventory.add(new Inhouse("head", 13.99, 3, 3, 7,77779));
        partInventory.add(new Outsourced("outsourced", 11.99, 5, 3, 7, "Goog"));
        
        ArrayList<Part> plist = new ArrayList<>();
        plist.add(new Inhouse("Joka", 13.99, 5,3,7,4557321));
        productInventory.add(new Product("Prod", 99.85, 2, 3, 4, plist));
        productInventory.add(new Product("Garni", 99.85, 2, 3, 4, plist));

        initializePartsTable();
        initializeProductsTable();
    } 
    
    
}
