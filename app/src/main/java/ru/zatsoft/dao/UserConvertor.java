package ru.zatsoft.dao;

import androidx.room.TypeConverter;
import ru.zatsoft.entity.UserEntity;
import ru.zatsoft.pojo.User;


public class UserConvertor {
    private static User user = new User();

    @TypeConverter
    public static UserEntity toUserEntity(User user) {
        return new UserEntity(user.getName(), user.getUid(), user.getLanguage());
    }

    @TypeConverter
    public User toUser(UserEntity userEntity) {
        user.setName(userEntity.name);
        user.setUid(userEntity.uid);
        user.setLanguage(userEntity.language);
        return user;
    }
}
