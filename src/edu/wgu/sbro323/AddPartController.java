/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wgu.sbro323;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

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
    
    @FXML
    private Button btnSave;
    

   public enum Category {
        INHOUSE("Inhouse"), OUTSOURCED("Outsourced");

        private final String category;

        Category(String c) {
            this.category = c;
        }
        
        public String string(){
            return category;
        }
    }
    
    
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
    
    
    private Category getCategory(){      
        Category category; 
        RadioButton rbtn = (RadioButton) categoryGroup.getSelectedToggle();       
        category = (rbtn.getText().equals("Inhouse")) ? Category.INHOUSE : Category.OUTSOURCED;
        
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
     
    
    @FXML
    private void saveButtonAction(ActionEvent event){
        
        try{
            //int partID = Integer.valueOf(txtPartID.getText());
            String name = txtPartName.getText();
            double price = Double.valueOf(txtPartPrice.getText());
            int instock = 0;
            if (!txtPartInventory.getText().isEmpty()) {
                instock = Integer.valueOf(txtPartInventory.getText());
            }
            int min = Integer.valueOf(txtPartMin.getText());
            int max = Integer.valueOf(txtPartMax.getText());
            
            try{
                //New part
                if (this.part == null) {
                    if (getCategory() == Category.INHOUSE) {
                        int machineID = Integer.valueOf(txtPartMachineID.getText());
                        Part newPart = new Inhouse(name, price, instock, min, max, machineID);
                        inventory.addPart(newPart);
                    } else if (getCategory() == Category.OUTSOURCED) {
                        String companyName = txtPartCompany.getText();
                        Part newPart = new Outsourced(name, price, instock, min, max, companyName);
                        inventory.addPart(newPart);
                    }
                    //Update part    
                } else {
                    int id = this.part.getPartID();

                    if (getCategory() == Category.INHOUSE) {
                        int machineID = Integer.valueOf(txtPartMachineID.getText());
                        Part newPart = new Inhouse(name, price, instock, min, max, machineID);
                        newPart.setPartID(id);
                        inventory.updatePart(newPart, id);
                    } else if (getCategory() == Category.OUTSOURCED) {
                        String companyName = txtPartCompany.getText();
                        Part newPart = new Outsourced(name, price, instock, min, max, companyName);
                        newPart.setPartID(id);
                        inventory.updatePart(newPart, id);
                    }
                }

                closeWindow(event);
            }catch(InvalidInventoryException e){
                showErrorDialog(e.getMessage());
            }catch(IllegalArgumentException e){
                showErrorDialog("Invalid or missing data");
            }
        } catch (NumberFormatException e){
            showErrorDialog("Must be a valid number: " + e.getMessage());

        }
        

        
    }
    
    @FXML
    private void cancelButtonAction(ActionEvent event) {
        cancelButton(event);
    }
    
    private void toggleCategoryFields(){
        if (getCategory().equals(Category.INHOUSE)) {
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
        
        btnSave.disableProperty().bind(Bindings.isEmpty(txtPartName.textProperty())
                .or(Bindings.isEmpty(txtPartPrice.textProperty()))
                .or(Bindings.isEmpty(txtPartInventory.textProperty()))
                .or(Bindings.isEmpty(txtPartMin.textProperty()))
                .or(Bindings.isEmpty(txtPartMax.textProperty()))
        );
        
        
        

        

    }    
    
}
