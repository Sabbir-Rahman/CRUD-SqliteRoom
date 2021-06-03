package com.example.crud_sqlite_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_goto_insert, btn_goto_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StaffRepositary staffRepositary = new StaffRepositary(MainActivity.this);

        //Database is created
        btn_goto_insert = (Button)findViewById(R.id.btn_goto_insert);
        btn_goto_view = (Button)findViewById(R.id.btn_goto_view);

        btn_goto_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to another activity
                Intent intent= new Intent(MainActivity.this,insertActivity.class);
                startActivity(intent);
            }
        });

        btn_goto_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}