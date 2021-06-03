package com.example.crud_sqlite_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class viewActivity extends AppCompatActivity {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    RecyclerView staffrecyclerview;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Staff> staffArrayList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        staffrecyclerview = (RecyclerView)findViewById(R.id.staffRecyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        staffrecyclerview.setLayoutManager(layoutManager);

        new LoadDataTask().execute();

    }

    class LoadDataTask extends AsyncTask<Void, Void, Void>{
        StaffRepositary staffRepositary;
        List<Staff> staffList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            staffRepositary = new StaffRepositary((getApplicationContext()));
        }

        @Override
        protected Void doInBackground(Void... voids) {
            staffList= staffRepositary.getStaff();
            staffArrayList = new ArrayList<>();

            for(int i=0;i < staffList.size();i++){
                staffArrayList.add(staffList.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            customAdapter = new CustomAdapter(staffArrayList, viewActivity.this);
            staffrecyclerview.setAdapter(customAdapter);
        }
    }
}