package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Bills;
import com.example.demo.entity.LocalDataBills;
import com.example.demo.entity.PhoneBills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneBillsDao extends JpaRepository<PhoneBills, Integer> {
    public List<PhoneBills> findAll();

  //  @Query(value="select phonebills from phonebills where number=?1")
 //   public List<PhoneBills> findByNumber(String number);

  //  @Query(value="select phonebills from  phonebills where number=?1 and starttime=?2")
   // public PhoneBills findByNumberAndStartTime(String number, String startTime);

    public  List<PhoneBills> findByNumber(String number);

    public PhoneBills save(PhoneBills phoneBill);

    public void delete(PhoneBills phoneBill);
}