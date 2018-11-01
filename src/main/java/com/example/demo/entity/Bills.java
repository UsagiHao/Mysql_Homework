package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Bills {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String month;//记录月份
    private Integer monthFee;
    private Double phoneFee;
    private Double messageFee;
    private Double localDataFee;
    private Double domesticDataFee;
    private Double fee;

    //必须要有构造函数
    public Bills() {
    }

    public Bills(String newNumber, String newMonth, Integer newMonthFee, double newPhoneFee, double newMessageFee, double newLocalDataFee, double newDomesticDataFee){
        setNumber(newNumber);
        setMonth(newMonth);
        setMonthFee(newMonthFee);
        setPhoneFee(newPhoneFee);
        setMessageFee(newMessageFee);
        setLocalDataFee(newLocalDataFee);
        setDomesticDataFee(newDomesticDataFee);

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getMonthFee() {
        return monthFee;
    }

    public void setMonthFee(Integer monthFee) {
        this.monthFee = monthFee;
    }

    public Double getPhoneFee() {
        return phoneFee;
    }

    public void setPhoneFee(Double phoneFee) {
        this.phoneFee = phoneFee;
    }

    public Double getMessageFee() {
        return messageFee;
    }

    public void setMessageFee(Double messageFee) {
        this.messageFee = messageFee;
    }

    public Double getLocalDataFee() {
        return localDataFee;
    }

    public void setLocalDataFee(Double localDataFee) {
        this.localDataFee = localDataFee;
    }

    public Double getDomesticDataFee() {
        return domesticDataFee;
    }

    public void setDomesticDataFee(Double domesticDataFee) {
        this.domesticDataFee = domesticDataFee;
    }

    public Double getFee(){
        return fee;
    }

    public void setFee(Double fee){
        this.fee = fee;
    }
}