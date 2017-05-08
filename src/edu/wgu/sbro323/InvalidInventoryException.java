package edu.wgu.sbro323;

/**
 *
 * @author Seth B
 */
public class InvalidInventoryException extends Exception {
    
    InvalidInventoryException(){
        //empty constructor
    }
    
    InvalidInventoryException(String message){
        super(message);
    }
    
}
