package com.example.demo.dao;

import com.example.demo.entity.PhoneBills;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PhoneBillsDaoTest {
    @Autowired
    private PhoneBillsDao phoneBillsDao;
    @Test
    public void test1() {
        String date1 = "2018-10-30 10:10:00";
        String date2 = "2018-10-30 10:12:23";

        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = dateFormat.parse(date1);
            Date d2 = dateFormat.parse(date2);
            long t1 = d1.getTime();
            long t2 = d2.getTime();
            long millis = t2 - t1;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) + 1;
            System.out.println(minutes);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        String date1 = "2018-10-30 10:10:00";
        String date2 = "2018-10-30 10:11:01";

        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = dateFormat.parse(date1);
            Date d2 = dateFormat.parse(date2);
            long t1 = d1.getTime();
            long t2 = d2.getTime();
            long millis = t2 - t1;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
            long minutes  = 0;
            if (seconds % 60 ==0){
                minutes = seconds / 60;
            }else {
                minutes = seconds / 60 + 1;
            }
            System.out.println(minutes);

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test3(){
        PhoneBills phoneBill = new PhoneBills("15189585960", "2018-10-30 10:10:00", "2018-10-30 10:12:00");
        phoneBill.setFee(1.0);
        phoneBillsDao.save(phoneBill);
    }
}