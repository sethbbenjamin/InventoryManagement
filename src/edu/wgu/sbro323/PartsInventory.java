/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wgu.sbro323;

import java.util.ArrayList;

/**
 *
 * @author elitebook
 */
public class PartsInventory {
    
    ArrayList<Part> parts;
    
    
    public void addPartToInventory(String name, int ID, Double price, int instock, int min, int max, int machineID){
        Inhouse part = new Inhouse();
        
        part.setName(name);
        part.setPartID(ID);
        part.setPrice(price);
        part.setInstock(instock);
        part.setMin(min);
        part.setMax(max);
        part.setMachineID(machineID);
        
        parts.add(part);
    }
    
    public void addPartToInventory(String name, int ID, Double price, int instock, int min, int max, String companyName) {
        
        Outsourced part = new Outsourced();
        
        part.setName(name);
        part.setPartID(ID);
        part.setPrice(price);
        part.setInstock(instock);
        part.setMin(min);
        part.setMax(max);
        part.setCompanyName(companyName);
        
        parts.add(part);

    }
    
    
public ArrayList<Part> getParts(){
    return parts;
}
    
}
