package br.com.flaviogf.strikeproductcatalog.extensions;

import android.widget.Spinner;

public class SpinnerExtensions {
    public static void setSelection(Spinner spinner, String[] values, String value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}
