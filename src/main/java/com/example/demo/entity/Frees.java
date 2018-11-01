package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Frees {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String month;
    private Integer freePhone;
    private Integer freeMessage;
    private Integer freeLocalData;
    private Integer freeDomesticData;

    public Frees(){

    }

    public Frees(String newNumber, String newMonth){
        setMonth(newMonth);
        setNumber(newNumber);
    }

    public Frees(String newNumber, String newMonth, Integer newFreePhone, Integer newFreeMessage, Integer newFreeLocalData, Integer newFreeDomesticData){
        setMonth(newMonth);
        setNumber(newNumber);
        setFreePhone(newFreePhone);
        setFreeMessagee(newFreeMessage);
        setFreeLocalData(newFreeLocalData);
        setFreeDomesticData(newFreeDomesticData);
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getMonth(){
        return month;
    }

    public void setMonth(String month){
        this.month = month;
    }

    public Integer getFreePhone(){
        return freePhone;
    }

    public void setFreePhone(Integer freePhone){
        this.freePhone = freePhone;
    }

    public Integer getFreeMessage(){
        return freeMessage;
    }

    public void setFreeMessagee(Integer freeMessage){
        this.freeMessage = freeMessage;
    }

    public Integer getFreeLocalData(){
        return freeLocalData;
    }

    public void setFreeLocalData(Integer freeLocalData){
        this.freeLocalData = freeLocalData;
    }

    public Integer getFreeDomesticData(){
        return freeDomesticData;
    }

    public void setFreeDomesticData(Integer freeDomesticData){
        this.freeDomesticData = freeDomesticData;
    }
}
