package com.rokomari_coding_test.db_access;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rokomari_coding_test.model.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllNotes();
    }

    public void insert(Task note){repository.insert(note);}
    public void update(Task note){repository.update(note);}
    public void delete(Task note){repository.delete(note);}
    public LiveData<List<Task>> getAllNotes(){return allTasks;}
}
