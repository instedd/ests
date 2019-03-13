package ug.co.sampletracker.app.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;

import ug.co.sampletracker.app.R;
import ug.co.sampletracker.app.models.StNotification;
import ug.co.sampletracker.app.utils.interfaces.RecyclerClickListeners;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/3/2017.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    private List<StNotification> notificationList;
    private Context context;
    private RecyclerClickListeners recyclerClickListeners;

    public NotificationsAdapter(List<StNotification> notificationList, Context context) {
        this.notificationList = notificationList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_feedback_message, parent, false);
        return new ViewHolder(view,context, notificationList);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        StNotification notification = notificationList.get(position);

        String message =  StringEscapeUtils.unescapeHtml4(notification.getMessage());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.txvMessage.setText(Html.fromHtml(message, Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.txvMessage.setText(Html.fromHtml(message));
        }

      //  holder.txvMessage.setText(message);
        holder.txvName.setText(notification.getCreated_by());
        holder.txvDate.setText(notification.getDate_created());

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView txvMessage, txvName, txvDate, txvPhone, txvEmail;

        public ViewHolder(View itemView, Context context, List<StNotification> locationList) {
            super(itemView);

            txvMessage = itemView.findViewById(R.id.txvMessage);
            txvName = itemView.findViewById(R.id.txvName);
            txvDate = itemView.findViewById(R.id.txvDate);

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
