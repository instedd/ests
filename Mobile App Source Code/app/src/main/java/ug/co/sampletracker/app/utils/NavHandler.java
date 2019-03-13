package ug.co.sampletracker.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import spencerstudios.com.bungeelib.Bungee;
import ug.co.sampletracker.app.components.auth.signin.SignIn;
import ug.co.sampletracker.app.database.PreferencesDb;

/**
 * Created by XT on 10/6/2015.
 */
public class NavHandler {

    private Context contextObject;

    private NavHandler()
    {

    }

    public NavHandler(Context context)
    {
        this.contextObject = context;
    }

    public static void navToActivityAnimated(View view, Context context, Class<?> cls) {

        view.getContext().startActivity(new Intent(context,cls));
//        animatePageNavigationFade(context);

    }

    private static void animatePageNavigation(Context context) {

        Bungee.slideLeft(context);

        //        Bungee.split(context);
//        Bungee.shrink(content);
//        Bungee.inAndOut(context);
        //       Bungee.swipeLeft(context);
//          Bungee.swipeRight(context);
//        Bungee.slideLeft(context);
//        Bungee.slideRight(context);
//        Bungee.slideDown(context);
//        Bungee.slideUp(context);
//        Bungee.fade(context);
//        Bungee.zoom(context);
//        Bungee.windmill(context);
//        Bungee.diagonal(context);
//        Bungee.spin(context);

    }

    private static void animatePageNavigationFade(Context context) {
        Bungee.fade(context);
    }

    public static void navToActivityAnimated(View view,Context context, Intent intent) {

        view.getContext().startActivity(intent);
        animatePageNavigation(context);

    }

    public void navigateToActivity(Activity current_class,Class<?> cls)
    {
       // current_class.finish();
        Intent intent = new Intent(contextObject,cls);
        current_class.startActivity(intent);
    }

    public void navigateToActivity(Context current_context,Class<?> cls) {

        Intent intent = new Intent(contextObject,cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        current_context.startActivity(intent);
    }

    public void navigateToActivityWithBundle(Activity current_class,Class<?> cls,Bundle bundle) {
        Intent intent = new Intent(contextObject,cls);
        intent.putExtras(bundle);
        current_class.startActivity(intent);
    }

    public void goToLoginPage(Context context) {

      Intent i = new Intent(context, SignIn.class) ;
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(i);

     //   Bungee.card(context);

    }

    public void logout() {

        PreferencesDb preferencesDb = new PreferencesDb(contextObject);
        preferencesDb.clear();
        goToLoginPage(contextObject);
    }

    public static void navToActivityAnimated(Context context, Class<?> cls) {

        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
        animatePageNavigation(context);

        //        Bungee.split(context);
//        Bungee.shrink(content);
//        Bungee.inAndOut(context);
 //       Bungee.swipeLeft(context);
//          Bungee.swipeRight(context);
//        Bungee.slideLeft(context);
//        Bungee.slideRight(context);
//        Bungee.slideDown(context);
//        Bungee.slideUp(context);
//        Bungee.fade(context);
//        Bungee.zoom(context);
//        Bungee.windmill(context);
//        Bungee.diagonal(context);
//        Bungee.spin(context);

    }

    public static void navToActivityAnimatedWithBundle(Context context, Class<?> cls, Bundle bundle) {

        Intent intent = new Intent(context,cls);
        intent.putExtras(bundle);
        context.startActivity(intent);
        animatePageNavigation(context);

    }

}