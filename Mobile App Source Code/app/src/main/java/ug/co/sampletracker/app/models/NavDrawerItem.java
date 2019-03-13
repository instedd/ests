package ug.co.sampletracker.app.models;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/9/2018.
 */

public class NavDrawerItem {

    private int displayNameResource;
    private int iconResource;
    private int identifier;

    public NavDrawerItem(int displayNameResource, int iconResource, int identifier) {
        this.displayNameResource = displayNameResource;
        this.iconResource = iconResource;
        this.identifier = identifier;
    }

    public int getDisplayNameResource() {
        return displayNameResource;
    }

    public void setDisplayNameResource(int displayNameResource) {
        this.displayNameResource = displayNameResource;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}
