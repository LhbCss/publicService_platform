package com.lin.POJO;

import java.util.Date;

public class Activity {
    private int a_id;
    private String a_name;
    private String a_icon;
    private Date a_date;
    private String a_lore;
    private String a_party;
    private String a_phone;
    private String a_describe;
    private String a_image;
    private String FormatDate;

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_icon() {
        return a_icon;
    }

    public void setA_icon(String a_icon) {
        this.a_icon = a_icon;
    }

    public Date getA_date() {
        return a_date;
    }

    public void setA_date(Date a_date) {
        this.a_date = a_date;
    }

    public String getA_lore() {
        return a_lore;
    }

    public void setA_lore(String a_lore) {
        this.a_lore = a_lore;
    }

    public String getA_party() {
        return a_party;
    }

    public void setA_party(String a_party) {
        this.a_party = a_party;
    }

    public String getA_phone() {
        return a_phone;
    }

    public void setA_phone(String a_phone) {
        this.a_phone = a_phone;
    }

    public String getA_describe() {
        return a_describe;
    }

    public void setA_describe(String a_describe) {
        this.a_describe = a_describe;
    }

    public String getA_image() {
        return a_image;
    }

    public void setA_image(String a_image) {
        this.a_image = a_image;
    }

    public String getFormatDate() {
        return FormatDate;
    }

    public void setFormatDate(String formatDate) {
        FormatDate = formatDate;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "a_id=" + a_id +
                ", a_name='" + a_name + '\'' +
                ", a_icon='" + a_icon + '\'' +
                ", a_date=" + a_date +
                ", a_lore='" + a_lore + '\'' +
                ", a_party='" + a_party + '\'' +
                ", a_phone='" + a_phone + '\'' +
                ", a_describe='" + a_describe + '\'' +
                ", a_image='" + a_image + '\'' +
                ", FormatDate='" + FormatDate + '\'' +
                '}';
    }
}
