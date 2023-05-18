package com.lin.POJO;

public class User {
    private String openid;
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public User(){
    }

    public User(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "User{" +
                "openid='" + openid + '\'' +
                '}';
    }
}
