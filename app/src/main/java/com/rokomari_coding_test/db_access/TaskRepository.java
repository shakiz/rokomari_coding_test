package com.rokomari_coding_test.db_access;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.rokomari_coding_test.model.Task;

import java.util.List;

public class TaskRepository {
    private TaskDAO taskDAO;
    private LiveData<List<Task>> allTasks;
    private LiveData<List<Task>> allTaskByStatus;

    public TaskRepository(Application application) {
        MasterDb masterDb = MasterDb.getInstance(application);
        taskDAO = masterDb.taskDAO();
    }

    public void insert(Task task){ new InsertAsyncTask(taskDAO).execute(task); }
    public void update(Task task){ new UpdateAsyncTask(taskDAO).execute(task); }
    public void delete(Task task){ new DeleteAsyncTask(taskDAO).execute(task); }
    public LiveData<List<Task>> getAllNotes(){
        allTasks = taskDAO.getAllTasks();
        return allTasks;
    }
    public LiveData<List<Task>> getAllNotesByStatus(int status){
        allTaskByStatus = taskDAO.getAllTasksByStatus(status);
        return allTaskByStatus;
    }

    private static class InsertAsyncTask extends AsyncTask<Task, Void, Void> {
        TaskDAO taskDAO;

        public InsertAsyncTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.insert(tasks[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Task, Void, Void> {
        TaskDAO taskDAO;

        public UpdateAsyncTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Task, Void, Void> {
        TaskDAO taskDAO;

        public DeleteAsyncTask(TaskDAO taskDAO) {
            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDAO.delete(tasks[0]);
            return null;
        }
    }

}
