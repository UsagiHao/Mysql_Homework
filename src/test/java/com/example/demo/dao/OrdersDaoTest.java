package com.example.demo.dao;

import com.example.demo.entity.Orders;
import com.example.demo.entity.Plans;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrdersDaoTest {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private PlansDao plansDao;
    @Test
    public void test1() {
     /*   Plans plan1 = plansDao.findByPlanId(1);
        String planName1 = plan1.getName();
        Orders order1 = new Orders("15189585960", 1, "套餐1", "2018-01-01 00:00:00", "生效中" );
        ordersDao.save(order1);*/

        Plans plan2 = plansDao.findByPlanId(2);
        String planName2 = plan2.getName();
        Date date2 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date2);
        Orders order2 = new Orders("13861597033", 2, planName2, dateString, "生效中" );
        ordersDao.save(order2);
    }

    @Test
    public void test2() {
        List<Orders> orders = ordersDao.findByPlanName("套餐1");
        System.out.println(orders.get(0).getNumber());
    }

    @Test
    public void test3() {
        List<Orders> order = ordersDao.findByPlanName("套餐1");
        order.get(0).setState("已失效");
        ordersDao.save(order.get(0));
    }

    @Test
    public void test4() {
        List<Orders> order = ordersDao.findByNumber("15189585960");
        System.out.println(order.get(0).getBuyTime());

    }

    @Test
    public void test5() {
        List<Orders> order = ordersDao.findByNumberAndState("15189585960", "已失效");
        System.out.println(order.get(0).getBuyTime());

    }
}