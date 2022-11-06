package ru.zatsoft;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import androidx.room.Room;
import ru.zatsoft.dao.UserConvertor;
import ru.zatsoft.db.AppDatabase;
import ru.zatsoft.entity.UserEntity;
import ru.zatsoft.pojo.User;

import static ru.zatsoft.sitecj.MainActivity.context;
import static ru.zatsoft.sitecj.MainActivity.users;

public class UserDB {
    public AppDatabase db;
    private List<UserEntity> usersEntity;

    public UserDB() {
        db = Room.databaseBuilder(context,
                AppDatabase.class, "sitecDb.db").build();
        db.userDao().clearAll();
        usersEntity = new ArrayList<UserEntity>() ;
         for (User user: users){ usersEntity.add(UserConvertor.toUserEntity(user));};
        db.userDao().insertAll(usersEntity);
    }

    public List<User> getAll()     {
        assert db != null;
        usersEntity = db.userDao().getAll();
        List<User> users = new ArrayList<>();

        for(UserEntity user: usersEntity)
        {
            System.out.println("id " + user.id);
            System.out.println("Должность " + user.name);
            System.out.println("шифр " + user.uid);
            System.out.println("------------------------------------");
        }
         for (UserEntity user: usersEntity){
             assert users != null;
             users.add(UserConvertor.toUser(user));}
        return users;
}
}