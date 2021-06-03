package com.example.crud_sqlite_room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insertActivity extends AppCompatActivity {
    EditText edit_name,edit_pos;
    Button submit_btn;

    String sname="",sposition="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_pos = (EditText) findViewById(R.id.edit_position);
        submit_btn = (Button) findViewById(R.id.btn_submit);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(edit_name.getText().toString().trim().isEmpty()||
                        edit_pos.getText().toString().trim().isEmpty()){
                   Toast.makeText(insertActivity.this, "Please enter value", Toast.LENGTH_SHORT).show();
               }
               else {
                   sname = edit_name.getText().toString().trim();
                   sposition=edit_pos.getText().toString().trim();

                   //pass name and position to the constructor
                   Staff staff = new Staff(sname, sposition);
                   StaffRepositary staffRepositary= new StaffRepositary((getApplicationContext()));
                   staffRepositary.InsertTask(staff);
                   finish();
               }
            }
        });
    }
}