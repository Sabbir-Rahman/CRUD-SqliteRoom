package com.example.crud_sqlite_room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Dao = Data Access Object
@Dao
public interface StaffDao {

    @Insert
    Long insertTask(Staff staff);

    @Update
    void updateTask(Staff staff);

    @Delete
    void deleteTask(Staff staff);

    //view data by ascending order
    @Query("select * from staff order by id asc")
    List<Staff> getAll();
}
