/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Controller;

import static dtoinventorymanagementsystem.Controller.MainScreenController.filterPredicate;
import dtoinventorymanagementsystem.Model.Part;
import dtoinventorymanagementsystem.Model.Product;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class ProductController implements Initializable {
    Product product;
    ObservableList<Part> masterAssociatedList;
    ObservableList<Part> masterAvailableList;
    ChangeListener<String> partSearchListener;

    public ProductController() {
        masterAssociatedList = FXCollections.observableArrayList();
        masterAvailableList = FXCollections.observableArrayList();
    }
    
    @FXML
    private Label dialogLabel;

    @FXML
    private Label currencyLabel;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private Spinner<Integer> inventoryField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField minField;

    @FXML
    private TextField maxField;

    @FXML
    private ToggleButton partSearchButton;
    
    @FXML
    private TextField searchText;

    @FXML
    private TableView<Part> availableParts;

    @FXML
    private TableView<Part> associatedParts;

    @FXML
    void onAddPart(ActionEvent event) {
        masterAssociatedList.add(availableParts.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onCancel(ActionEvent event) {
        Alert prompt = new Alert(Alert.AlertType.CONFIRMATION, "Any changes will be lost, are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = prompt.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.YES)
            ((Stage)((Button)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void onPartSearch(ActionEvent event) {
        partSearchListener.changed(null, null, searchText.getText());
    }

    @FXML
    void onClearSearch(ActionEvent event) {
        searchText.clear();
    }

    @FXML
    void onDeletePart(ActionEvent event) {
        masterAssociatedList.remove(associatedParts.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onSave(ActionEvent event) {
        Product newProduct = product != null ? new Product(product) : new Product();
        newProduct.setName(nameField.getText());
        newProduct.setPrice(Double.parseDouble(priceField.getText()));
        newProduct.setInStock(inventoryField.valueProperty().get());
        newProduct.setMin(Integer.parseInt(minField.getText()));
        newProduct.setMax(Integer.parseInt(maxField.getText()));
        
        newProduct.getAssociatedParts().clear();
        for(int x = 0; x < associatedParts.getItems().size(); x++)
            newProduct.addAssociatedPart(associatedParts.getItems().get(x));
        
        if(!validateProduct(newProduct))
            return;

        product = newProduct;
        ((Stage)((Button)event.getSource()).getScene().getWindow()).close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Create a text filter to restrict text field entries
        IntegerTextFormatter intFilter = new IntegerTextFormatter();
        DoubleTextFormatter doubleFilter = new DoubleTextFormatter();
        
        priceField.setTextFormatter(new TextFormatter<>(doubleFilter));
        minField.setTextFormatter(new TextFormatter<>(intFilter));
        maxField.setTextFormatter(new TextFormatter<>(intFilter));
        
        FilteredList<Part> filteredAvailableData = new FilteredList<>(masterAvailableList, p -> true);
        FilteredList<Part> filteredAssociatedData = new FilteredList<>(masterAssociatedList, p -> true);
        
        partSearchListener = (observable, oldValue, newValue) -> {
            if(partSearchButton.selectedProperty().get()) {
                filteredAssociatedData.setPredicate(part -> MainScreenController.filterPredicate(part, newValue));
                filteredAvailableData.setPredicate(part -> MainScreenController.filterPredicate(part, newValue));
            }
        };
        searchText.textProperty().addListener(partSearchListener);
        
        SortedList<Part> sortedData = new SortedList<>(filteredAvailableData);
        sortedData.comparatorProperty().bind(availableParts.comparatorProperty());
        availableParts.setItems(sortedData);

        SortedList<Part> sortedData1 = new SortedList<>(filteredAssociatedData);
        sortedData1.comparatorProperty().bind(associatedParts.comparatorProperty());
        associatedParts.setItems(sortedData1);

        currencyLabel.setText(NumberFormat.getCurrencyInstance().getCurrency().getSymbol());
        
        inventoryField.editorProperty().get().setTextFormatter(new TextFormatter<>(intFilter));
        SpinnerValueFactory factory =  new SpinnerValueFactory<Integer>() {
            @Override
            public void decrement(int steps) {
                if(this.valueProperty().get() - steps >= Integer.parseInt(ProductController.this.minField.getText()))
                    setValue(this.valueProperty().get() - steps);
            }

            @Override
            public void increment(int steps) {
                if(this.valueProperty().get() + steps <= Integer.parseInt(ProductController.this.maxField.getText()))
                    setValue(this.valueProperty().get() + steps);
            }
        };
        factory.setConverter(new IntegerStringConverter());
        inventoryField.setValueFactory(factory);
        inventoryField.focusedProperty().addListener((o, oldValue, newValue) -> {
            if(!newValue)
                inventoryField.increment(0);
        });
    }    
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
        
        this.idField.setText(String.valueOf(product.getProductID()));
        this.nameField.setText(product.getName());
        this.minField.setText(String.valueOf(product.getMin()));
        this.maxField.setText(String.valueOf(product.getMax()));
        this.priceField.setText(String.valueOf(product.getPrice()));
        this.masterAssociatedList.addAll(product.getAssociatedParts());
        inventoryField.valueFactoryProperty().get().setValue(product.getInStock());
        
        dialogLabel.setText("Modify Product");
    }
    
    public void setAvailableParts(ObservableList<Part> parts)
    {
        masterAvailableList.addAll(parts);
    }
    
    private boolean validateProduct(Product product) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        boolean valid = true;
        
        if(product.getMin() > product.getMax())
        {
            error.setContentText("Min value is higher than Max.");
            valid = false;
        }
        
        // Check if inventory is within range
        if(valid && product.getInStock() < product.getMin())
        {
            error.setContentText("Inventory count is lower than the minimum.");
            valid = false;
        }
        
        if(valid && product.getInStock() > product.getMax())
        {
            error.setContentText("Inventory count is lower than the minimum.");
            valid = false;
        }
        
        if(valid)
        {
            double totalPrice = 0;
            ObservableList<Part> parts = product.getAssociatedParts();
            for(int x = 0; x < parts.size(); x++) {
                totalPrice += parts.get(x).getPrice();
            }
            
            if(totalPrice > product.getPrice()) {
                error.setContentText("Product price is lower than the sum of the parts.");
                valid = false;
            }
        }
        if(!valid)
            error.showAndWait();
        
        return valid;
    }
}
