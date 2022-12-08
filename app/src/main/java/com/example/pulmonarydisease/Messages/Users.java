package com.example.pulmonarydisease.Messages;

public class Users {

    private String userName, email, profileImage;

    public Users() {
    }

    public Users(String userName, String email, String profileImage) {
        this.userName = userName;
        this.email = email;
        this.profileImage = profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
