package com.rokomari_coding_test.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rokomari_coding_test.R;
import com.rokomari_coding_test.activities.details.TaskDetailsActivity;
import com.rokomari_coding_test.databinding.FragmentOpenTaskBinding;

public class OpenTaskFragment extends Fragment {
    private FragmentOpenTaskBinding binding;
    private Context context;

    public OpenTaskFragment() {
    }

    public static OpenTaskFragment newInstance() {
        OpenTaskFragment fragment = new OpenTaskFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = inflater.getContext();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_open_task, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fabAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, TaskDetailsActivity.class));
            }
        });
    }
}