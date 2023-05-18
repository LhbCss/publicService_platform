package com.lin.POJO;

import java.util.Date;

public class briefInfo {
    private String a_icon;
    private String a_name;
    private Date a_date;
    private String a_party;
    private String a_lore;
    private String formatDate;

    public String getA_icon() {
        return a_icon;
    }

    public void setA_icon(String a_icon) {
        this.a_icon = a_icon;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public Date getA_date() {
        return a_date;
    }

    public void setA_date(Date a_date) {
        this.a_date = a_date;
    }

    public String getA_party() {
        return a_party;
    }

    public void setA_party(String a_party) {
        this.a_party = a_party;
    }

    public String getA_lore() {
        return a_lore;
    }

    public void setA_lore(String a_lore) {
        this.a_lore = a_lore;
    }

    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    @Override
    public String toString() {
        return "briefInfo{" +
                "a_icon='" + a_icon + '\'' +
                ", a_name='" + a_name + '\'' +
                ", a_date=" + a_date +
                ", a_party='" + a_party + '\'' +
                ", a_lore='" + a_lore + '\'' +
                ", formatDate='" + formatDate + '\'' +
                '}';
    }
}
