package ru.zatsoft.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import ru.zatsoft.entity.UserEntity;


@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<UserEntity> getAll();

    @Query("SELECT * FROM users")
    List<UserEntity> loadAll();

    @Query("SELECT * FROM users WHERE name LIKE :name LIMIT 1")
    UserEntity findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserEntity> users);

    @Delete
    void delete(UserEntity user);

    @Query("DELETE FROM users")
    void clearAll();

}

