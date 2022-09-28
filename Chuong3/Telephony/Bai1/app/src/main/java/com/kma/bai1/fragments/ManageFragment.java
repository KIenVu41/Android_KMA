package com.kma.bai1.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kma.bai1.Message;
import com.kma.bai1.R;

import java.util.List;

public class ManageFragment extends Fragment {
    List<Message> messageList;
    RecyclerView recyclerView;
    SmsAdapter smsAdapter;

    public ManageFragment(List<Message> messageList) {
        // Required empty public constructor
        this.messageList = messageList;
    }

//    public static ManageFragment newInstance(String param1, String param2) {
//        ManageFragment fragment = new ManageFragment();
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.smsView);
        smsAdapter = new SmsAdapter(messageList);
        recyclerView.setAdapter(smsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}