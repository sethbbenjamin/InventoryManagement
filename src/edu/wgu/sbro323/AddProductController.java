/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wgu.sbro323;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Seth B
 */




public class AddProductController implements Initializable {

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
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
