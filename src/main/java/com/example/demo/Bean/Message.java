package com.example.demo.Bean;

public class Message {

    private  static class MessageClass{
        private static final Message message=new Message();
    }

    private Message(){}

    public static  Message getInstancell(){
        return MessageClass.message;
    }

    private boolean success;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
