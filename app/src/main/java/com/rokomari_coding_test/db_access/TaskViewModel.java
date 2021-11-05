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
    private LiveData<List<Task>> allTasksByStatus;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
    }

    public void insert(Task task){repository.insert(task);}
    public void update(Task task){repository.update(task);}
    public void delete(Task task){repository.delete(task);}
    public LiveData<List<Task>> getAllTasks(){
        allTasks = repository.getAllNotes();
        return allTasks;
    }
    public LiveData<List<Task>> getAllTasksByStatus(int status){
        allTasksByStatus = repository.getAllNotesByStatus(status);
        return allTasksByStatus;
    }
}
