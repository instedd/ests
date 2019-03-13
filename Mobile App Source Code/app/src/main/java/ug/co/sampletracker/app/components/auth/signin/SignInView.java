package ug.co.sampletracker.app.components.auth.signin;

import ug.co.sampletracker.app.models.responses.LoginResponse;
import ug.co.sampletracker.app.models.responses.LoginStResponse;
import ug.co.sampletracker.app.utils.interfaces.CustomView;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 6/29/2018.
 */

public interface SignInView extends CustomView {

    void grantAccess(LoginResponse statusDescription);
    void grantAccess(LoginStResponse response);

}
