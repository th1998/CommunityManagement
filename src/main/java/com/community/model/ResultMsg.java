package com.community.model;

public class ResultMsg {

    private Integer flag;
    private String content;

    public ResultMsg(Integer flag,String content){
        this.flag = flag;
        this.content = content;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}