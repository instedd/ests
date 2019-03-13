package ug.co.sampletracker.app.utils.interfaces;

import android.view.View;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/6/2017.
 */

public interface RecyclerClickListeners {
    /**
     * @param view - the item long clicked
     * @param position - the position of the item in the list
     * Method handles a long click event from a Recyclerview item
     */
    void longRecyclerItemClick(View view, int position);

    /**
     * @param view - the item clicked
     * @param position - the position of the item in the list
     * Method handles a click event from a Recyclerview item
     */
    void clickRecyclerItemClick(View view, int position);
}
