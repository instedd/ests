package ug.co.sampletracker.app.models;

import android.net.Uri;

import java.io.File;

public class FormData {

    private Uri uri;
    private File file;

    public FormData(Uri uri, File file) {
        this.uri = uri;
        this.file = file;
    }

    public Uri getUri() {
        return uri;
    }

    private void setUri(Uri uri) {
        this.uri = uri;
    }

    public File getFile() {
        return file;
    }

    private void setFile(File file) {
        this.file = file;
    }
}
