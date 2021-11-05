package com.rokomari_coding_test.activities.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rokomari_coding_test.R;
import com.rokomari_coding_test.activities.HomeActivity;
import com.rokomari_coding_test.databinding.ActivityTaskDetailsBinding;
import com.rokomari_coding_test.db_access.TaskViewModel;
import com.rokomari_coding_test.model.Task;

public class TaskDetailsActivity extends AppCompatActivity {
    private ActivityTaskDetailsBinding taskDetailsBinding;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_task_details);

        initObjects();
        bindUiWithComponents();
    }

    private void initObjects() {
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
    }

    private void bindUiWithComponents(){
        setSupportActionBar(taskDetailsBinding.toolBar);

        taskDetailsBinding.submitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });

        taskDetailsBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void saveTask() {
        Task task = new Task(System.currentTimeMillis(),
                taskDetailsBinding.CreateDate.getText().toString(),
                1,
                taskDetailsBinding.Title.getText().toString(),
                taskDetailsBinding.Description.getText().toString(),
                taskDetailsBinding.Deadline.getText().toString(),"","","");
        taskViewModel.insert(task);
        Toast.makeText(getApplicationContext(),"Task Saved",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(TaskDetailsActivity.this, HomeActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}