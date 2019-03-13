package ug.co.sampletracker.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lmntrx.livin.library.droidawesome.DroidAwesome;

import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.models.DashboardMenuRowItem;
import ug.co.sampletracker.app.models.DashboardMenuRow;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/3/2017.
 */

public class DashboardMenuAdapter extends RecyclerView.Adapter<DashboardMenuAdapter.ViewHolder> {

    private List<DashboardMenuRow> menuRows;
    private Context context;
    private RecyclerClickListeners recyclerClickListeners;

    public DashboardMenuAdapter(List<DashboardMenuRow> menuRows, Context context) {
        this.menuRows = menuRows;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_menu_row, parent, false);
        return new ViewHolder(view,context, menuRows);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DashboardMenuRow menuRow = menuRows.get(position);

        if(position == menuRows.size()-1){
            menuRow.setLastRow(true);
        }

        if(menuRow.getLastRow()){
            holder.bottomDivider.setVisibility(View.GONE);
        }else{
            holder.bottomDivider.setVisibility(View.VISIBLE);
        }

        showLeftMenuRowOption(holder,menuRow.getLeftMenuItem());
        showRightMenuRowOption(holder,menuRow.getRightMenuItem());

    }

    private void showRightMenuRowOption(ViewHolder holder, DashboardMenuRowItem rightMenuItem) {

        if(rightMenuItem == null){
            holder.btnRightOption.setVisibility(View.INVISIBLE);
            return;
        }

        holder.btnRightOption.setVisibility(View.VISIBLE);
        holder.txvRightOption.setText(rightMenuItem.getLabel());
//        holder.imgRightOption.setImageResource(rightMenuItem.getIcon());

        holder.imgRightOption.setImageDrawable(
                new DroidAwesome.DrawableBuilder(context)
                        .color(R.color.colorPrimary)//.color(R.color.colorDashboardIcons) //colorRes
                        .size(28) //dimension float (sp)
                        .icon(context.getString(rightMenuItem.getIcon())) //icon
                        .build());

    }

    private void showLeftMenuRowOption(ViewHolder holder, DashboardMenuRowItem leftMenuItem) {

        if(leftMenuItem == null){
            holder.btnLeftOption.setVisibility(View.INVISIBLE);
            return;
        }

        holder.btnLeftOption.setVisibility(View.VISIBLE);
        holder.txvLeftOption.setText(leftMenuItem.getLabel());
      //  holder.imgLeftOption.setImageResource(leftMenuItem.getIcon());

        holder.imgLeftOption.setImageDrawable(
                new DroidAwesome.DrawableBuilder(context)
                        .color(R.color.colorPrimary)//.color(R.color.colorDashboardIcons) //colorRes
                        .size(28) //dimension float (sp)
                        .icon(context.getString(leftMenuItem.getIcon())) //icon
                        .build()
        );

        //holder.awesomeLeft.setT

    }

    @Override
    public int getItemCount() {
        return menuRows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        RelativeLayout btnLeftOption, btnRightOption;

        ImageView imgLeftOption, imgRightOption;
        TextView txvLeftOption, txvRightOption;
        View bottomDivider;

      //  DroidAwesomeImageView awesomeLeft;


        public ViewHolder(View itemView, Context context, List<DashboardMenuRow> dealers) {
            super(itemView);

           // itemView.setOnClickListener(this);
           // itemView.setOnLongClickListener(this);

            btnLeftOption = (RelativeLayout)itemView.findViewById(R.id.btnLeftOption);
            btnRightOption = (RelativeLayout)itemView.findViewById(R.id.btnRightOption);

            imgLeftOption = (ImageView)itemView.findViewById(R.id.circleImgViewLeft);
            imgRightOption = (ImageView)itemView.findViewById(R.id.circleImgViewRight);

            txvLeftOption = (TextView) itemView.findViewById(R.id.txvLeftOption);
            txvRightOption = (TextView) itemView.findViewById(R.id.txvRightOption);

            bottomDivider = (View) itemView.findViewById(R.id.viewDashboardMenuRowDividerBottom);

         //  awesomeLeft = itemView.findViewById(R.id.awesomeLeft);

            btnLeftOption.setOnClickListener(this);
            btnRightOption.setOnClickListener(this);

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
