package edu.wgu.sbro323;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author elitebook
 */
public class InventoryManagementController implements Initializable {
    
    
    private final ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private final ObservableList<Product> productInventory = FXCollections.observableArrayList();
    
    private FXMLLoader loader;

    
    
    @FXML
    private Parent inventoryManagementRoot;
    
    @FXML
    private Parent addPartRoot;
    
//    @FXML
//    private Button btnExit;
//    
//    @FXML
//    private Button btnAddPart;
//    @FXML
//    private Button btnModifyPart;
//    @FXML
//    private Button btnDeletePart;
//    @FXML
//    private Button btnSearchPart;
    
    @FXML
    private TextField txtSearchPart;
    
    //part table
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partIDColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> instockColumn;
    @FXML
    private TableColumn<Part, Double>priceColumn;

    
    @FXML
    private void exitButtonAction(ActionEvent event){

        //final Node source = (Node) event.getSource();
//        final Stage stage = (Stage) inventoryManagementRoot.getScene().getWindow();
//        stage.close();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void addPartButtonAction(ActionEvent event){

        String title = "Add Part";
        URL url = getClass().getResource("AddPart.fxml");
        Stage stage = createStage(title, url);

        AddPartController addPartController = loader.getController();
        addPartController.setTitle(title);

        //place at end so application doesn't "wait" before it should
        stage.showAndWait();
              
        if (addPartController.getPart() != null)
        {
            partInventory.add(addPartController.getPart());
        }
    }
    
    @FXML
    private void modifyPartButtonAction(ActionEvent event){

        Part part = partsTable.getSelectionModel().getSelectedItem();
        String title = "Modify Part";
        URL url = getClass().getResource("AddPart.fxml");

        
                
        if(itemIsSelected(part)){
            Stage stage = createStage(title, url);
            
            AddPartController addPartController = loader.getController();
            addPartController.setPart(part);
            addPartController.setTitle(title);

            stage.showAndWait();

            if (addPartController.isChanged()) {
                int i = partInventory.indexOf(part);
                partInventory.set(i, addPartController.getPart());
            }
            
        }      
    }
    
    
    private <T> boolean itemIsSelected(T item) {
        
        if (item != null) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notice");
            alert.setHeaderText(null);
            alert.setContentText("No item selected!");
            alert.showAndWait();
        }
        
        return false;
    }
    
      
    private Stage createStage(String title, URL url){
        Stage stage = new Stage();
        loader = new FXMLLoader(url);
        
        try{
            stage.setScene(new Scene(loader.load()));
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(inventoryManagementRoot.getScene().getWindow());

        } catch(IOException e){
            e.printStackTrace();
        }
        
        return stage;
    }
    
    @FXML
    private void deletePartButtonAction(){ 
        Part part = partsTable.getSelectionModel().getSelectedItem();

        if(part != null){    
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Delete Part?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                partInventory.remove(part);
            }
        }
    }
    
    
    @FXML
    private void searchPartButtonAction(ActionEvent event){
        txtSearchPart.clear();   
    }
    
    private void initializePartsTable(){
        
        //Populate and bind table column data
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        partIDColumn.setCellValueFactory(cellData -> cellData.getValue().PartIDProperty().asObject());
        instockColumn.setCellValueFactory(cellData -> cellData.getValue().instockProperty().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        //Format table cell "price" to display currency values
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        priceColumn.setCellFactory(column -> {
            return new TableCell<Part, Double>() {
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

        FilteredList<Part> filteredData = new FilteredList<>(partInventory, p -> true);

        txtSearchPart.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(part -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (part.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                //No match
                return false; 
            });
        });

        SortedList<Part> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(partsTable.comparatorProperty());

        partsTable.setItems(sortedData);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        //test data
        partInventory.add(new Inhouse("fish", 13.99, 5,3,7,4557321));
        partInventory.add(new Inhouse("chair", 14.99, 8, 3, 7, 007));
        partInventory.add(new Inhouse("head", 13.99, 3, 3, 7,77779));
        partInventory.add(new Outsourced("outsourced", 11.99, 5, 3, 7, "Goog"));

        initializePartsTable();

     
    } 
    
    
}
