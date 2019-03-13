package ug.co.sampletracker.app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.database.DataManager;
import ug.co.sampletracker.app.models.Order;
import ug.co.sampletracker.app.models.StReceivedSample;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/3/2017.
 */

public class ReceivedSamplesAdapter extends RecyclerView.Adapter<ReceivedSamplesAdapter.ViewHolder> {

    private List<StReceivedSample> samples;
    private Context context;
    private RecyclerClickListeners recyclerClickListeners;

    public ReceivedSamplesAdapter(List<StReceivedSample> samples, Context context) {
        this.samples = samples;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order, parent, false);
        return new ViewHolder(view,context, samples);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        StReceivedSample item = samples.get(position);

        holder.txvDateReceived.setText(item.getDate_received());

        holder.txvSampleId.setText(item.getSample_id());
        holder.txvIsAtDest.setText(item.getIs_destination());

        String delivered_by = item.getDelivered_by();

        holder.txvDeliveredBy.setText(DataManager.findTransporterNameByTransporterId(delivered_by));
        holder.txvDestination.setText(DataManager.findDestNameById(item.getDestination_id()));
        holder.txvStatus.setText(item.getSample_status());

    }

    @NonNull
    private String getQuantity(Order order) {

        if(order.getQuantity() == null || order.getQuantity().trim().isEmpty()){
            return ConstStrings.NOT_AVAILABLE;
        }else{
            return order.getQuantity() + " " + order.getPackaging();
        }

    }

    private String getItemName(Order order) {

        if(order.getItemName() == null || order.getItemName().trim().isEmpty()){
            return ConstStrings.NOT_AVAILABLE;
        }else{
            return order.getItemName();
        }

    }

    private int getStatusBackgroundDrawableId(String status) {

        if(status.equalsIgnoreCase(ConstStrings.STATUS_SUCCESS) || status.equalsIgnoreCase(ConstStrings.STATUS_BOOKED)){
            return R.color.md_green_500;
        }else if(status.equalsIgnoreCase(ConstStrings.STATUS_FAILED)){
            return R.color.md_red_500;
        }else{
            return R.color.md_blue_500;
        }

        /*if(status.equalsIgnoreCase(ConstStrings.STATUS_SUCCESS) || status.equalsIgnoreCase(ConstStrings.STATUS_BOOKED)){
            return R.drawable.badge_success;
        }else if(status.equalsIgnoreCase(ConstStrings.STATUS_FAILED)){
            return R.drawable.badge_failed;
        }else{
            return R.drawable.badge_pending;
        }*/

    }

    @Override
    public int getItemCount() {
        return samples.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView txvSampleId, txvDestination, txvIsAtDest, txvDeliveredBy, txvDateReceived, txvStatus;

        public ViewHolder(View itemView, Context context, List<StReceivedSample> orders) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            txvSampleId = itemView.findViewById(R.id.txvSampleId);
            txvDestination = itemView.findViewById(R.id.txvDestination);
            txvIsAtDest = itemView.findViewById(R.id.txvIsAtDest);
            txvDeliveredBy = itemView.findViewById(R.id.DeliveredBy);
            txvDateReceived = itemView.findViewById(R.id.txvDateReceived);
            txvStatus = itemView.findViewById(R.id.txvStatus);

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
