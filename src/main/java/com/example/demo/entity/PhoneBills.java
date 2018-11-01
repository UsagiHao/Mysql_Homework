package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class PhoneBills {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String startTime;
    private String endTime;
    private Double fee;


    //必须要有构造函数
    public PhoneBills() {
    }

    public PhoneBills(String newNumber, String newStartTime, String newEndTime){
        setNumber(newNumber);
        setStartTime(newStartTime);
        setEndTime(newEndTime);
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getFee(){
        return fee;
    }

    public void setFee(Double fee){
        this.fee = fee;
    }
}