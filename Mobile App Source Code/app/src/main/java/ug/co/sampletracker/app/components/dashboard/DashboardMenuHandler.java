package ug.co.sampletracker.app.components.dashboard;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.components.account.ViewAccount;
import ug.co.sampletracker.app.components.contact.ContactUs;
import ug.co.sampletracker.app.components.notification.Notifications;
import ug.co.sampletracker.app.components.receivedsamples.menu.SampleRegistrationMenu;
import ug.co.sampletracker.app.components.receivedsamples.receivesample.SampleReceivingMenu;
import ug.co.sampletracker.app.database.DataManager;
import ug.co.sampletracker.app.models.DashboardMenuRow;
import ug.co.sampletracker.app.models.DashboardMenuRowItem;
import ug.co.sampletracker.app.models.SelectionMenuItem;
import ug.co.sampletracker.app.models.StNotification;
import ug.co.sampletracker.app.utils.constants.EnumExtendMenuLabels;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/9/2018.
 */

public class DashboardMenuHandler {

    public static final String NOTIFICATIONS = "Notifications";
    public static final String RECEIVE_SAMPLE = "Receive Sample";
    public static final String VIEW_RECEIVED_SAMPLES = "View Received Samples";
    public static final String REGISTER_SAMPLE = "Register Sample";
    public static final String VIEW_REGISTERED_SAMPLES = "View Registered Samples";
    private static final String ACCOUNT = "My Account";
    private static final String CONTACT_US = "Contact Us";


    public static List<DashboardMenuRow> getCustomDashboardMenu(boolean authUser, boolean authDealer) {

        List<DashboardMenuRow> menuRows = new ArrayList<>();
        List<DashboardMenuRowItem> menuRowsItems = new ArrayList<>();


        DashboardMenuRowItem menuItemRegisterSample = new DashboardMenuRowItem(R.string.icon_register_sample, REGISTER_SAMPLE);
        DashboardMenuRowItem menuItemReceiveSample = new DashboardMenuRowItem(R.string.icon_receive_sample, RECEIVE_SAMPLE);
        DashboardMenuRowItem menuItemAccount = new DashboardMenuRowItem(R.string.icon_account,ACCOUNT);
        DashboardMenuRowItem menuItemContact = new DashboardMenuRowItem(R.string.icon_contact_us,CONTACT_US);

        List<StNotification> notifications = DataManager.appData.getNotifications();
        int notificationCount =notifications == null ? 0 : notifications.size();
        DashboardMenuRowItem menuItemNotifications = new DashboardMenuRowItem(R.string.icon_chat,NOTIFICATIONS+"("+ notificationCount +")");




        /*
         1. Sample registration menu
         */
        menuItemRegisterSample.setaClass(SampleRegistrationMenu.class);
        menuRowsItems.add(menuItemRegisterSample);

        /*
        2. Sample Receiving menu
        * */
        menuItemReceiveSample.setaClass(SampleReceivingMenu.class);
        menuRowsItems.add(menuItemReceiveSample);


        /*
          My Account Module Menu Item
         */
        if(authUser ){
            menuItemAccount.setaClass(ViewAccount.class);
            menuRowsItems.add(menuItemAccount);
        }


        if(!authDealer){
            menuItemContact.setaClass(ContactUs.class);
            menuRowsItems.add(menuItemContact);
        }

     /*
         Notification
         */
        menuItemNotifications.setaClass(Notifications.class);
        menuRowsItems.add(menuItemNotifications);

        menuRows = generateMenuRowList(menuRowsItems);

        return menuRows;

    }

    private static List<DashboardMenuRow> generateMenuRowList(List<DashboardMenuRowItem> menuRowsItems) {

        List<DashboardMenuRow> menuRows = new ArrayList<>();
        int countItems = menuRowsItems.size();
        if(countItems == 0){
            return menuRows;
        }

        int lengthExcess = countItems + 1;
        for (int index = 0; index < lengthExcess; index+=2) {

            DashboardMenuRow menuRow = new DashboardMenuRow();

            if(index < countItems){
                DashboardMenuRowItem item1 = menuRowsItems.get(index);
                menuRow.setLeftMenuItem(item1);
            }

            if(index+1 < countItems){
                DashboardMenuRowItem item2 = menuRowsItems.get(index+1);
                menuRow.setRightMenuItem(item2);
            }

            if(menuRow.getLeftMenuItem() != null){
                menuRows.add(menuRow);
            }

        }

        return menuRows;

    }


    public static List<SelectionMenuItem> getExtendedMenuItems() {

        List<SelectionMenuItem> menuItems = new ArrayList<>();
        SelectionMenuItem itemTonnageReport =  new SelectionMenuItem(EnumExtendMenuLabels.TONNAGE_REPORT.getItem(),R.drawable.ic_add_option);
        SelectionMenuItem itemInvoiceInq =  new SelectionMenuItem(EnumExtendMenuLabels.INVOICE_INQUIRY.getItem(),R.drawable.ic_add_option);
        SelectionMenuItem itemDeliveryConfirmation =  new SelectionMenuItem(EnumExtendMenuLabels.DELIVERIES.getItem(),R.drawable.ic_add_option);

        menuItems.add(itemTonnageReport);
        menuItems.add(itemInvoiceInq);
        menuItems.add(itemDeliveryConfirmation);

        return menuItems;

    }
}
