package edu.wgu.sbro323;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author elitebook
 */
public class InventoryManagementController extends InventoryController implements Initializable {
      

    
    
//    @FXML
//    private Parent inventoryManagementRoot;
    
    private Inventory inventory;

    
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
    private Button btnModifyPart;
    @FXML
    private Button btnModifyProduct;

    
    @FXML
    private void exitButtonAction(ActionEvent event){
        closeWindow(event);
    }

    
    @Override
    public void setData(String title, Inventory inventory) {
        //TO DO
        this.inventory = inventory;
        initializePartsTable();
        initializeProductsTable();
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
        if(itemIsSelected(part)){
            inventory.setPart(part);
            String title = "Modify Part";
            URL url = getClass().getResource("AddPart.fxml");
            add(title, url);
        }
    }
    
    @FXML
    private void modifyProductButtonAction(ActionEvent event) {

        Product product = productsTable.getSelectionModel().getSelectedItem();
        if(itemIsSelected(product)){
            inventory.setProduct(product);
            String title = "Modify Product";
            URL url = getClass().getResource("AddProduct.fxml");
            add(title, url);
        }
    }
    

    
      

    
    @FXML
    private void deletePartButtonAction(){ 
        Part part = partsTable.getSelectionModel().getSelectedItem();

        if (itemIsSelected(part)) {
            if (deleteConfirmed("Delete Part?")){
                inventory.removePart(part);
            }
        }
    }
    
    @FXML
    private void deleteProductButtonAction() {
        Product product = productsTable.getSelectionModel().getSelectedItem();

        if (itemIsSelected(product)) {
            int size = product.getParts().size();
            String message = "Contains (" + Integer.toString(size) + ") attached parts. Delete?";
            if (deleteConfirmed(message)){
                inventory.removeProduct(product.getProductID());
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
        
        toggleModifyButton(btnModifyPart, partsTable);
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
        toggleModifyButton(btnModifyProduct, productsTable);
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    } 
    
    
}
