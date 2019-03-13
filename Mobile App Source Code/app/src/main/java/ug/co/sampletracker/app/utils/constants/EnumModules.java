package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/26/2018.
 */

public enum EnumModules {

    MINI_STATEMENT_UNREF("MINI_STATEMENT_UNREF"),
    ORDER_HISTORY_UNREF("ORDER_HISTORY_UNREF");
    String mod;

    EnumModules(String mod) {
        this.mod = mod;
    }

    public String getMod() {
        return mod;
    }

}
