package com.rokomari_coding_test.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokomari_coding_test.R;


public class DoneTaskFragment extends Fragment {
    public DoneTaskFragment() {
        // Required empty public constructor
    }

    public static DoneTaskFragment newInstance() {
        DoneTaskFragment fragment = new DoneTaskFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_done_task, container, false);
    }
}