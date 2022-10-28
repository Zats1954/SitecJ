package ru.zatsoft.pojo;
import java.util.List;
 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;


public class Users {

    @SerializedName("ListUsers")
    @Expose
    private List<User> listUsers = null;

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

}
