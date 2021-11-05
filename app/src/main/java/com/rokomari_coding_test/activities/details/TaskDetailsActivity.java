package com.rokomari_coding_test.activities.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.rokomari_coding_test.R;
import com.rokomari_coding_test.activities.HomeActivity;
import com.rokomari_coding_test.databinding.ActivityTaskDetailsBinding;
import com.rokomari_coding_test.db_access.TaskViewModel;
import com.rokomari_coding_test.model.Task;
import com.rokomari_coding_test.tools.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TaskDetailsActivity extends AppCompatActivity {
    private ActivityTaskDetailsBinding taskDetailsBinding;
    private TaskViewModel taskViewModel;
    private Task task;
    private int Status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_task_details);

        //region get intent data
        getIntentData();
        //endregion

        initObjects();
        bindUiWithComponents();
    }

    //region get intent data
    private void getIntentData(){
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getParcelable("task") != null){
                taskDetailsBinding.addNewTaskLayout.setVisibility(View.GONE);
                taskDetailsBinding.existingTaskLayout.setVisibility(View.VISIBLE);
                task = getIntent().getExtras().getParcelable("task");
                taskDetailsBinding.CreateDateText.setText(task.getCreateDate());
                taskDetailsBinding.TitleText.setText(task.getTitle());
                taskDetailsBinding.DescriptionText.setText(task.getDescription());
                taskDetailsBinding.DeadlineText.setText(task.getDeadline());
                taskDetailsBinding.StatusText.setText(getTaskStatus(task.getStatus()));
            }
        }
        else{
            taskDetailsBinding.addNewTaskLayout.setVisibility(View.VISIBLE);
            taskDetailsBinding.existingTaskLayout.setVisibility(View.GONE);
        }
    }
    //endregion

    private void initObjects() {
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
    }

    private void bindUiWithComponents(){
        setSupportActionBar(taskDetailsBinding.toolBar);
        setSpinnerAdapter();

        taskDetailsBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                taskDetailsBinding.Deadline.setText(sdf.format(myCalendar.getTime()));
            }

        };

        taskDetailsBinding.DeadlineDateSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(TaskDetailsActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        taskDetailsBinding.submitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });

        taskDetailsBinding.Status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Status = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String getTaskStatus(int status){
        if (status == 1){
            return "Open";
        }
        else if (status == 2){
            return "In Progress";
        }
        else if (status == 3){
            return "Test";
        }
        else if (status == 4){
            return "Done";
        }
        else{
            return "Not Selected";
        }
    }

    public void setSpinnerAdapter(){
        String[] spinnerValue = {"Status","Open","In Progress","Test","Done"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_drop, spinnerValue);
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        taskDetailsBinding.Status.setAdapter(spinnerAdapter);
    }

    private void saveTask() {
        if (TextUtils.isEmpty(taskDetailsBinding.Title.getText().toString())){
            Toast.makeText(getApplicationContext(), "Title can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(taskDetailsBinding.Deadline.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please select deadline", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Status == 0){
            Toast.makeText(getApplicationContext(), "Please select task status", Toast.LENGTH_SHORT).show();
            return;
        }

        Task task = new Task(System.currentTimeMillis(),
                Utils.getCurrentDate(),
                Status,
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