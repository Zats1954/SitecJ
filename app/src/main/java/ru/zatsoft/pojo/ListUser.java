package ru.zatsoft.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListUser {

   @SerializedName("User")
   @Expose
   private String user;
   @SerializedName("Uid")
   @Expose
   private String uid;
   @SerializedName("Language")
   @Expose
   private String language;

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public String getUid() {
      return uid;
   }

   public void setUid(String uid) {
      this.uid = uid;
   }

   public String getLanguage() {
      return language;
   }

   public void setLanguage(String language) {
      this.language = language;
   }

}
