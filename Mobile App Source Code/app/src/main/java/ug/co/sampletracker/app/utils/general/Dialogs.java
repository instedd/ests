package ug.co.sampletracker.app.utils.general;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Calendar;
import java.util.List;

import ug.co.sampletracker.app.R;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/3/2017.
 */

public class Dialogs {

    public static void toast(Context context, String message){
        if(context != null){

            Snackbar snackbar = Snackbar
                    .make(((Activity) context).findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
            snackbar.show();


        }else{
            Log.e("Dialogs","Dialogs#Toast Null Context Provided");
        }
    }


    public static void mdSingleChoiceSelectionDialog(Context context, String title, List<String> choices,
                                                     MaterialDialog.ListCallbackSingleChoice listCallbackSingleChoice){

        new MaterialDialog.Builder(context)
                .title(title)
                .items(choices)
                .itemsCallbackSingleChoice(-1, listCallbackSingleChoice)
                .positiveText(context.getResources().getString(R.string.choose))
                .show();

    }

    public static void mdPrompt(Context context, String title, String message,
                                MaterialDialog.SingleButtonCallback positiveButtonClickListener) {

        if(context == null){
            Log.e("Dialogs","Dialogs#dialog Null Context Provided");
            return;
        }

        new MaterialDialog.Builder(context)
                .title(title)
                .content(message)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .onPositive(positiveButtonClickListener)
                .onNegative((dialog, which) -> {
                    // TODO
                })
                .show();

    }

    public static void mdPromptChoiceRequired(Context context, String title, String message,
                                MaterialDialog.SingleButtonCallback positiveBtnClickListener, MaterialDialog.SingleButtonCallback negativeBtnClickListener) {

        if(context == null){
            Log.e("Dialogs","Dialogs#dialog Null Context Provided");
            return;
        }

        new MaterialDialog.Builder(context)
                .title(title)
                .content(message)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .onPositive(positiveBtnClickListener)
                .onNegative(negativeBtnClickListener)
                .cancelable(false)
                .show();

    }

    public static void mdPromptSingleBtn(Context context, String title, String message,
                                MaterialDialog.SingleButtonCallback positiveButtonClickListener) {

        if(context == null){
            Log.e("Dialogs","Dialogs#dialog Null Context Provided");
            return;
        }

        new MaterialDialog.Builder(context)
                .title(title)
                .content(message)
                .positiveText(R.string.okay)
                .onPositive(positiveButtonClickListener)
                .show();

    }


    public static MaterialDialog mdProgressDialog(Context context, String title){

        MaterialDialog dialog = new MaterialDialog.Builder(context)
               // .title(title)
              //  .content(R.string.please_wait)
                .content(WordUtils.capitalize(title+"....".toLowerCase()))
                .progress(true, 0)
                .cancelable(false)
                .build();

        return dialog;

    }



    public static void mdConfirm(String message, Context context){

        new MaterialDialog.Builder(context)
                .title(R.string.app_name)
                .content(message)
                .negativeText(R.string.okay)
                .show();

    }

    public static void showDatePicker(Context context, DatePickerDialog.OnDateSetListener dateSetListener){

        final Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, dateSetListener, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    @NonNull
    public static MaterialDialog mdCustomDialog(View customView, Context context, String title,
                                                MaterialDialog.SingleButtonCallback positiveBtnClickListener) {

        return
                new MaterialDialog.Builder(context)
                .title(title)
                .customView(customView, true)
                .positiveText(R.string.label_confirm)
                .negativeText(R.string.label_cancel)
                .autoDismiss(false)
                .onPositive(positiveBtnClickListener)
                .onNegative((dialog, which) -> dialog.dismiss())
                .negativeColor(context.getResources().getColor(R.color.md_red_500))
                .positiveColor(context.getResources().getColor(R.color.colorGreen))
                .cancelable(false)
                .build();

    }

    @NonNull
    public static MaterialDialog mdCustomDialogNoButtons(View customView, Context context, String title) {

        return
                new MaterialDialog.Builder(context)
                        .title(title)
                        .customView(customView, true)
                        .autoDismiss(false)
                        .cancelable(false)
                        .build();

    }

    public static void showDateRangePicker(FragmentManager activity,
                                           SmoothDateRangePickerFragment.OnDateRangeSetListener rangePickerLister){

        SmoothDateRangePickerFragment rangePickerFragment = SmoothDateRangePickerFragment.newInstance(rangePickerLister);
        rangePickerFragment.show(activity,"SmoothDateRangePicker");

    }

    public static void fileChooserDialog(Context context, int requestCode){

        new MaterialFilePicker()
                .withActivity((Activity) context)
                .withRequestCode(requestCode)
               // .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
                .withFilterDirectories(true) // Set directories filterable (false by default)
                .withHiddenFiles(true) // Show hidden files and folders
                .start();

    }


}
