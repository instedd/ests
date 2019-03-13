package ug.co.sampletracker.app.utils.general;

import android.util.Log;
import android.widget.ArrayAdapter;

import fr.ganfra.materialspinner.MaterialSpinner;
import ug.co.sampletracker.app.utils.constants.ConstStrings;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/10/2018.
 */

public class SpinnerHandler {

    public static void populateSpinner(MaterialSpinner spinner, ArrayAdapter<String> adapter){

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public static void clearSpinners(MaterialSpinner[] spinners) {

        for (MaterialSpinner spinner :
                spinners) {
            spinner.setSelection(0);
        }

    }

    public static void selectValue(MaterialSpinner spinner, ArrayAdapter<String> adapter, String compareValue) {

      /*  if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spinner.setSelection(spinnerPosition);
        }
*/
        Log.e("Compare Value",compareValue);

        for (int spinnerPosition=0;spinnerPosition<adapter.getCount();spinnerPosition++){

            if (spinner.getItemAtPosition(spinnerPosition).toString().equalsIgnoreCase(compareValue)){
                Log.e("Match found",compareValue);
                spinner.setSelection(spinnerPosition+1);
                break;
            }

        }

    }

    public static String spinnerValue(MaterialSpinner spinner){

        if(spinner != null && spinner.getSelectedItem() != null ) {
            return  (String)spinner.getSelectedItem();
        }
        return  ConstStrings.PROMPT_OPTION;

    }

}
