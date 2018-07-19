package com.example.demo.Bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Log {

    private String author;
    private String content;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operatetime;
    private String remote;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }
}
