package ug.co.sampletracker.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.models.SelectionMenuItem;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/3/2017.
 */

public class SelectionMenuAdapter extends RecyclerView.Adapter<SelectionMenuAdapter.ViewHolder> {

    private List<SelectionMenuItem> menuItems;
    private Context context;
    private RecyclerClickListeners recyclerClickListeners;

    public SelectionMenuAdapter(List<SelectionMenuItem> menuItems, Context context) {
        this.menuItems = menuItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu_options, parent, false);
        return new ViewHolder(view,context, menuItems);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        SelectionMenuItem menuItem = menuItems.get(position);

        holder.imgIcon.setImageResource(menuItem.getIcon());
        holder.txvLabel.setText(menuItem.getLabel());

    }


    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        ImageView imgIcon;
        TextView txvLabel;


        public ViewHolder(View itemView, Context context, List<SelectionMenuItem> menuItems) {
            super(itemView);

            imgIcon = (ImageView) itemView.findViewById(R.id.imgOption);
            txvLabel = (TextView) itemView.findViewById(R.id.txvOptionLabel);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            recyclerClickListeners.clickRecyclerItemClick(view,position);

        }

        @Override
        public boolean onLongClick(View view) {

            int position = getAdapterPosition();
            recyclerClickListeners.longRecyclerItemClick(view,position);
            return true;
        }

    }

    public void setRecyclerClickListeners(RecyclerClickListeners recyclerClickListeners) {
        this.recyclerClickListeners = recyclerClickListeners;
    }
}
