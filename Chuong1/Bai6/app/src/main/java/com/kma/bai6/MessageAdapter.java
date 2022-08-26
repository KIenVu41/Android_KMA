package com.kma.bai6;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(@NonNull Context context, List<Message> messages) {
        super(context,0, messages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Message message = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }
        // Lookup view for data population
        TextView tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
        TextView tvMess = (TextView) convertView.findViewById(R.id.tvMess);
        // Populate the data into the template view using the data object
        tvAddress.setText(message.getAddress());
        tvMess.setText(message.getMessage());
        // Return the completed view to render on screen
        return convertView;
    }
}
