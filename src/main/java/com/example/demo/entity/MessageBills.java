package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class MessageBills {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String sendTime;
    private Double fee;


    //必须要有构造函数
    public MessageBills() {
    }

    public MessageBills(String number, String sendTime){
        setNumber(number);
        setSendTime(sendTime);
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Double getFee(){
        return fee;
    }

    public void setFee(Double fee){
        this.fee = fee;
    }
}