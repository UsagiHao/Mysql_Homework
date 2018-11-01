package com.example.demo.dao;

import com.example.demo.entity.DomesticDataBills;
import com.example.demo.entity.LocalDataBills;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DomesticDataBillsDaoTest {
    @Autowired
    private DomesticDataBillsDao domesticDataBillsDao;
    @Test
    public void test1() {
        DomesticDataBills domesticDataBill= new DomesticDataBills("13861597033", "2018-10-30 10:10:00", "2018-10-30 10:10:20", 5);
        domesticDataBill.setFee(0.0);
        domesticDataBillsDao.save(domesticDataBill);
    }
}