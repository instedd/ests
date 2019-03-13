package ug.co.sampletracker.app.utils.general;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

import ug.co.sampletracker.app.utils.constants.ConstStrings;

public class DatePickerListener implements DatePickerDialog.OnDateSetListener {

    public EditText edt;

    public DatePickerListener(EditText edt) {
        this.edt = edt;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int mYear, int mMonth,int mDay) {

        if(edt != null) {

           Date date = DateHandler.buildDate(mYear,mMonth,mDay);
           edt.setText(new DateHandler().getHumanReadableDate(date,ConstStrings.DATE_FORMAT_TXN_SHORT));

        }

    }
}
