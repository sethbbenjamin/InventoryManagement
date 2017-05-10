package edu.wgu.sbro323;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Seth B
 */
public class Inventory {
    
    
    //ObservableList for use with JavaFX tableview
    private final ObservableList<Product> products = FXCollections.observableArrayList();

    //Included in "Inventory" as part of the model
    private final ObservableList<Part> partInventory = FXCollections.observableArrayList();
    
    //temporary variables
    private Part part;
    private Product product;
    
    
    
    public void addProduct(Product product){
        this.products.add(product);
    }
    
    public boolean removeProduct(int id){

        Product p = lookupProduct(id);
        if(p != null){
            this.products.remove(p);
            return true;
        } else {
            return false;
        }      
    }
    
    public Product lookupProduct(int id){
        
        for(Product p : products){
            if(p.getProductID() == id){
                return p;
            }
        }        
        return null;
    }
    
    //Method not used
    public void updateProduct(int id) {
        //not used in favor of: update(product)
        throw new IllegalArgumentException("Method not used");
    }
      
    
    public void update(Product product) {
        if (this.product == null) {
            products.add(product);
        } else if (!this.product.equals(product)) {
            products.set(products.indexOf(this.product), product);
        }
    }
    
    public void update(Part part) {
        if (this.part == null) {
            partInventory.add(part);
        } else if (!this.part.equals(part)) {
            partInventory.set(partInventory.indexOf(this.part), part);
        }
    }
    
    //Helper methods
    
    public ObservableList<Part> getPartInventory(){return partInventory;}  
    public ObservableList<Product> getProductInventory() {return products;} 
    
    //set and get temporary part / product references
    public void setPart(Part part){this.part = part;}
    public Part getPart(){return part;}
    
    //Add new part to inventory
    public void addPart(Part part){
        this.partInventory.add(part);
    }
    
    public void updatePart(Part newPart, int id){
        Part oldPart = lookupPart(id);
        
        if(oldPart != null){
            int index = partInventory.indexOf(oldPart);
            partInventory.set(index, newPart);
        }
        
    }
    
    private Part lookupPart(int id){
        
        for(Part part : partInventory){
            if(part.getPartID() == id){
                return part;
            }
        }
        
        return null;      
    }
    
    //Set / get temporary product reference
    public void setProduct(Product product) {this.product = product;}
    public Product getProduct() {return product;}
    
    public void removePart(Part part){partInventory.remove(part);}
    
    
    public void clear(){
        this.part = null;
        this.product = null;
    }
    
    
}
