package ru.zatsoft.dao;

import androidx.room.TypeConverter;
import ru.zatsoft.entity.UserEntity;
import ru.zatsoft.pojo.User;


public class UserConvertor {

    @TypeConverter
    public static UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.name = user.getName();
        userEntity.uid = user.getUid();
        userEntity.language = user.getLanguage();
        return userEntity;
    }

    @TypeConverter
    public static User toUser(UserEntity userEntity) {
        User user = new User();
        user.setName(userEntity.name);
        user.setUid(userEntity.uid);
        user.setLanguage(userEntity.language);
        return user;
    }
}
