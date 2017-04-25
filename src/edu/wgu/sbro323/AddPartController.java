/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wgu.sbro323;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
public class AddPartController implements Initializable {

   
    private Part part;
    private Parent root;
    
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
    private TextField txtCompany;
    @FXML
    private Label lblCompany;
    @FXML
    private TextField txtMachineID;
    @FXML
    private Label lblMachineID;
    
    @FXML 
    private Label lblTitle;
    
    @FXML 
    private ToggleGroup categoryGroup = new ToggleGroup();
    @FXML
    private RadioButton rbtnInhouse;
    @FXML
    private RadioButton rbtnOutsourced;
    
    //private String category;
    
    
    
    
//    private void setCategory(String category){
//        this.category = category;
//    }
    
    private String getCategory(){
        
        String category;
        
        RadioButton rbtn = (RadioButton) categoryGroup.getSelectedToggle();
        
        category = (rbtn.getText().equals("Inhouse")) ? "Inhouse" : "Outsourced";
        
        return category;
    }
    
    
    
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
        
        if (this.part instanceof Inhouse) {
            rbtnInhouse.setSelected(true);
            //this.category = "Inhouse";
        } else if (this.part instanceof Outsourced) {
            rbtnOutsourced.setSelected(true);
            //this.category = "Outsourced";
        }
        
        

        

    }
    
    
    private void setCategory(){

    }
    
    private boolean categoryChanged(){
        
        boolean isChanged = false;
        
        if(this.part != null){
            if(this.part instanceof Inhouse){
                isChanged = !getCategory().equals("Inhouse");
            } else if ( this.part instanceof Outsourced){
                isChanged = !getCategory().equals("Outsourced");
            }
        }      
        return isChanged;
    }
    
    
    @FXML
    private void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
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
        
        if ((part == null) || (categoryChanged()) ){
            this.part = createNewPart(); 
        }
        
        if (categoryChanged()){
            this.part.setPartID(Integer.valueOf(txtPartID.getText()));
        }
        
        
        String name = txtPartName.getText();
        this.part.setName(name);
        this.part.setPrice(Double.valueOf(txtPartPrice.getText()));
        this.part.setInstock(Integer.valueOf(txtPartInventory.getText()));
        this.part.setMin(Integer.valueOf(txtPartMin.getText()));
        this.part.setMax(Integer.valueOf(txtPartMax.getText()));
        //System.out.println(name);

        
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
    public void setRoot(Parent layout){
        this.root = layout;
    }
    
    private void toggleCategoryFields(){
        if (getCategory().equals("Inhouse")) {
            txtCompany.setVisible(false);
            txtCompany.managedProperty().bind(txtCompany.visibleProperty());
            lblCompany.setVisible(false);
            lblCompany.managedProperty().bind(lblCompany.visibleProperty());
            
            txtMachineID.setVisible(true);
            txtMachineID.managedProperty().bind(txtMachineID.visibleProperty());
            lblMachineID.setVisible(true);
            lblMachineID.managedProperty().bind(lblMachineID.visibleProperty());
            
        } else { 
            txtCompany.setVisible(true);
            txtCompany.managedProperty().bind(txtCompany.visibleProperty());
            lblCompany.setVisible(true);
            lblCompany.managedProperty().bind(lblCompany.visibleProperty());
            
            txtMachineID.setVisible(false);
            txtMachineID.managedProperty().bind(txtMachineID.visibleProperty());
            lblMachineID.setVisible(false);
            lblMachineID.managedProperty().bind(lblMachineID.visibleProperty());                   
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
                    Toggle old_toggle, Toggle new_toggle) {
                if (categoryGroup.getSelectedToggle() != null) {
                    //System.out.println("RADIO killed the radio star");
                    toggleCategoryFields();                 
                }
            }
        });
        
        
        

        

    }    
    
}
