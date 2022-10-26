package ru.zatsoft.pojo;
import java.util.List;
 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;


public class Users {

    @SerializedName("ListUsers")
    @Expose
    private List<ListUser> listUsers = null;

    public List<ListUser> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<ListUser> listUsers) {
        this.listUsers = listUsers;
    }

}
