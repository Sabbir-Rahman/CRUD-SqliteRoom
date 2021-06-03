package com.example.crud_sqlite_room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Staff.class}, version = 1, exportSchema = false)
public abstract class StaffDatabase extends RoomDatabase{

    //make the object of staffDao
    public abstract StaffDao staffDao();
}
