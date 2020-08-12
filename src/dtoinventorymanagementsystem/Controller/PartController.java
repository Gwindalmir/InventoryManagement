/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Controller;

import com.sun.javaws.exceptions.InvalidArgumentException;
import dtoinventorymanagementsystem.Model.InhousePart;
import dtoinventorymanagementsystem.Model.OutsourcedPart;
import dtoinventorymanagementsystem.Model.Part;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PartController implements Initializable {
    private Part part;
    int inventory;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Create a text filter to restrict text field entries
        IntegerTextFormatter intFilter = new IntegerTextFormatter();
        DoubleTextFormatter doubleFilter = new DoubleTextFormatter();
        
        price.setTextFormatter(new TextFormatter<>(doubleFilter));
        min.setTextFormatter(new TextFormatter<>(intFilter));
        max.setTextFormatter(new TextFormatter<>(intFilter));
        machineID.setTextFormatter(new TextFormatter<>(intFilter));
        inventoryCount.editorProperty().get().setTextFormatter(new TextFormatter<>(intFilter));
        SpinnerValueFactory factory =  new SpinnerValueFactory<Integer>() {
            @Override
            public void decrement(int steps) {
                if(this.valueProperty().get() - steps >= Integer.parseInt(PartController.this.min.getText()))
                    setValue(this.valueProperty().get() - steps);
            }

            @Override
            public void increment(int steps) {
                if(this.valueProperty().get() + steps <= Integer.parseInt(PartController.this.max.getText()))
                    setValue(this.valueProperty().get() + steps);
            }
        };
        factory.setConverter(new IntegerStringConverter());
        inventoryCount.setValueFactory(factory);
        inventoryCount.focusedProperty().addListener((o, oldValue, newValue) -> {
            if(!newValue)
                inventoryCount.increment(0);
        });
        
        currencyLabel.setText(NumberFormat.getCurrencyInstance().getCurrency().getSymbol());
    }    
    
    @FXML
    private Label dialogLabel;
    
    @FXML
    private Label machineIDLabel;

    @FXML
    private Label companyNameLabel;

    @FXML
    private Label currencyLabel;
    
    @FXML
    private Button addButton;
    
    @FXML
    private RadioButton radioInhouse;

    @FXML
    private RadioButton radioOutsourced;

    @FXML
    private TextField partID;

    @FXML
    private TextField partName;

//    @FXML
//    private TextField inventoryCount;
    @FXML
    private Spinner<Integer> inventoryCount;

    @FXML
    private TextField price;

    @FXML
    private TextField min;

    @FXML
    private TextField max;

    @FXML
    private TextField machineID;

    @FXML
    private TextField companyName;

    @FXML
    void onAdd(ActionEvent event) throws IllegalArgumentException {
        Part newpart = null;
            
        if(radioInhouse.isSelected())
        {
            newpart = part != null ? new InhousePart(part) : new InhousePart();
            ((InhousePart)newpart).setMachineID(Integer.parseInt(machineID.getText()));
        }
        else if(radioOutsourced.isSelected())
        {
            newpart = part != null ? new OutsourcedPart(part) : new OutsourcedPart();
            ((OutsourcedPart)newpart).setCompanyName(companyName.getText());
        }
        else
            throw new IllegalArgumentException("Invalid part type");
        
        newpart.setName(partName.getText());
        newpart.setPrice(Double.parseDouble(price.getText()));
        newpart.setInStock(inventoryCount.valueProperty().get());
        newpart.setMin(Integer.parseInt(min.getText()));
        newpart.setMax(Integer.parseInt(max.getText()));

        if(!validatePart(newpart))
            return;
        
        part = newpart;
        ((Stage)((Button)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void onCancel(ActionEvent event) {
        Alert prompt = new Alert(Alert.AlertType.CONFIRMATION, "Any changes will be lost, are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = prompt.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.YES)
            ((Stage)((Button)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void onRadioAction(ActionEvent event) {
        if(radioInhouse.isSelected())
        {
            machineID.setVisible(true);
            machineIDLabel.setVisible(true);
            companyName.setVisible(false);
            companyNameLabel.setVisible(false);
        }
        else
        {
            machineID.setVisible(false);
            machineIDLabel.setVisible(false);
            companyName.setVisible(true);
            companyNameLabel.setVisible(true);
        }
    }
    
    public void setPart(Part part) {
        this.part = part;
        if(part != null)
        {
            // If we are setting the part, then switch to "Modify"
            dialogLabel.setText("Modify Part");
            addButton.setText("Modify");
            
            if(part instanceof InhousePart)
            {
                radioInhouse.setSelected(true);
                machineID.setText(String.valueOf(((InhousePart) part).getMachineID()));
            }
            else
            {
                radioOutsourced.setSelected(true);
                companyName.setText(((OutsourcedPart) part).getCompanyName());
            }
        
            partID.setText(String.valueOf(part.getPartID()));
            partName.setText(part.getName());
            price.setText(String.valueOf(part.getPrice()));
            min.setText(String.valueOf(part.getMin()));
            max.setText(String.valueOf(part.getMax()));
            inventoryCount.valueFactoryProperty().get().setValue(part.getInStock());
        }
    }

    public Part getPart() {
        return part;
    }
    
    private boolean validatePart(Part part) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        boolean valid = true;
        
        if(part.getMin() > part.getMax())
        {
            error.setContentText("Min value is higher than Max.");
            valid = false;
        }
        
        // Check if inventory is within range
        if(valid && part.getInStock() < part.getMin())
        {
            error.setContentText("Inventory count is lower than the minimum.");
            valid = false;
        }
        
        if(valid && part.getInStock() > part.getMax())
        {
            error.setContentText("Inventory count is lower than the minimum.");
            valid = false;
        }
        
        if(!valid)
            error.showAndWait();
        
        return valid;
    }
}
