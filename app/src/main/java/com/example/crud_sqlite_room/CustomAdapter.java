package com.example.crud_sqlite_room;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList<Staff> staffArrayList;
    Context context;

    public CustomAdapter(ArrayList<Staff> staffArrayList, Context context) {
        this.staffArrayList = staffArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entity_staff,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(staffArrayList.get(position).getName());
        holder.tv_position.setText(staffArrayList.get(position).getPosition());
        holder.tv_title.setText(staffArrayList.get(position).getName().toUpperCase().charAt(0)+"");

        //=== set some random color in title background
        Random random = new Random();
        int redChannel = random.nextInt(128)+127;  //max is 255 greater than 128 fro bright color
        int blueChannel = random.nextInt(128)+127;
        int greenChannel = random.nextInt(128)+127;

        holder.tv_title.setBackgroundColor(Color.rgb(redChannel,greenChannel,blueChannel));

        holder.img_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_dialog(position);
            }
        });

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_dialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return staffArrayList.size();
    }

    //======= my view holder starts
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_name;
        TextView tv_position;
        ImageView img_update;
        ImageView img_delete;

        //construcrtor
        public MyViewHolder(View itemView){
            super(itemView);

            this.tv_title = (TextView)itemView.findViewById(R.id.tv_title);
            this.tv_position = (TextView)itemView.findViewById(R.id.tv_position);
            this.tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            this.img_update = (ImageView)itemView.findViewById(R.id.edit_icon);
            this.img_delete = (ImageView)itemView.findViewById(R.id.delete_icon);

        }
    }
    //====== my view holder ends

    //======= dialog for update starts
        public void update_dialog(int position_of_update){
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Update");

            LayoutInflater li = LayoutInflater.from(context);
            View view_update = li.inflate(R.layout.activity_update, null);
            builder.setView(view_update);

            EditText edit_names= (EditText)view_update.findViewById(R.id.edit_name);
            EditText edit_position = (EditText)view_update.findViewById(R.id.edit_position);

            edit_names.setText(staffArrayList.get(position_of_update).getName()+"");
            edit_position.setText(staffArrayList.get(position_of_update).getPosition()+"");

            builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(edit_names.getText().toString().trim().isEmpty()||
                        edit_position.getText().toString().trim().isEmpty()){
                        Toast.makeText(context, "Enter Value", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //==== update from dataabse
                        StaffRepositary staffRepositary =new StaffRepositary(context);
                        String updated_name = edit_names.getText().toString().trim();
                        String updated_position = edit_position.getText().toString().trim();

                        Staff staff_update = new Staff(staffArrayList.get(position_of_update).getId(),updated_name,updated_position);
                        staffRepositary.UpdateTask(staff_update);
                        //==== update from database ends

                        staffArrayList.get(position_of_update).setName(updated_name);
                        staffArrayList.get(position_of_update).setPosition(updated_position);

                        notifyDataSetChanged();
                        dialogInterface.dismiss();
                    }



                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            builder.show();
        }
    //======= dialog for update starts

    //==dialog for delete starts
        public void delete_dialog(int position_of_delete){
           AlertDialog.Builder builder = new AlertDialog.Builder(context);
           builder.setTitle("Warning");
           builder.setMessage("Are you sure to delete?");

           builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                 StaffRepositary staffRepositary = new StaffRepositary(context);
                 staffRepositary.DeleteTask(staffArrayList.get(position_of_delete));

                 staffArrayList.remove(position_of_delete);
                 notifyDataSetChanged();
                 dialogInterface.dismiss();
               }
           });
           builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   dialogInterface.dismiss();
               }
           });
           builder.show();
        }
    //==dialog for delete ends
}
