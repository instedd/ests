package ug.co.sampletracker.app.components.registeredsamples.view.dialog;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.GregorianCalendar;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.models.requests.StatementRequest;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.DataGenerator;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.constants.EnumStatementTypes;
import ug.co.sampletracker.app.utils.constants.EnumStmtReceiptionTypes;
import ug.co.sampletracker.app.utils.general.DateHandler;
import ug.co.sampletracker.app.utils.general.Dialogs;
import ug.co.sampletracker.app.utils.general.Validation;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/8/2018.
 */

public class DialogCustomStatement {

    private Activity activity;
    private EditText edtStatementEmail;
    private EditText edtStatementStartDate;
    private EditText edtStatementEndDate;
    private MaterialSpinner spinnerStmtReceptionType;
    private String customerReferenceNo;
    private StatementCustomizationListener listener;
    private MaterialDialog dialog;
    private TextInputLayout edtStatementStartDateWrap;
    private TextInputLayout edtStatementEndDateWrap;
    private TextInputLayout edtStatementEmailWrap;

    public DialogCustomStatement(Activity activity, String customerReferenceNo,
                                 StatementCustomizationListener listener) {
        this.activity = activity;
        this.customerReferenceNo = customerReferenceNo;
        this.listener = listener;
    }

    public void showDialog() {

        View view = activity.getLayoutInflater().inflate(R.layout.dialog_mini_statement_custom,null);
        initializeViews(view);
        populateFields();
        attachListeners();

        spinnerStmtReceptionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(spinnerStmtReceptionType == null || spinnerStmtReceptionType.getSelectedItem() == null){
                    return;
                }

                String stmtReceptionType = spinnerStmtReceptionType.getSelectedItem().toString();
                handleStmtReceiptionTypeSelection(stmtReceptionType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dialog = Dialogs.mdCustomDialog(view, activity, "Customize Statement", (dialog, which) -> handleCustomization());
        dialog.show();

    }

    private void attachListeners() {

        attachListenersToDatePickerFields();

        EditText[] editTexts = new EditText[]{edtStatementEmail,edtStatementStartDate, edtStatementEndDate};
        attachFocusChangeListener(editTexts);

    }

    private void attachFocusChangeListener(EditText[] editTexts) {

        for (EditText edtText : editTexts) {
            edtText.setOnFocusChangeListener(new CustomTextWatcher(edtText));
        }

    }

    private class  CustomTextWatcher implements View.OnFocusChangeListener {

        private View view1;
        private CustomTextWatcher(View view) {
            this.view1 = view;
        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {

            if(!hasFocus){

                switch (view1.getId()) {

                    case R.id.spinnerStatementType:

                        validStatementType(getStatementReceptionType());
                        break;

                    case R.id.edtStatementEndDate:

                        validDate(getStartDate(),edtStatementStartDateWrap);
                        break;

                    case R.id.edtStatementStartDate:

                        validDate(getEndDate(),edtStatementEndDateWrap);
                        break;

                    case R.id.edtStatementEmail:

                        validEmail(getEmail());
                        break;

                }

            }

        }

    }

    private boolean validEmail(String email) {

        if(!Validation.validateEmail(email)){
            edtStatementEmailWrap.setError(EnumErrors.INVALID_EMAIL.getErr());
            return false;
        }

        edtStatementEmailWrap.setErrorEnabled(false);
        return true;

    }

    private boolean validDate(String date, TextInputLayout edtStatementStartDateWrap) {


        if(date != null && !date.trim().isEmpty()){
            edtStatementStartDateWrap.setErrorEnabled(false);
            return true;
        }

        edtStatementStartDateWrap.setError(EnumErrors.INVALID_DATE.getErr());
        return false;

    }

    private boolean validStatementType(String receptionType) {

        if(receptionType.equalsIgnoreCase(ConstStrings.PROMPT_OPTION)){

            spinnerStmtReceptionType.setError(EnumErrors.SELECT_RECEIPTION_METHOD.getErr());
            return  false;
        }else{
            spinnerStmtReceptionType.setError(null);
            return true;
        }


    }


    private void attachListenersToDatePickerFields() {

        edtStatementStartDate.setOnClickListener(btn->Dialogs.showDatePicker(activity, (view, year, month, dayOfMonth) -> {

            GregorianCalendar calendar = new GregorianCalendar(year,month,dayOfMonth);
            String date = new DateHandler().getHumanReadableDate(calendar.getTime(), ConstStrings.DATE_FORMAT_TXN_SHORT);
            edtStatementStartDate.setText(date);

        }));

        edtStatementEndDate.setOnClickListener(btn->Dialogs.showDatePicker(activity, (view, year, month, dayOfMonth) -> {

            GregorianCalendar calendar = new GregorianCalendar(year,month,dayOfMonth);
            String date = new DateHandler().getHumanReadableDate(calendar.getTime(), ConstStrings.DATE_FORMAT_TXN_SHORT);
            edtStatementEndDate.setText(date);

        }));

    }

    private void handleCustomization() {

        StatementRequest statementRequest = new StatementRequest();

        statementRequest.setCustomerReferenceNo(customerReferenceNo);
        statementRequest.setShowOrders(Boolean.FALSE.toString().toUpperCase());
        statementRequest.setShowPayments(Boolean.TRUE.toString().toUpperCase());
        statementRequest.setStatementType(EnumStatementTypes.MONTHLY.getType());
        statementRequest.setEmail(getEmail());
        statementRequest.setStartDate(getStartDate());
        statementRequest.setEndDate(getEndDate());
        statementRequest.setTransactionNo(getTransactionNo());
        statementRequest.setReceptionType(getStatementReceptionType());

        if(!validRequest(statementRequest)){
            return;
        }

        if(dialog != null){
            dialog.dismiss();
        }

        listener.loadStatementBasedOnCustomization(statementRequest);

    }

    private boolean validRequest(StatementRequest req) {

        boolean valid = true;

        if(!validStatementType(req.getReceptionType())){
            return  false;
        }

        if(req.getReceptionType().equalsIgnoreCase(EnumStmtReceiptionTypes.EMAIL.getType()) &&
                (!validEmail(req.getEmail()))){
            valid = false;
        }

        if(!validDate(req.getStartDate(),edtStatementStartDateWrap)){
            valid = false;
        }

        if(!validDate(req.getEndDate(),edtStatementEndDateWrap)){
            valid = false;
        }

        return valid;

    }

    private String getEndDate() {

        return edtStatementEndDate.getText().toString();
       /* String receptionType = getStatementReceptionType();
        return receptionType.equalsIgnoreCase(EnumStatementTypes.MONTHLY.getMod())
                ? edtStatementEndDate.getText().toString() : "";*/
    }

    private String getStartDate() {
        return  edtStatementStartDate.getText().toString();
        /*String statementType = getStatementReceptionType();
        return statementType.equalsIgnoreCase(EnumStatementTypes.MONTHLY.getMod())
                ? edtStatementStartDate.getText().toString() : "";*/
    }

    private String getEmail() {
        String receptionType = getStatementReceptionType();
        return !receptionType.equalsIgnoreCase(ConstStrings.PROMPT_OPTION)
                ? edtStatementEmail.getText().toString() : "";
    }

    private String getTransactionNo() {

        String statementType = getStatementReceptionType();
        return statementType.equalsIgnoreCase(EnumStatementTypes.LAST_FIVE.getType())
                ? "5" : "";
    }


    private String getStatementReceptionType() {

        if(spinnerStmtReceptionType == null || spinnerStmtReceptionType.getSelectedItem() == null){
            return ConstStrings.PROMPT_OPTION;
        }

        return spinnerStmtReceptionType.getSelectedItem().toString();

    }

    private void populateFields() {

        List<String> receptionTypes = DataGenerator.getStmtReceptionTypes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, receptionTypes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStmtReceptionType.setAdapter(adapter);

        spinnerStmtReceptionType.setSelection(1);

    }

    private void initializeViews(View view) {

        spinnerStmtReceptionType = view.findViewById(R.id.spinnerStatementType);
        edtStatementEmail = view.findViewById(R.id.edtStatementEmail);
        edtStatementStartDate = view.findViewById(R.id.edtStatementStartDate);
        edtStatementEndDate = view.findViewById(R.id.edtStatementEndDate);

        edtStatementStartDateWrap = (TextInputLayout)view.findViewById(R.id.edtStatementStartDateWrap);
        edtStatementEndDateWrap = (TextInputLayout)view.findViewById(R.id.edtStatementEndDateWrap);
        edtStatementEmailWrap = (TextInputLayout)view.findViewById(R.id.edtStatementEmailWrap);

    }

    private void handleStmtReceiptionTypeSelection(String receiptionType) {

        if(receiptionType.equalsIgnoreCase(EnumStmtReceiptionTypes.EMAIL.getType())){
            toggleVisibilityEmailFields(true);
            return;
        }

        toggleVisibilityEmailFields(false);

    }

    private void toggleVisibilityEmailFields(boolean enabled) {

        if(enabled){
            edtStatementEmail.setVisibility(View.VISIBLE);
        }else{
            edtStatementEmail.setVisibility(View.GONE);
        }

    }

    public interface StatementCustomizationListener {

        void loadStatementBasedOnCustomization(StatementRequest request);

    }

}
