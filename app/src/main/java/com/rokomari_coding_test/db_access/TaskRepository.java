package com.rokomari_coding_test.db_access;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.rokomari_coding_test.model.Task;

import java.util.List;

public class TaskRepository {
    private TaskDAO taskDAO;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application) {
        MasterDb masterDb = MasterDb.getInstance(application);
        taskDAO = masterDb.taskDAO();
        allTasks = taskDAO.getAllTasks();
    }

    public void insert(Task note){ new InsertAsyncTask(taskDAO).execute(note); };
    public void update(Task note){ new UpdateAsyncTask(taskDAO).execute(note); };
    public void delete(Task note){ new DeleteAsyncTask(taskDAO).execute(note); };
    public LiveData<List<Task>> getAllNotes(){return allTasks;};

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
