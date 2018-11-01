package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.MessageBills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MessageBillsDao extends JpaRepository<MessageBills, Integer> {
    public List<MessageBills> findAll();

  //  @Query(value="select messagebills from messagebills where number=?1")
    public List<MessageBills> findByNumber(String number);

  //  @Query(value="select messagebills from messagebills where number=?1 and sendtime=?2")
   // public MessageBills findByNumberAndSendTime(String number, String sendTime);

    public MessageBills save(MessageBills messageBill);

    public void delete(MessageBills messageBill);
}