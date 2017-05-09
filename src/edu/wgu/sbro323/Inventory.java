package edu.wgu.sbro323;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Seth B
 */
public class Inventory {
    
    
    private ArrayList<Product> products;
    
    private final ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private final ObservableList<Product> productInventory = FXCollections.observableArrayList();
    
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
    
    
    
    public void setPart(Part part){
        this.part = part;
    }
    
    public Part getPart(){
        return part;
    }
    
    
    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }  
    
    
    public void update(Part part){
        if(this.part == null){
            partInventory.add(part);
        } else if(!this.part.equals(part)) {
            partInventory.set(partInventory.indexOf(this.part), part);
        }
    }
    
    public void update(Product product) {
        if (this.product == null) {
            productInventory.add(product);
        } else if (!this.product.equals(product)) {
            productInventory.set(productInventory.indexOf(this.product), product);
        }
    }
    
//    private void update(Part newPart){
//    }
    
//    private void update(Product newProduct) {
//        productInventory.set(productInventory.indexOf(this.product), newProduct);
//    }
    
    public ObservableList<Part> getPartInventory(){
        return partInventory;
    }
    
    public ObservableList<Product> getProductInventory() {
        return productInventory;
    }
    
    public void remove(Part part){
        partInventory.remove(part);
    }
    
    public void remove(Product product) {
        productInventory.remove(product);
    }
    
    public void clear(){
        this.part = null;
        this.product = null;
    }
    
    
}
