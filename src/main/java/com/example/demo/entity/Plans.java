package com.example.demo.entity;

import javax.persistence.*;


@Entity
public class Plans {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Integer planId;
    private String name;
    private Integer monthFee;
    private Integer freePhone;
    private Integer freeMessage;
    private Integer freeLocalData;
    private Integer freeDomesticData;

    //必须要有构造函数
    public Plans() {
    }

    public Plans(String newName, Integer newMonthFee, Integer newFreePhone, Integer newFreeMessage, Integer newFreeLocalData, Integer newFreeDomesticData){
        setName(newName);
        setMonthFee(newMonthFee);
        setFreePhone(newFreePhone);
        setFreeMessage(newFreeMessage);
        setFreeLocalData(newFreeLocalData);
        setFreeDomesticData(newFreeDomesticData);
    }

    public Integer getId() {
        return planId;
    }

    public void setId(Integer planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMonthFee() {
        return monthFee;
    }

    public void setMonthFee(Integer monthFee) {
        this.monthFee = monthFee;
    }

    public Integer getFreePhone() {
        return freePhone;
    }

    public void setFreePhone(Integer freePhone) {
        this.freePhone = freePhone;
    }

    public Integer getFreeMessage() {
        return freeMessage;
    }

    public void setFreeMessage(Integer freeMessage) {
        this.freeMessage = freeMessage;
    }

    public Integer getFreeLocalData() {
        return freeLocalData;
    }

    public void setFreeLocalData(Integer freeLocalData) {
        this.freeLocalData = freeLocalData;
    }

    public Integer getFreeDomesticData() {
        return freeDomesticData;
    }

    public void setFreeDomesticData(Integer freeDomesticData) {
        this.freeDomesticData = freeDomesticData;
    }
}