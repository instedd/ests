package ug.co.sampletracker.app.models;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 8/22/2018.
 */
public class DashboardMenuRow {

    DashboardMenuRowItem leftMenuItem;
    DashboardMenuRowItem rightMenuItem;
    private Boolean isLastRow = false;

    public DashboardMenuRowItem getLeftMenuItem() {
        return leftMenuItem;
    }

    public void setLeftMenuItem(DashboardMenuRowItem leftMenuItem) {
        this.leftMenuItem = leftMenuItem;
    }

    public DashboardMenuRowItem getRightMenuItem() {
        return rightMenuItem;
    }

    public void setRightMenuItem(DashboardMenuRowItem rightMenuItem) {
        this.rightMenuItem = rightMenuItem;
    }

    public Boolean getLastRow() {
        return isLastRow;
    }

    public void setLastRow(Boolean lastRow) {
        isLastRow = lastRow;
    }
}
