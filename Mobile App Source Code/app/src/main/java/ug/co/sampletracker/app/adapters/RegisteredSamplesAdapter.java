package ug.co.sampletracker.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.database.DataManager;
import ug.co.sampletracker.app.models.StRegisteredSamplePojo;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/3/2017.
 */

public class RegisteredSamplesAdapter extends RecyclerView.Adapter<RegisteredSamplesAdapter.ViewHolder> {

    private List<StRegisteredSamplePojo> samplePojos;
    private Context context;
    private RecyclerClickListeners recyclerClickListeners;

    public RegisteredSamplesAdapter(List<StRegisteredSamplePojo> samplePojos, Context context) {
        this.samplePojos = samplePojos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mini_statement, parent, false);
        return new ViewHolder(view,context, samplePojos);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        StRegisteredSamplePojo sample = samplePojos.get(position);

        String sampleId = sample.sample_id;
        String destination = sample.destination_id;
        String sampleTakingDate = sample.initialSampleDate;
        String receivedStatus = sample.received_status;
        String healthFacility = sample.facility_code_id;
        String suspectedDisease = sample.disease_id;
        String specimen = sample.sample_type_id;
        String transporter = DataManager.findTransporterNameByTransporterId(sample.transporter);
        String expectedDateToReach = sample.actualFinalDate;

        holder.txvSampleId.setText(sampleId);
        holder.txvDestination.setText(destination);
        holder.txvSampleTakingDate.setText(sampleTakingDate);
        holder.txvReceivedStatus.setText(receivedStatus);
        holder.txvHealthFacility.setText(healthFacility);
        holder.txvSuspectedDisease.setText(suspectedDisease);
        holder.txvSpecimen.setText(specimen);
        holder.txvTransporter.setText(transporter);
        holder.txvExpectedDateToReach.setText(expectedDateToReach);

    }


    @Override
    public int getItemCount() {
        return samplePojos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView txvHealthFacility, txvSuspectedDisease, txvSpecimen,txvSampleId,
                txvDestination, txvReceivedStatus, txvTransporter, txvSampleTakingDate, txvExpectedDateToReach;

        public ViewHolder(View itemView, Context context, List<StRegisteredSamplePojo> samplePojos) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            txvHealthFacility = itemView.findViewById(R.id.txvHealthFacility);
            txvSuspectedDisease = itemView.findViewById(R.id.txvSuspectedDisease);
            txvSpecimen = itemView.findViewById(R.id.txvSpecimen);
            txvDestination = itemView.findViewById(R.id.txvDestination);
            txvReceivedStatus = itemView.findViewById(R.id.txvReceivedStatus);
            txvTransporter = itemView.findViewById(R.id.txvTransporter);
            txvSampleTakingDate = itemView.findViewById(R.id.txvSampleTakingDate);
            txvExpectedDateToReach = itemView.findViewById(R.id.txvExpectedDateToReach);
            txvSampleId = itemView.findViewById(R.id.txvSampleId);

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
