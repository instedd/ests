package ug.co.sampletracker.app.components.auth.login;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.utils.NavHandler;
import ug.co.sampletracker.app.utils.general.Dialogs;

public class Login extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Activity mActivity;
    private Button btnLaunchDashboard;
    private Button btnLaunchReferenceVerification;
    private PreferencesDb preferencesDb;

    public Login() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {

        setUpToolbar(view);
        initializeFields(view);
        initializeDependencies();
        attachListeners();
        customizeLookAndFeel();
        Log.e("Login Called","Testing call");

    }

    private void customizeLookAndFeel() {

        PreferencesDb preferencesDb = new PreferencesDb(mActivity);
        if(preferencesDb.isSignedIn()){
            btnLaunchDashboard.setVisibility(View.GONE);
            return;
        }

        btnLaunchDashboard.setVisibility(View.VISIBLE);

    }

    private void initializeDependencies() {

        preferencesDb = new PreferencesDb(mActivity);

    }

    private void setUpToolbar(View view) {

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(mActivity.getResources().getString(R.string.m_service_name));

        if (getActivity() != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        }

    }

    private void attachListeners() {
        btnLaunchDashboard.setOnClickListener(btn->{
            mListener.launchDashboard();
        });

        btnLaunchReferenceVerification.setOnClickListener(btn->{

            if(new PreferencesDb(mActivity).isSignedIn()){
                mListener.launchDashboard();
                return;
            }
            mListener.launchReferenceVerification();

        });

    }

    private void initializeFields(View view) {
        btnLaunchDashboard = view.findViewById(R.id.btnLaunchDashboard);
        btnLaunchReferenceVerification = view.findViewById(R.id.btnLaunchReferenceVerification);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            mActivity = (Activity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
        void launchDashboard();
        void launchReferenceVerification();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();

        if(preferencesDb.isSignedIn()){
            mActivity.getMenuInflater().inflate(R.menu.menu_main_logged_in, menu);
            return;
        }

        mActivity.getMenuInflater().inflate(R.menu.menu_dialog_title_only, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logout) {

            showLogoutConfirmationDialog();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void showLogoutConfirmationDialog() {

        Dialogs.mdPrompt(mActivity, "Confirm Action", "Are you sure you want to logout?",
                (dialog, which) -> new NavHandler(mActivity).logout());

    }

}
