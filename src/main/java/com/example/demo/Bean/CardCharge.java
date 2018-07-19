package com.example.demo.Bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class CardCharge {
    private  String stu_account;
    private double card_balance;
    private String free_unit;

    @JSONField(format = "yyyy-MM-dd")
    private Date chargetime;

    private String stu_name;
    private String charge_money;

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getCharge_money() {
        return charge_money;
    }

    public void setCharge_money(String charge_money) {
        this.charge_money = charge_money;
    }

    public String getStu_account() {
        return stu_account;
    }

    public void setStu_account(String stu_account) {
        this.stu_account = stu_account;
    }

    public double getCard_balance() {
        return card_balance;
    }

    public void setCard_balance(double card_balance) {
        this.card_balance = card_balance;
    }

    public String getFree_unit() {
        return free_unit;
    }

    public void setFree_unit(String free_unit) {
        this.free_unit = free_unit;
    }

    public Date getChargetime() {
        return chargetime;
    }

    public void setChargetime(Date chargetime) {
        this.chargetime = chargetime;
    }
}
