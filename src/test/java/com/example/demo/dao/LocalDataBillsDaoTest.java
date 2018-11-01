package com.example.demo.dao;

import com.example.demo.entity.LocalDataBills;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LocalDataBillsDaoTest {
    @Autowired
    private LocalDataBillsDao localDataBillsDao;
    @Test
    public void test1() {
        LocalDataBills localDataBill = new LocalDataBills("15189585960", "2018-10-30 10:10:00", "2018-10-30 10:10:20", 5);
        localDataBill.setFee(1.0);
        localDataBillsDao.save(localDataBill);
    }
}