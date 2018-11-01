package com.example.demo.dao;

import com.example.demo.entity.MessageBills;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageBillsDaoTest {
    @Autowired
    private MessageBillsDao messageBillsDao;
    @Test
    public void test1() {
        MessageBills messageBill = new MessageBills("15189585960", "2018-10-30 10:10:00");
        messageBill.setFee(1.0);
        messageBillsDao.save(messageBill);
    }
}