/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wgu.sbro323;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elitebook
 */
public class AddPartController extends InventoryController implements Initializable {

    private Inventory inventory;
    private Part part;
    
    private boolean isChanged = false;
    
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
    @FXML
    private TextField txtPartCompany;
    @FXML
    private Label lblPartCompany;
    @FXML
    private TextField txtPartMachineID;
    @FXML
    private Label lblPartMachineID;
    
    @FXML 
    private Label lblTitle;
    
    @FXML 
    private ToggleGroup categoryGroup;
    @FXML
    private RadioButton rbtnInhouse;
    @FXML
    private RadioButton rbtnOutsourced;
    
//    private boolean isCategoryChanged(){
//        return isChanged;
//    }

    
    public void setData(String title, Inventory inventory){
        setTitle(title);
        this.inventory = inventory;
        
        if (inventory.getPart() != null) {
            this.part = inventory.getPart();
            setFields();
        }
        
    }

    
    private void setTitle(String title){
        this.lblTitle.setText(title);
    }
    
    
    private String getCategory(){      
        String category; 
        RadioButton rbtn = (RadioButton) categoryGroup.getSelectedToggle();       
        category = (rbtn.getText().equals("Inhouse")) ? "Inhouse" : "Outsourced";
        
        return category;
    }    
    
 
    private void setFields(){
        txtPartName.setText(part.getName());
        txtPartID.setText(Integer.toString(part.getPartID()));
        txtPartPrice.setText(Double.toString(part.getPrice()));
        txtPartInventory.setText(Integer.toString(part.getInstock()));
        txtPartMin.setText(Integer.toString(part.getMin()));
        txtPartMax.setText(Integer.toString(part.getMax()));
        
        if (this.part instanceof Inhouse) {
            rbtnInhouse.setSelected(true);
            Inhouse p = (Inhouse)part;
            txtPartMachineID.setText(Integer.toString(p.getMachineID()));
        } else if (this.part instanceof Outsourced) {
            Outsourced p = (Outsourced)part;
            txtPartCompany.setText(p.getCompanyName());
            rbtnOutsourced.setSelected(true);
            //this.category = "Outsourced";
        }
    }
    

    
    //Updates category and sets isChanged flag
    private boolean updateCategory(){
                
        if(this.part != null){
            if(this.part instanceof Inhouse){
                isChanged = !getCategory().equals("Inhouse");
            } else if ( this.part instanceof Outsourced){
                isChanged = !getCategory().equals("Outsourced");
            }
        }      
        return isChanged;
    }
    
    
    private Part createNewPart(){      
        
        Part newPart;
        
        if (getCategory().equals("Inhouse")) {
            newPart = new Inhouse();
        } else {
            newPart = new Outsourced();
        }    
        return newPart;
    }
    
    
    @FXML
    private void saveButtonAction(ActionEvent event){
        
        updateCategory();
        
        if ((this.part == null)){
            this.part = createNewPart(); 
        } else if (isChanged){
            this.part = createNewPart();
            this.part.setPartID(Integer.valueOf(txtPartID.getText()));
        }       
        
        //assign machineID or Company Name based on category
        if(getCategory().equals("Inhouse")){
            Inhouse p = (Inhouse)part;
            p.setMachineID(Integer.valueOf(txtPartMachineID.getText()));
        } else if (getCategory().equals("Outsourced")){
            Outsourced p = (Outsourced) part;
            p.setCompanyName(txtPartCompany.getText());
        }       

        this.part.setName(txtPartName.getText());
        this.part.setPrice(Double.valueOf(txtPartPrice.getText()));
        this.part.setInstock(Integer.valueOf(txtPartInventory.getText()));
        this.part.setMin(Integer.valueOf(txtPartMin.getText()));
        this.part.setMax(Integer.valueOf(txtPartMax.getText()));
        
        inventory.update(part);
        
        closeWindow(event);
    }
    
    @FXML
    private void cancelButtonAction(ActionEvent event) {
        cancelButton(event);
    }
    
    private void toggleCategoryFields(){
        if (getCategory().equals("Inhouse")) {
            txtPartCompany.setVisible(false);
            txtPartCompany.managedProperty().bind(txtPartCompany.visibleProperty());
            lblPartCompany.setVisible(false);
            lblPartCompany.managedProperty().bind(lblPartCompany.visibleProperty());
            
            txtPartMachineID.setVisible(true);
            txtPartMachineID.managedProperty().bind(txtPartMachineID.visibleProperty());
            lblPartMachineID.setVisible(true);
            lblPartMachineID.managedProperty().bind(lblPartMachineID.visibleProperty());
            
        } else { 
            txtPartCompany.setVisible(true);
            txtPartCompany.managedProperty().bind(txtPartCompany.visibleProperty());
            lblPartCompany.setVisible(true);
            lblPartCompany.managedProperty().bind(lblPartCompany.visibleProperty());
            
            txtPartMachineID.setVisible(false);
            txtPartMachineID.managedProperty().bind(txtPartMachineID.visibleProperty());
            lblPartMachineID.setVisible(false);
            lblPartMachineID.managedProperty().bind(lblPartMachineID.visibleProperty());                   
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        toggleCategoryFields();
        
        categoryGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle ot, Toggle nt) {
                if (categoryGroup.getSelectedToggle() != null) {
                    toggleCategoryFields();                 
                }
            }
        });
        
        
        

        

    }    
    
}
