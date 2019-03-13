package ug.co.sampletracker.app.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lmntrx.livin.library.droidawesome.DroidAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.account.ViewAccount;
import ug.co.sampletracker.app.components.auth.password.ChangePassword;
import ug.co.sampletracker.app.components.auth.signin.SignIn;
import ug.co.sampletracker.app.components.contact.ContactUs;
import ug.co.sampletracker.app.components.dashboard.DashboardPage;
import ug.co.sampletracker.app.components.dashboard.extendedmenu.ExtendedMenu;
import ug.co.sampletracker.app.components.notification.Notifications;
import ug.co.sampletracker.app.components.receivedsamples.view.ViewReceivedSamples;
import ug.co.sampletracker.app.components.receivedsamples.menu.SampleRegistrationMenu;
import ug.co.sampletracker.app.components.receivedsamples.receivesample.SampleReceivingMenu;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.NavDrawerItem;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumDrawerItemsIdentifiers;
import ug.co.sampletracker.app.utils.general.Dialogs;

public class DrawerUtil {

    private static PreferencesDb prefDb;
    private static Activity mActivity;

    public static void getDrawer(final Activity activity, Toolbar toolbar, PreferencesDb preferencesDb){

        prefDb = preferencesDb;
        mActivity = activity;

        List<NavDrawerItem> navDrawerItems = getNavDrawerItems();
        getPersonalizedDrawer(activity,toolbar,navDrawerItems,preferencesDb);

    }

    private static List<NavDrawerItem> getNavDrawerItems() {

        NavDrawerItem dItemHome = new NavDrawerItem(R.string.module_home,
                 R.string.icon_home,//R.drawable.ic_home,
                EnumDrawerItemsIdentifiers.HOME.getId());

        int registerLabel = R.string.module_register_sample;
        NavDrawerItem dItemRegisterSample = new NavDrawerItem(registerLabel,
                R.string.icon_register_sample,//R.drawable.ic_cart,
                EnumDrawerItemsIdentifiers.REGISTER_SAMPLES.getId());

        NavDrawerItem dItemReceiveSample = new NavDrawerItem(R.string.module_receive_sample,
                R.string.icon_receive_sample,//R.drawable.ic_receipt,
                EnumDrawerItemsIdentifiers.RECEIVE_SAMPLE.getId());

        NavDrawerItem dItemViewAccount = new NavDrawerItem(R.string.module_account,
                R.string.icon_account,// R.drawable.ic_account_circle,
                EnumDrawerItemsIdentifiers.VIEW_ACCOUNT.getId());

        NavDrawerItem dItemMoKash = new NavDrawerItem(R.string.module_mo_kash,
                R.drawable.ic_mo_cash,
                EnumDrawerItemsIdentifiers.MO_KASH.getId());

        NavDrawerItem dItemContactUs = new NavDrawerItem(R.string.module_contact_us,
                R.string.icon_contact_us,//R.drawable.ic_contact_us,
                EnumDrawerItemsIdentifiers.CONTACT_US.getId());

        NavDrawerItem dItemDealers = new NavDrawerItem(R.string.module_dealers,
                R.string.icon_dealers,//R.drawable.ic_dealers,
                EnumDrawerItemsIdentifiers.DEALERS.getId());

        NavDrawerItem dItemDealerReports = new NavDrawerItem(R.string.module_dealer_reports,
                R.drawable.ic_more,
                EnumDrawerItemsIdentifiers.DEALER_REPORTS.getId());

        NavDrawerItem dItemDealerReportsInvoice = new NavDrawerItem(R.string.module_dealer_report_invoice,
                R.string.icon_invoice,//R.drawable.ic_invoice,
                EnumDrawerItemsIdentifiers.DEALER_REPORTS_INVOICE.getId());

        NavDrawerItem dItemDealerReportsTonnage = new NavDrawerItem(R.string.module_dealer_report_tonnage,
                R.string.icon_tonnage_report,//R.drawable.ic_tonnage_report,
                EnumDrawerItemsIdentifiers.DEALER_REPORTS_TONNAGE.getId());

        NavDrawerItem dItemDealerReportsDeliveryConfirm = new NavDrawerItem(R.string.module_dealer_report_delivery_note,
                R.string.icon_deliveries,//R.drawable.ic_delivery,
                EnumDrawerItemsIdentifiers.DEALER_REPORTS_DELIVERY_NOTE.getId());

        NavDrawerItem dItemNotifications = new NavDrawerItem(R.string.module_notification,
                R.string.icon_chat,
                EnumDrawerItemsIdentifiers.NOTIFICATION.getId());


        List<NavDrawerItem> navDrawerItems = new ArrayList<>();
        navDrawerItems.add(dItemHome);
        navDrawerItems.add(dItemRegisterSample);
        navDrawerItems.add(dItemReceiveSample);
        navDrawerItems.add(dItemNotifications);

        return navDrawerItems;

    }

    private static void getPersonalizedDrawer(final Activity activity, Toolbar toolbar,
                                              List<NavDrawerItem> navDrawerItems, PreferencesDb preferencesDb) {


        List<IDrawerItem> iDrawerItems = new ArrayList<>();

        for (NavDrawerItem navDrawerItem : navDrawerItems) {

            PrimaryDrawerItem drawerItemModule = new PrimaryDrawerItem()
                    .withIdentifier(navDrawerItem.getIdentifier())
                    .withName(navDrawerItem.getDisplayNameResource())
                 //   .withIcon(navDrawerItem.getIconResource());
                    .withIcon(new DroidAwesome.DrawableBuilder(activity)
                            .color(R.color.colorDashboardIcons)
                            .icon(activity.getString(navDrawerItem.getIconResource()))
                            .build());
            iDrawerItems.add(drawerItemModule);

        }

        List<SecondaryDrawerItem> navDrawerBottomSectionItems = getGeneralDrawerItems(preferencesDb);

        iDrawerItems.add(new DividerDrawerItem()); // add divider line
        iDrawerItems.addAll(navDrawerBottomSectionItems);

        IDrawerItem[] iDrawerItemsArray = iDrawerItems.toArray(new IDrawerItem[iDrawerItems.size()]);

        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withAccountHeader(getAccountHeader(activity,preferencesDb))
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        iDrawerItemsArray
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {

                    long itemIdentifier = drawerItem.getIdentifier();
                    Intent intent = getIntentForActivityToStart(itemIdentifier, activity);

                    if(intent != null){

                        NavHandler.navToActivityAnimated(view,activity,intent);

                    }

                    return false;
                })
                .build();

    }

    private static List<SecondaryDrawerItem> getGeneralDrawerItems(PreferencesDb preferencesDb) {

        List<SecondaryDrawerItem> secondaryDrawerItems = new ArrayList<>();

        SecondaryDrawerItem drawerItemChangePassword = new SecondaryDrawerItem().withIdentifier(EnumDrawerItemsIdentifiers.PASSWORD_CHANGE.getId())
                .withName("Change Password").withIcon(android.R.drawable.ic_menu_edit);


        SecondaryDrawerItem drawerItemLogout = new SecondaryDrawerItem().withIdentifier(EnumDrawerItemsIdentifiers.LOGOUT.getId())
                .withName("Log out").withIcon(R.drawable.ic_logout);

        SecondaryDrawerItem drawerItemLogin = new SecondaryDrawerItem().withIdentifier(EnumDrawerItemsIdentifiers.LOGIN.getId())
                .withName("Login").withIcon(R.drawable.ic_logout);

       if(preferencesDb.isAuthUser()){
           secondaryDrawerItems.add(drawerItemChangePassword);
           secondaryDrawerItems.add(drawerItemLogout);
       }else{
           secondaryDrawerItems.add(drawerItemLogin);
       }

       return  secondaryDrawerItems;

    }

    private static AccountHeader getAccountHeader(Activity activity, PreferencesDb preferencesDb) {

        String headerNameText = preferencesDb.customerReferenceNumber().equalsIgnoreCase(ConstStrings.PREF_DEFAULT_CUST_REF_NO)?
                                    "PROFILE" : "PROFILE: "+preferencesDb.customerReferenceNumber();

        return new AccountHeaderBuilder()
                .withActivity(activity)
               // .withHeaderBackground(R.drawable.drawer_profile_background)
                .withHeaderBackground(R.color.primary_dark)
                .withTextColor(activity.getResources().getColor(android.R.color.white))
                .addProfiles(
//                        new ProfileDrawerItem().withName(headerNameText).withIcon(R.drawable.ic_account_transparent)
                        new ProfileDrawerItem().withName(headerNameText).withIcon(
                        //        R.drawable.ic_account_white
                                new DroidAwesome.DrawableBuilder(activity)
                                        .color(R.color.white)
                                        .icon(activity.getString(R.string.icon_account))
                                        .build()
                        )
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

    }

    private static Intent getIntentForActivityToStart(long identifier, Activity activity) {

        Intent intent = null;

        if (identifier == EnumDrawerItemsIdentifiers.HOME.getId() && !(activity instanceof DashboardPage)) {
            intent = new Intent(activity, DashboardPage.class);
        }else if(identifier == EnumDrawerItemsIdentifiers.REGISTER_SAMPLES.getId() && !(activity instanceof ViewReceivedSamples)){
            intent = new Intent(activity, SampleRegistrationMenu.class);
        }else if(identifier == EnumDrawerItemsIdentifiers.VIEW_ACCOUNT.getId() && !(activity instanceof ViewAccount)){

            if(prefDb.isAuthUser() || prefDb.isAuthDealer()){
                intent = new Intent(activity, ViewAccount.class);
            }else{
                Dialogs.mdConfirm("Please login in order to view your account details",activity);
            }

        }else if(identifier == EnumDrawerItemsIdentifiers.CONTACT_US.getId() && !(activity instanceof ContactUs)){
            intent = new Intent(activity, ContactUs.class);
        }

        else if(identifier == EnumDrawerItemsIdentifiers.NOTIFICATION.getId() && !(activity instanceof Notifications)){
            intent = new Intent(activity, Notifications.class);
        }

        else if(identifier == EnumDrawerItemsIdentifiers.RECEIVE_SAMPLE.getId() && !(activity instanceof SampleReceivingMenu)){

            intent = new Intent(activity, SampleReceivingMenu.class);

        }
        else if(identifier == EnumDrawerItemsIdentifiers.DEALER_REPORTS.getId()){

            intent = new Intent(activity, ExtendedMenu.class);

        } else if(identifier == EnumDrawerItemsIdentifiers.PASSWORD_CHANGE.getId()){

            intent = new Intent(activity, ChangePassword.class);

        }  else if(identifier == EnumDrawerItemsIdentifiers.LOGOUT.getId()){

            new PreferencesDb(activity).clear();
            intent = new Intent(activity, SignIn.class);

        }else if(identifier == EnumDrawerItemsIdentifiers.LOGIN.getId()){
            intent = new Intent(activity, SignIn.class);
        }

        return intent;

    }
}