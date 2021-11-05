package com.rokomari_coding_test.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rokomari_coding_test.R;
import com.rokomari_coding_test.model.Task;

import java.util.List;

public class RecyclerAdapterTask extends RecyclerView.Adapter <RecyclerAdapterTask.ViewHolder>{
    private List<Task> taskList;

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    //region interfaces for on item click, delete and edit
    public onItemClick onItemClick;
    public interface onItemClick{
        void onItemClick(Task task);
    }

    public onDeleteTap onDeleteTap;
    public interface onDeleteTap{
        void onDeleteTap(Task task);
    }

    public onEditTap onEditTap;
    public interface onEditTap{
        void onEditTap(Task task);
    }

    public void setOnItemClick(RecyclerAdapterTask.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setOnDeleteTap(onDeleteTap onDeleteTap) {
        this.onDeleteTap = onDeleteTap;
    }

    public void setOnEditTap(onEditTap onEditTap) {
        this.onEditTap = onEditTap;
    }
    //endregion

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_task,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.Title.setText(task.getTitle());
        holder.CreateDate.setText(task.getCreateDate());
        holder.Deadline.setText(task.getDeadline());
        holder.item_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null){
                    onItemClick.onItemClick(task);
                }
            }
        });
        holder.EditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEditTap != null) {
                    onEditTap.onEditTap(task);
                }
            }
        });
        holder.DeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteTap != null) {
                    onDeleteTap.onDeleteTap(task);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (taskList != null && taskList.size() > 0) return taskList.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title, CreateDate, Deadline;
        CardView item_card_view;
        ImageView EditTask, DeleteTask;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.Title);
            CreateDate = itemView.findViewById(R.id.CreateDate);
            Deadline = itemView.findViewById(R.id.Deadline);
            EditTask = itemView.findViewById(R.id.EditTask);
            DeleteTask = itemView.findViewById(R.id.DeleteTask);
            item_card_view = itemView.findViewById(R.id.item_card_view);
        }
    }
}
