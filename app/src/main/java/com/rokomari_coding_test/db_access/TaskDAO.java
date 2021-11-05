package com.rokomari_coding_test.db_access;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rokomari_coding_test.model.Task;

import java.util.List;

@Dao
public interface TaskDAO {
    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("select * from Task order by RecordId desc")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM Task WHERE Status = :status")
    LiveData<List<Task>> getAllTasksByStatus(int status);
}
