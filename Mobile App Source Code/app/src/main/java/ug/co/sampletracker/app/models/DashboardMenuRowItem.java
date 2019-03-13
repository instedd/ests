package ug.co.sampletracker.app.models;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 8/22/2018.
 */
public class DashboardMenuRowItem {

    private int icon;
    private String label;
    /**
     * Class to navigate to on pressing this option on the Dashboard
     */
    private Class<?> aClass;

    public DashboardMenuRowItem(int icon, String label) {
        this.icon = icon;
        this.label = label;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
