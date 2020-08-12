/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Controller;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.UnaryOperator;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author Daniel
 */
public abstract class NumericTextFormatterBase implements UnaryOperator<TextFormatter.Change> {
    // Example found from: https://gist.github.com/karimsqualli96/f8d4c2995da8e11496ed
    DecimalFormatSymbols decimal = new DecimalFormatSymbols(Locale.getDefault());
    String decimalSep = String.valueOf(decimal.getDecimalSeparator());
    String radixSep = String.valueOf(decimal.getGroupingSeparator());
    
    @Override
    public TextFormatter.Change apply(TextFormatter.Change t) {
        if(t.isReplaced())
        {
            if(t.getText().matches("[^0-9" + radixSep + (this instanceof DoubleTextFormatter ? decimalSep : "") + "]"))
                t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
        }
        else if(t.isAdded()) {
            if (t.getText().matches("[^0-9" + radixSep + decimalSep + "]")) {
                t.setText("");
            }
        }
        return t;
    }
    
}
