package com.example.crud_sqlite_room;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StaffRepositary {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());


    private String DB_NAME = "staffdb";
    private StaffDatabase staffDatabase;
    Context context;

    public StaffRepositary(Context context) {
        this.context = context;
        staffDatabase = Room.databaseBuilder(context,StaffDatabase.class,DB_NAME).build();

    }

    //============= insert task starts ==================
    public void InsertTask(final Staff staff) {


        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here
                staffDatabase.staffDao().insertTask(staff);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        Toast.makeText(context, staff.name+" is inserted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
    //============= insert task ends ====================

    //============= get data task starts ==================
    public List<Staff> getStaff(){
        List<Staff> staffList = staffDatabase.staffDao().getAll();
        return staffList;
    }
    //============= get data task ends ====================

    //============= update task starts ==================
    public void UpdateTask(final Staff staff) {


        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here
                staffDatabase.staffDao().updateTask(staff);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        Toast.makeText(context, staff.name+" is updated", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
    //============= update task ends ====================

    //============= delete task starts ==================
    public void DeleteTask(final Staff staff) {


        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here
                staffDatabase.staffDao().deleteTask(staff);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        Toast.makeText(context, staff.name+" is deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
    //============= delete task ends ====================

}
