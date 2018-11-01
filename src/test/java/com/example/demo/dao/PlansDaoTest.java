package com.example.demo.dao;

import com.example.demo.entity.Plans;
import com.example.demo.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PlansDaoTest {
    @Autowired
    private PlansDao plansDao;
    @Test
    public void test1() {
        Plans plan1 = new Plans("套餐1", 20, 100,0,0,0);
        plansDao.save(plan1);
        Plans plan2 = new Plans("套餐2", 10, 0,200,0,0);
        plansDao.save(plan2);
    }

    @Test
    public void test2() {
        Plans plan= plansDao.findByPlanId(1);
        System.out.println(plan.getName());
    }

    @Test
    public void test3() {
        List<Plans> plan = plansDao.findByName("套餐1");
        System.out.println(plan.get(0).getMonthFee());
    }


}