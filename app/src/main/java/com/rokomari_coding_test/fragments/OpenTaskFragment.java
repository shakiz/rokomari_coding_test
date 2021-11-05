package com.rokomari_coding_test.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokomari_coding_test.R;

public class OpenTaskFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_open_task, container, false);
    }
}