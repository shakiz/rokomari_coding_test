package com.rokomari_coding_test.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rokomari_coding_test.R;
import com.rokomari_coding_test.activities.details.TaskDetailsActivity;
import com.rokomari_coding_test.adapters.RecyclerAdapterTask;
import com.rokomari_coding_test.databinding.FragmentDoneTaskBinding;
import com.rokomari_coding_test.databinding.FragmentTestTaskBinding;
import com.rokomari_coding_test.db_access.TaskViewModel;
import com.rokomari_coding_test.model.Task;

import java.util.List;


public class DoneTaskFragment extends Fragment {
    private FragmentDoneTaskBinding binding;
    private Context context;
    private RecyclerAdapterTask recyclerAdapterTask;
    private TaskViewModel taskViewModel;

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
        context = inflater.getContext();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_done_task, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskViewModel = ViewModelProviders.of(getActivity()).get(TaskViewModel.class);
        bindUIWithComponents();
    }

    private void bindUIWithComponents() {
        binding.fabAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, TaskDetailsActivity.class));
            }
        });
        setRecyclerAdapter();
        taskViewModel.getAllTasksByStatus(4).observe(getActivity(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                recyclerAdapterTask.setTaskList(tasks);
                recyclerAdapterTask.notifyDataSetChanged();
            }
        });
    }

    //region recycler adapter
    private void setRecyclerAdapter(){
        recyclerAdapterTask = new RecyclerAdapterTask();
        binding.mRecyclerViewTask.setLayoutManager(new LinearLayoutManager(context));
        binding.mRecyclerViewTask.setAdapter(recyclerAdapterTask);
        recyclerAdapterTask.setOnDeleteTap(new RecyclerAdapterTask.onDeleteTap() {
            @Override
            public void onDeleteTap(Task task) {
                taskViewModel.delete(task);
            }
        });
        recyclerAdapterTask.setOnEditTap(new RecyclerAdapterTask.onEditTap() {
            @Override
            public void onEditTap(Task task) {
                context.startActivity(
                        new Intent(context, TaskDetailsActivity.class)
                                .putExtra("task", task)
                                .putExtra("screenType","edit")
                );
            }
        });
        recyclerAdapterTask.setOnItemClick(new RecyclerAdapterTask.onItemClick() {
            @Override
            public void onItemClick(Task task) {
                context.startActivity(
                        new Intent(context, TaskDetailsActivity.class)
                                .putExtra("task", task)
                                .putExtra("screenType","view")
                );
            }
        });
        recyclerAdapterTask.notifyDataSetChanged();
    }
    //endregion
}