package com.lin.POJO;

public class UserActivity {
    private String openid;
    private int a_id;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    @Override
    public String toString() {
        return "UserActivity{" +
                "openid='" + openid + '\'' +
                ", a_id=" + a_id +
                '}';
    }
}
