/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wgu.sbro323;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddProductController extends InventoryController implements Initializable {

    
    private Inventory inventory;
    private Product product;
    private ObservableList<Part> attachedParts;
    
    //private boolean isChanged = false;

    
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtProductID;
    @FXML
    private TextField txtProductPrice;
    @FXML
    private TextField txtProductInventory;
    @FXML
    private TextField txtProductMin;
    @FXML
    private TextField txtProductMax;
    @FXML
    private TextField txtSearchPart;
    
    @FXML
    private Label lblTitle;
    
    @FXML
    private Button btnSave;

    
    
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
    private TableColumn<Part, Double> partPriceColumn;
    
    
    //attached part table
    @FXML
    private TableView<Part> attachedPartsTable;
    @FXML
    private TableColumn<Part, Integer> attachedPartIDColumn;
    @FXML
    private TableColumn<Part, String> attachedPartNameColumn;
    @FXML
    private TableColumn<Part, Integer> attachedPartInstockColumn;
    @FXML
    private TableColumn<Part, Double> attachedPartPriceColumn;

    
    
    
    public void setProduct(Product product) {

    }
    
    /**
     *
     * @param title
     * @param inventory
     */
    @Override
    public void setData(String title, Inventory inventory) {
        setTitle(title); 
        this.inventory = inventory;
        this.product = inventory.getProduct();

        if (this.product != null) {
            setFields();
            attachedParts = FXCollections.observableArrayList(product.getParts());
        }
        

        initializeAttachedPartsTable();      
        initializePartsTable();

    }
    
    private void initializePartsTable() {

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
    
    private void initializeAttachedPartsTable() {

        if (attachedParts == null) {
            attachedParts = FXCollections.observableArrayList();
        }
        
        //Populate and bind table column data
        attachedPartNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        attachedPartIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        attachedPartInstockColumn.setCellValueFactory(cellData -> cellData.getValue().instockProperty().asObject());
        attachedPartPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        //Format as currency
        formatTableColumnCurrency(attachedPartPriceColumn);
        attachedPartsTable.setItems(attachedParts);
    }
    
    
    private void setTitle(String title){
        this.lblTitle.setText(title);
    }
    
    private void setFields() {
        txtProductName.setText(product.getName());
        txtProductID.setText(Integer.toString(product.getProductID()));
        txtProductPrice.setText(Double.toString(product.getPrice()));
        txtProductInventory.setText(Integer.toString(product.getInstock()));
        txtProductMin.setText(Integer.toString(product.getMin()));
        txtProductMax.setText(Integer.toString(product.getMax()));
        
    }
     
    @FXML
    private void attachButtonAction(ActionEvent event){
        Part part = partsTable.getSelectionModel().getSelectedItem();
        if(itemIsSelected(part)){
            attachedParts.add(part);
        }
    }
    
    @FXML
    private void cancelButtonAction(ActionEvent event) {
        cancelButton(event);
    }
    
    @FXML
    private void saveButtonAction(ActionEvent event){

        ArrayList<Part> attached = new ArrayList<>(attachedParts);

        
        try{        
            String name = txtProductName.getText();
            
            double price = Double.valueOf(txtProductPrice.getText());
            
            int instock = 0;
            if(!txtProductInventory.getText().isEmpty()){
                instock = Integer.valueOf(txtProductInventory.getText());
            }
            
            int min = Integer.valueOf(txtProductMin.getText());
            int max = Integer.valueOf(txtProductMax.getText()); 

            if (this.product == null) {

                try{
                    this.product = new Product(name, price, instock, min, max, attached);
                    inventory.addProduct(this.product); 
                    closeWindow(event);
                } catch (InvalidInventoryException e) {
                    showErrorDialog(e.getMessage());
                } catch (IllegalArgumentException e) {
                    showErrorDialog("Invalid or missing data");
                }

            } else {
                
                try{

                    this.product = new Product(name, price, instock, min, max, attached);
                    inventory.update(product);
                    closeWindow(event);

                } catch (InvalidInventoryException e) {
                    showErrorDialog(e.getMessage());
                } catch (IllegalArgumentException e) {
                    showErrorDialog("Invalid or missing data");
                }
            }
        } catch (NumberFormatException e){
            showErrorDialog("Must be a valid number: " + e.getMessage());
        }
 
    }
    
    @FXML
    private void removeButtonAction(ActionEvent event){
        Part part = attachedPartsTable.getSelectionModel().getSelectedItem();
        if(itemIsSelected(part)){
            attachedParts.remove(part);
        }
    }
    
    @FXML
    private void clearPartButtonAction(ActionEvent event) {
        txtSearchPart.clear();
    }
     
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        btnSave.disableProperty().bind(Bindings.isEmpty(txtProductName.textProperty())
                .or(Bindings.isEmpty(txtProductPrice.textProperty()))
                .or(Bindings.isEmpty(txtProductInventory.textProperty()))
                .or(Bindings.isEmpty(txtProductMin.textProperty()))
                .or(Bindings.isEmpty(txtProductMax.textProperty()))
        
        );
        
       
        
        
    }    
    
}
