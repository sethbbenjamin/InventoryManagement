/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wgu.sbro323;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddProductController extends InventoryController implements Initializable {

    
    private Product product;
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    
    private boolean isChanged = false;

    
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
    
    
    public boolean isChanged() {
        return isChanged;
    }
    
    public Product getData() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;

        if (this.product != null) {
            setFields();
        }

    }
    
    public void setData(String title, ObservableList<Part>... inventory) {
        setTitle(title);
        
        for(ObservableList<Part> i : inventory ){
            this.partInventory = i;
        }
        
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
        SortedList<Part> sortedData = filterTableData(partInventory, txtSearchPart, partsTable);
        partsTable.setItems(sortedData);
    }
    
    
    public void setTitle(String title){
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
    
    

    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
