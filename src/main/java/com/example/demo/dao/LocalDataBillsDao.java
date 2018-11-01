package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.LocalDataBills;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LocalDataBillsDao extends JpaRepository<LocalDataBills, Integer> {
    public List<LocalDataBills> findAll();

    public List<LocalDataBills> findByNumber (String number);

    public LocalDataBills save(LocalDataBills localDataBill);

    public void delete(LocalDataBills localDataBill);
}