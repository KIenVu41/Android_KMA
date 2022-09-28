package com.kma.bai2.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kma.bai2.R;
import com.kma.bai2.model.CallLog;
import com.kma.bai2.model.Contact;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.CallHolder> {
    List<CallLog> callLogs;

    public CallAdapter(List<CallLog> callLogs) {
        this.callLogs = callLogs;
    }

    @NonNull
    @Override
    public CallHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_item, parent,false);
        return new CallHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallHolder holder, int position) {
        CallLog callLog = callLogs.get(position);
        holder.name.setText("Name: " + callLog.getName());
        long unix_seconds = Long.parseLong(callLog.getDate());
        Date date = new Date(unix_seconds * 1000L);
        DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz");
        String java_date = df.format(date);
        holder.date.setText("Date: " + java_date);
        holder.duration.setText("Duration: " + callLog.getDuration());
        holder.type.setText("Type: " + callLog.getType());
    }

    @Override
    public int getItemCount() {
        if (callLogs != null) {
            return callLogs.size();
        }
        return 0;
    }

    class CallHolder extends RecyclerView.ViewHolder {
        TextView name, date, duration, type;

        public CallHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            duration = itemView.findViewById(R.id.duration);
            type = itemView.findViewById(R.id.type);
        }
    }
}
