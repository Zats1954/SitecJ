package ru.zatsoft.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInf {
    @SerializedName("Users")
    @Expose
    private Users users;
    public Users getUsers() {
        return users;
    }
    public void setUsers(Users users) {
        this.users = users;
    }

}
