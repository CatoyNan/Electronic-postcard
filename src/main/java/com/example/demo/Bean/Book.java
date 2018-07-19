package com.example.demo.Bean;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {

    private String stu_account;
    private String book_name;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date book_out_time;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date book_int_time;
    private int book_state;
    private String book_image;
    private String book_uid;
    private String stu_name;
    private String stu_class;
    private String book_type;

    public String getBook_type() {
        return book_type;
    }

    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public String getStu_account() {
        return stu_account;
    }

    public void setStu_account(String stu_account) {
        this.stu_account = stu_account;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Date getBook_out_time() {
        return book_out_time;
    }

    public void setBook_out_time(Date book_out_time) {
        this.book_out_time = book_out_time;
    }

    public Date getBook_int_time() {
        return book_int_time;
    }

    public void setBook_int_time(Date book_int_time) {
        this.book_int_time = book_int_time;
    }

    public int getBook_state() {
        return book_state;
    }

    public void setBook_state(int book_state) {
        this.book_state = book_state;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }

    public String getBook_uid() {
        return book_uid;
    }

    public void setBook_uid(String book_uid) {
        this.book_uid = book_uid;
    }
}
