package com.kma.bai1.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kma.bai1.Message;
import com.kma.bai1.R;

import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.SmsHolder>{
    List<Message> messageList;

    public SmsAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public SmsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_item, parent,false);
        return new SmsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmsHolder holder, int position) {
        Message message = messageList.get(position);
        holder.from.setText("From: " + message.getFrom());
        holder.to.setText("To: " + message.getTo());
        holder.content.setText("Body: " + message.getContent());
        holder.type.setText("Type: " + message.getType());

    }

    @Override
    public int getItemCount() {
        if (messageList != null) {
            return messageList.size();
        }
        return 0;
    }

    class SmsHolder extends RecyclerView.ViewHolder {
        TextView from, to, content, type;

        public SmsHolder(@NonNull View itemView) {
            super(itemView);

            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            content = itemView.findViewById(R.id.body);
            type = itemView.findViewById(R.id.type);
        }
    }
}
