/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Controller;

import dtoinventorymanagementsystem.Model.Part;
import java.text.NumberFormat;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author Daniel
 */
public class CurrencyTableCell<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    
    @Override
    public TableCell<S, T> call(TableColumn<S, T> param) {
        return new TableCell<S, T>() {
            @Override
            protected void updateItem(T price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        };
    }
}
