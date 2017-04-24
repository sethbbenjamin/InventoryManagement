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
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elitebook
 */
public class AddPartController implements Initializable {

   
    private Part part;
    
    Parent root;
    
    @FXML
    private TextField txtPartName; 
    @FXML
    private TextField txtPartID;   
    @FXML
    private TextField txtPartPrice;   
    @FXML
    private TextField txtPartInventory;
    @FXML
    private TextField txtPartMin;
    @FXML
    private TextField txtPartMax;
    
    
    public Part getPart(){
        return part;
    }
    
    public void setPart(Part part){
        this.part = part;
        
        if (this.part != null) {
            setFields();
        }
        
    }
    
    
    private void setFields(){
        txtPartName.setText(part.getName());
        txtPartID.setText(Integer.toString(part.getPartID()));
        txtPartPrice.setText(Double.toString(part.getPrice()));
        txtPartInventory.setText(Integer.toString(part.getInstock()));
        txtPartMin.setText(Integer.toString(part.getMin()));
        txtPartMax.setText(Integer.toString(part.getMax()));
        
    }
    
    @FXML
    private void savePart(){
        
        if (part == null){
            this.part = new Inhouse();
        }
        
        String name = txtPartName.getText();
        this.part.setName(name);
        //this.part.setPartID(Integer.valueOf(txtPartID.getText()));
        this.part.setPrice(Double.valueOf(txtPartPrice.getText()));
        this.part.setInstock(Integer.valueOf(txtPartInventory.getText()));
        this.part.setMin(Integer.valueOf(txtPartMin.getText()));
        this.part.setMax(Integer.valueOf(txtPartMax.getText()));
        
        
        final Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
    public void setRoot(Parent layout){
        this.root = layout;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
