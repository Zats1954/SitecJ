package ru.zatsoft.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.zatsoft.dao.UserDao;
import ru.zatsoft.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "sitecDb";

    public abstract UserDao userDao();
}
