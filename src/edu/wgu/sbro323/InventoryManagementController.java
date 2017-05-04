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
    
    
//    private final ObservableList<Part> partInventory = FXCollections.observableArrayList();
//    private final ObservableList<Product> productInventory = FXCollections.observableArrayList();
    
    private final Inventory inventory = new Inventory();
    

    
    
//    @FXML
//    private Parent inventoryManagementRoot;
    
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
    

    
    public void setData(String title, Inventory inventory) {
        //TO DO
    }
    
 
    
    private <T extends InventoryController> void add(String title, URL url){
        Stage stage = createStage(title, url);
        T controller = getLoader().getController();        
        
        controller.setData(title, inventory);
        stage.showAndWait();
    }
    
    @FXML
    private void addPartButtonAction(ActionEvent event) {
        inventory.clear();
        String title = "Add Part";
        URL url = getClass().getResource("AddPart.fxml");
        add(title, url);
    }
    
    @FXML
    private void addProductButtonAction(ActionEvent event) {

        inventory.clear();
        String title = "Add Product";
        URL url = getClass().getResource("AddProduct.fxml");
        add(title, url);
    }
    
    @FXML
    private void modifyPartButtonAction(ActionEvent event){

        Part part = partsTable.getSelectionModel().getSelectedItem();
        inventory.setPart(part);
        String title = "Modify Part";
        URL url = getClass().getResource("AddPart.fxml");
        add(title, url);
    }
    
    @FXML
    private void modifyProductButtonAction(ActionEvent event) {

        Product product = productsTable.getSelectionModel().getSelectedItem();
        inventory.setProduct(product);
        String title = "Modify Product";
        URL url = getClass().getResource("AddProduct.fxml");
        add(title, url);
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
                inventory.remove(part);
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
        SortedList<Part> sortedData = filterTableData(inventory.getPartInventory(), txtSearchPart, partsTable);
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
        SortedList<Product> sortedData = filterTableData(inventory.getProductInventory(), txtSearchProduct, productsTable);
        productsTable.setItems(sortedData);
    }

    /**
     *
     * @return
     */
    @Override
    public Inventory getData(){
        return inventory;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //test data
        inventory.add(new Inhouse("fish", 13.99, 5, 3, 7, 4557321));
        inventory.add(new Inhouse("chair", 14.99, 8, 3, 7, 007));
        inventory.add(new Inhouse("head", 13.99, 3, 3, 7, 77779));
        inventory.add(new Outsourced("outsourced", 11.99, 5, 3, 7, "Goog"));

        ArrayList<Part> plist = new ArrayList<>();
        plist.add(new Inhouse("Joka", 13.99, 5, 3, 7, 4557321));
        inventory.add(new Product("Prod", 99.85, 2, 3, 4, plist));
        inventory.add(new Product("Garni", 99.85, 2, 3, 4, plist));
        
        
        initializePartsTable();
        initializeProductsTable();
    } 
    
    
}
