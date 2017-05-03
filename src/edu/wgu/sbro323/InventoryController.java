/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wgu.sbro323;

import java.text.NumberFormat;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * 
 * @author Seth B
 */
public abstract class InventoryController {
    
    
    //Bind table data to search field

    /**
     *
     * @param <T>
     * @param list
     * @param txtField
     * @param itemTable
     * @return
     */
    public <T extends InventoryItem> SortedList<T> filterTableData(ObservableList<T> list, TextField txtField, TableView itemTable) {
        FilteredList<T> filteredData = new FilteredList<>(list, p -> true);

        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all parts.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                //String i = (InventoryItem)item.g
                if (item.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                //No match
                return false;
            });
        });

        SortedList<T> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(itemTable.comparatorProperty());

        return sortedData;
    }
    
    /**
     *
     * @param priceColumn
     */
    public void formatTableColumnCurrency(TableColumn priceColumn) {
        //Format table cell "price" to display currency values
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        priceColumn.setCellFactory(column -> {
            return new TableCell<Product, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Format as currency
                        setText(currencyFormatter.format(item));
                    }
                }
            };
        });
    }
    

    
//    public abstract void setTitle(String title);
    public abstract void setData(String title, ObservableList<Part>...inventory);

    public abstract <T> T getData();
}
