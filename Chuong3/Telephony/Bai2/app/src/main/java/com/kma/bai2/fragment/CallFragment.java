package com.kma.bai2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kma.bai2.R;
import com.kma.bai2.model.CallLog;

import java.util.List;

public class CallFragment extends Fragment {
    List<CallLog> callLogs;
    RecyclerView recyclerView;
    CallAdapter callAdapter;

    public CallFragment(List<CallLog> callLogs) {
        // Required empty public constructor
        this.callLogs = callLogs;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.callView);
        callAdapter = new CallAdapter(callLogs);
        recyclerView.setAdapter(callAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}