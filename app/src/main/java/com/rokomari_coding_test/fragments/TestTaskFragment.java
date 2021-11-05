package com.rokomari_coding_test.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokomari_coding_test.R;

public class TestTaskFragment extends Fragment {
    public TestTaskFragment() {
        // Required empty public constructor
    }

    public static TestTaskFragment newInstance() {
        TestTaskFragment fragment = new TestTaskFragment();
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
        return inflater.inflate(R.layout.fragment_test_task, container, false);
    }
}