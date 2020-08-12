/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Controller;

import dtoinventorymanagementsystem.DTOInventoryManagementSystem;
import dtoinventorymanagementsystem.Model.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class MainScreenController implements Initializable {
    Inventory inventory;
    ChangeListener<String> partSearchListener;
    ChangeListener<String> productSearchListener;
    
    public MainScreenController() {
        inventory = new Inventory();
        
        inventory.addPart(new InhousePart("Wheel", 10.0, 1, 1, 10, 1));
        inventory.addPart(new InhousePart("Engine", 2.0, 1, 1, 10, 1));
        inventory.addPart(new InhousePart("Seat", 5.5, 1, 1, 10, 1));
        inventory.addPart(new InhousePart("Console", 1.0, 1, 1, 10, 1));
        ObservableList<Part> parts = FXCollections.observableArrayList();
        parts.add(inventory.lookupPart(1));
        inventory.addProduct(new Product("Product 1", 100.00, 1, 1, 10, parts));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up a wrapped FilteredList to enable searching
        FilteredList<Part> filteredData = new FilteredList<>(inventory.getParts(), p -> true);
        partSearchListener = (observable, oldValue, newValue) -> {
            if(partSearchButton.selectedProperty().get())
                filteredData.setPredicate(part -> filterPredicate(part, newValue));
        };
        partSearchText.textProperty().addListener(partSearchListener);
        SortedList<Part> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(partsList.comparatorProperty());
        partsList.setItems(sortedData);
        
        FilteredList<Product> filteredProductData = new FilteredList<>(inventory.getProducts(), p -> true);
        productSearchListener = (observable, oldValue, newValue) -> {
            if(productSearchButton.selectedProperty().get())
                filteredProductData.setPredicate(product -> filterPredicate(product, newValue));
        };
        productSearchText.textProperty().addListener(productSearchListener);
        SortedList<Product> sortedProductData = new SortedList<>(filteredProductData);
        sortedProductData.comparatorProperty().bind(productList.comparatorProperty());
        productList.setItems(sortedProductData);

    }
    
    @FXML
    private ToggleButton partSearchButton;
    
    @FXML
    private ToggleButton productSearchButton;
    
    @FXML
    private TableView<Part> partsList;

    @FXML
    private TextField partSearchText;
    
    @FXML
    private TableView<Product> productList;

    @FXML
    private TextField productSearchText;

    @FXML
    void onAddProduct(ActionEvent event) throws IOException {
        // Show window, wait for user
        FXMLLoader loader = loadWindow("View/AddProduct.fxml", (Stage)((Button)event.getSource()).getScene().getWindow());
        Stage stage = (Stage)((Parent)loader.getRoot()).getScene().getWindow();
        
        // Get product from window, and add to list
        ProductController controller = loader.getController();
        controller.setAvailableParts(inventory.getParts());
        
        stage.showAndWait();
        Product product = controller.getProduct();
        
        if(product != null)
            inventory.addProduct(product);
    }

    @FXML
    void onClearProductSearch(ActionEvent event) {
        productSearchText.clear();
    }

    @FXML
    void onDeleteProduct(ActionEvent event) {
        Product product = productList.getSelectionModel().getSelectedItem();
        
        Alert prompt = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the product \"" + product.getName() + "\"?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = prompt.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES)
        {
            int id = product.getProductID();
            inventory.removeProduct(id);
        }
    }

    @FXML
    void onModifyProduct(ActionEvent event) throws IOException {
        // Show window, wait for user
        FXMLLoader loader = loadWindow("View/ModifyProduct.fxml", (Stage)((Button)event.getSource()).getScene().getWindow());
        Stage stage = (Stage)((Parent)loader.getRoot()).getScene().getWindow();

        // Get product from window, and add to list
        ProductController controller = loader.getController();
        int id = productList.getSelectionModel().getSelectedItem().getProductID();
        controller.setProduct(inventory.lookupProduct(id));
        controller.setAvailableParts(inventory.getParts());

        stage.showAndWait();

        Product product = controller.getProduct();
        
        if(product != null)
            inventory.updateProduct(product.getProductID(), product);
    }

    @FXML
    void onAddPart(ActionEvent event) throws IOException, InterruptedException {
        // Show window, wait for user
        FXMLLoader loader = loadWindow("View/AddPart.fxml", (Stage)((Button)event.getSource()).getScene().getWindow());
        Stage stage = (Stage)((Parent)loader.getRoot()).getScene().getWindow();
        stage.showAndWait();

        // Get product from window, and add to list
        PartController controller = loader.getController();
        Part part = controller.getPart();
        
        if(part != null)
            inventory.addPart(part);
    }

    @FXML
    void onClearPartSearch(ActionEvent event) {
        partSearchText.clear();
    }

    @FXML
    void onDeletePart(ActionEvent event) {
        Part part = partsList.getSelectionModel().getSelectedItem();
        Alert prompt = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the part \"" + part.getName() + "\"?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = prompt.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES)
        {
            int id = part.getPartID();
            inventory.deletePart(id);
        }
    }

    @FXML
    void onModifyPart(ActionEvent event) throws IOException {
        // Show window, wait for user
        FXMLLoader loader = loadWindow("View/ModifyPart.fxml", (Stage)((Button)event.getSource()).getScene().getWindow());
        Stage stage = (Stage)((Parent)loader.getRoot()).getScene().getWindow();
        
        // Send product to window
        PartController controller = loader.getController();
        int id = partsList.getSelectionModel().getSelectedItem().getPartID();
        controller.setPart(inventory.lookupPart(id));
        stage.showAndWait();
        
        // Grab new product, if user canceled, product is the same
        Part part = controller.getPart();
        inventory.updatePart(id, part);
    }

    @FXML
    void onPartSearch(ActionEvent event) {
        partSearchListener.changed(null, null, partSearchText.getText());
    }

    @FXML
    void onProductSearch(ActionEvent event) {
        productSearchListener.changed(null, null, productSearchText.getText());
    }
    
    @FXML
    void onExit(ActionEvent event) {
        ((Stage)((Button)event.getSource()).getScene().getWindow()).close();
    }
    
    private FXMLLoader loadWindow(String resource, Stage owner) throws IOException
    {
        // Create window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(DTOInventoryManagementSystem.class.getResource(resource));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        // caller should show the window
        return loader;
    }
    
    public static boolean filterPredicate(Part part, String filterText)
    {
        if (filterText == null || filterText.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = filterText.toLowerCase();
        String partId = String.valueOf(part.getPartID());

        if (part.getName().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (partId.contains(lowerCaseFilter)) {
            return true;
        }
        return false;
    }

    public static boolean filterPredicate(Product product, String filterText)
    {
        if (filterText == null || filterText.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = filterText.toLowerCase();
        String productId = String.valueOf(product.getProductID());

        if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (productId.contains(lowerCaseFilter)) {
            return true;
        }
        return false;
    }
}
