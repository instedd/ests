package ug.co.sampletracker.app.components.auth.reset;

import ug.co.sampletracker.app.utils.interfaces.CustomView;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/20/2018.
 */
public interface ResetPasswordView extends CustomView {
    void displayOTPDialog(String phone);
}
