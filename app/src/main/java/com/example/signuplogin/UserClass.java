package com.example.signuplogin;

public class UserClass {

    public UserClass() {

    }

    public String username;
    public String email;
    public String password;

    public UserClass(String username, String email, String password, String confirmPass, String bDate, String phoneNo) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPass = confirmPass;
        this.bDate = bDate;
        this.phoneNo = phoneNo;
    }

    public String confirmPass;
    public String bDate;
    public String phoneNo;



}
