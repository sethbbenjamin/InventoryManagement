package edu.wgu.sbro323;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Seth B
 */
public abstract class InventoryController {
    
    private FXMLLoader loader;
    
    
    public abstract void setData(String title, Inventory inventory);

    
    
    public FXMLLoader getLoader(){
        return loader;
    }
    
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
    
    
    public void closeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    
    public void cancelButton(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            closeWindow(event);
        }
    }
    
    
    public Stage createStage(String title, URL url) {
        Stage stage = new Stage();
        loader = new FXMLLoader(url);

        try {
            stage.setScene(new Scene(loader.load()));
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initOwner(inventoryManagementRoot.getScene().getWindow());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stage;
    }
    
    

}
