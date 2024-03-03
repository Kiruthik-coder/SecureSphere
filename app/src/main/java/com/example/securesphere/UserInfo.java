package com.example.securesphere;

public class UserInfo {
    private String userName;
    private String userEmail;
    private String userNumber;

    private String fmcID;
    public UserInfo() {

    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }
    public String getUserEmail() {
        return userEmail;
    }


    public void setUserName(String name) {
        this.userName = name;
    }
    public String getUserName() {
        return userName;
    }


    public void setUserNumber(String number) {
        this.userNumber = number;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setFmcID(String fmcID) {
        this.fmcID = fmcID;
    }

    public String getFmcID(){
        return fmcID;
    }
}
