package ug.co.sampletracker.app.utils.interfaces;


/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/27/2018.
 */
public interface GsonFormatter {

     String toGson(Object object);
     Object fromGson(String data);

}
