package ug.co.sampletracker.app.utils.general;

import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.widget.EditText;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/20/2018.
 */
public class ViewHandler {

    public static void setPasswordHintType(EditText edt) {
        edt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    public  static void clearErrors(EditText[] editTexts){
        for (EditText editText : editTexts) {
            editText.setError(null);
        }
    }

    public static void clearErrors(TextInputLayout[] edtWraps) {

        for (TextInputLayout inputLayout : edtWraps) {
            inputLayout.setErrorEnabled(false);
        }
    }
}
