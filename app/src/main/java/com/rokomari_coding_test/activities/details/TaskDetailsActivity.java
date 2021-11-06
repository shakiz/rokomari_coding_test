package com.rokomari_coding_test.activities.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
    private Task task = new Task();
    private int Status = 0;
    private String screenType = "", action = "add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_task_details);

        initObjects();
        bindUiWithComponents();
        getIntentData();
    }

    //region get intent data
    private void getIntentData() {
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getParcelable("task") != null
                    && getIntent().getStringExtra("screenType") != null) {
                task = getIntent().getExtras().getParcelable("task");
                screenType = getIntent().getStringExtra("screenType");
                if (screenType.equals("view") && task.getRecordId() != 0) {
                    updateUIForView();
                } else if (screenType.equals("edit") && task.getRecordId() != 0) {
                    updateUiForEdit();
                }
            }
        } else {
            taskDetailsBinding.addNewTaskLayout.setVisibility(View.VISIBLE);
            taskDetailsBinding.existingTaskLayout.setVisibility(View.GONE);
        }
    }
    //endregion

    //region init necessary objects
    private void initObjects() {
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
    }
    //endregion

    //region perform all kind of UI interactions from here
    private void bindUiWithComponents() {
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
                saveOrUpdateTask();
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

        taskDetailsBinding.fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (task.getRecordId() != 0) {
                    updateUiForEdit();
                }
            }
        });

        taskDetailsBinding.UrlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("url", R.drawable.ic_url, getString(R.string.enter_url), getString(R.string.save_url));
            }
        });
        taskDetailsBinding.PhoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("phone", R.drawable.ic_phone, getString(R.string.enter_phone), getString(R.string.save_phone));
            }
        });
        taskDetailsBinding.EmailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("email", R.drawable.ic_email, getString(R.string.enter_email), getString(R.string.save_email));
            }
        });
        taskDetailsBinding.UrlLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("url", R.drawable.ic_url, getString(R.string.enter_url), getString(R.string.save_url));
            }
        });
        taskDetailsBinding.PhoneLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("phone", R.drawable.ic_phone, getString(R.string.enter_phone), getString(R.string.save_phone));
            }
        });
        taskDetailsBinding.EmailLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("email", R.drawable.ic_email, getString(R.string.enter_email), getString(R.string.save_email));
            }
        });
    }
    //endregion

    //region show/hide edit and view screen
    private void updateUIForView() {
        taskDetailsBinding.addNewTaskLayout.setVisibility(View.GONE);
        taskDetailsBinding.existingTaskLayout.setVisibility(View.VISIBLE);
        taskDetailsBinding.CreateDateText.setText(task.getCreateDate());
        taskDetailsBinding.TitleText.setText(task.getTitle());
        taskDetailsBinding.DescriptionText.setText(task.getDescription());
        taskDetailsBinding.DeadlineText.setText(task.getDeadline());
        taskDetailsBinding.StatusText.setText(getTaskStatus(task.getStatus()));
        Status = task.getStatus();
    }

    private void updateUiForEdit() {
        taskDetailsBinding.addNewTaskLayout.setVisibility(View.VISIBLE);
        taskDetailsBinding.existingTaskLayout.setVisibility(View.GONE);
        taskDetailsBinding.Title.setText(task.getTitle());
        taskDetailsBinding.Description.setText(task.getDescription());
        taskDetailsBinding.Deadline.setText(task.getDeadline());
        taskDetailsBinding.Status.setSelection(task.getStatus(), true);
        Status = task.getStatus();
        action = "update";
    }
    //endregion

    //region get task status
    private String getTaskStatus(int status) {
        if (status == 1) { return "Open"; }
        else if (status == 2) { return "In Progress"; }
        else if (status == 3) { return "Test"; }
        else if (status == 4) { return "Done"; }
        else { return "Not Selected"; }
    }
    //endregion

    //region set spinner adapter for task Status
    public void setSpinnerAdapter() {
        String[] spinnerValue = {"Status", "Open", "In Progress", "Test", "Done"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_drop, spinnerValue);
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        taskDetailsBinding.Status.setAdapter(spinnerAdapter);
    }
    //endregion

    //region save or update task to db
    private void saveOrUpdateTask() {
        if (TextUtils.isEmpty(taskDetailsBinding.Title.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Title can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(taskDetailsBinding.Deadline.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please select deadline", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Status == 0) {
            Toast.makeText(getApplicationContext(), "Please select task status", Toast.LENGTH_SHORT).show();
            return;
        }

        task.setCreateDate(Utils.getCurrentDate());
        task.setStatus(Status);
        task.setTitle(taskDetailsBinding.Title.getText().toString());
        task.setDescription(taskDetailsBinding.Description.getText().toString());
        task.setDeadline(taskDetailsBinding.Deadline.getText().toString());
        if (action.equals("add")) {
            task.setRecordId(System.currentTimeMillis());
            taskViewModel.insert(task);
            showConfirmationDialog(getString(R.string.task_saved_successfully));
        } else {
            taskViewModel.update(task);
            showConfirmationDialog(getString(R.string.task_updated_successfully));
        }
    }
    //endregion

    //region show all social dialog
    public void showDialog(String layoutFor, int icon, String inputHintRes, String buttonTextRes){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_input_dialog);

        ImageView iconImg = dialog.findViewById(R.id.icon);
        EditText inputField = dialog.findViewById(R.id.textInput);
        Button dialogButton = dialog.findViewById(R.id.saveButton);

        iconImg.setImageResource(icon);
        inputField.setHint(inputHintRes);
        dialogButton.setText(buttonTextRes);

        if (layoutFor.equals("email")) {
            if (!TextUtils.isEmpty(task.getEmail())){
                inputField.setText(task.getEmail());
            }
        }
        else if (layoutFor.equals("url")){
            if(!TextUtils.isEmpty(task.getUrl())){
                inputField.setText(task.getUrl());
            }
        }
        else{
            if (!TextUtils.isEmpty(task.getPhoneNumber())){
                inputField.setText(task.getPhoneNumber());
            }
        }

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(inputField.getText().toString())){
                    if (layoutFor.equals("email")) task.setEmail(inputField.getText().toString());
                    else if (layoutFor.equals("url")) task.setUrl(inputField.getText().toString());
                    else task.setPhoneNumber(inputField.getText().toString());
                }
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    //endregion

    //region show all social dialog
    public void showConfirmationDialog(String messageText){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.confirmation_dialog);

        Button dialogButton = dialog.findViewById(R.id.okButton);
        TextView message = dialog.findViewById(R.id.message);

        message.setText(messageText);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(TaskDetailsActivity.this, HomeActivity.class));
            }
        });
        dialog.show();
    }
    //endregion

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}