package com.rokomari_coding_test.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task implements Parcelable {
    @PrimaryKey(autoGenerate = false)
    private long RecordId;
    private String CreateDate;
    private int Status;
    private String Title;
    private String Description;
    private String Deadline;
    private String Email;
    private String PhoneNumber;
    private String Url;

    public Task(long recordId, String createDate, int status, String title, String description, String deadline, String email, String phoneNumber, String url) {
        RecordId = recordId;
        CreateDate = createDate;
        Status = status;
        Title = title;
        Description = description;
        Deadline = deadline;
        Email = email;
        PhoneNumber = phoneNumber;
        Url = url;
    }

    public Task() {
    }

    protected Task(Parcel in) {
        RecordId = in.readLong();
        CreateDate = in.readString();
        Status = in.readInt();
        Title = in.readString();
        Description = in.readString();
        Deadline = in.readString();
        Email = in.readString();
        PhoneNumber = in.readString();
        Url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(RecordId);
        dest.writeString(CreateDate);
        dest.writeInt(Status);
        dest.writeString(Title);
        dest.writeString(Description);
        dest.writeString(Deadline);
        dest.writeString(Email);
        dest.writeString(PhoneNumber);
        dest.writeString(Url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public long getRecordId() {
        return RecordId;
    }

    public void setRecordId(long recordId) {
        RecordId = recordId;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
