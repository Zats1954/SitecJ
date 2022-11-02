package ru.zatsoft.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.zatsoft.dao.UserDao;
import ru.zatsoft.entity.UserEntity;


@Database(entities = {UserEntity.class}, version = 1, exportSchema =false)
//@TypeConverters({UserConvertor.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "sitecDb";

//    private static AppDatabase instance;
//    public static synchronized AppDatabase getInstance(Context context){
//        if(instance == null){
//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                                             AppDatabase.class,DB_NAME)
//                           .fallbackToDestructiveMigration()
//                           .build();
//        }
//        return instance;
//    }
    public abstract UserDao userDao();
}
