package ug.co.sampletracker.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.models.OfficeLocation;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/3/2017.
 */

public class OfficesAdapter extends RecyclerView.Adapter<OfficesAdapter.ViewHolder> {

    private List<OfficeLocation> locationList;
    private Context context;
    private RecyclerClickListeners recyclerClickListeners;

    public OfficesAdapter(List<OfficeLocation> locationList, Context context) {
        this.locationList = locationList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_office, parent, false);
        return new ViewHolder(view,context, locationList);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        OfficeLocation officeLocation = locationList.get(position);

        String officeName = officeLocation.getOfficeName();
        String physicalLocation = officeLocation.getPhysicalAddress();
        String email = officeLocation.getEmail();
        String poBox = officeLocation.getPoBox();
        String phone = officeLocation.getPhone();


        holder.txvOfficeName.setText(officeName);
        holder.txvPhysicalAddress.setText(physicalLocation);
        holder.txvPoBox.setText(poBox);
        holder.txvPhone.setText(phone);
        holder.txvEmail.setText(email);

    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView txvOfficeName, txvPhysicalAddress, txvPoBox, txvPhone, txvEmail;

        public ViewHolder(View itemView, Context context, List<OfficeLocation> locationList) {
            super(itemView);

           // itemView.setOnClickListener(this);
           // itemView.setOnLongClickListener(this);

            txvOfficeName = itemView.findViewById(R.id.txvOfficeName);
            txvPhysicalAddress = itemView.findViewById(R.id.txvPhysicalLocation);
            txvPoBox = itemView.findViewById(R.id.txvPoBox);
            txvPhone = itemView.findViewById(R.id.txvPhone);
            txvEmail = itemView.findViewById(R.id.txvEmail);

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
