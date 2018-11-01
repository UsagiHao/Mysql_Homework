package com.example.demo.service;

import com.example.demo.entity.Plans;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserBLServiceTest {
    @Autowired
    private UsersBLService service;

    //测试一：添加用户
    @Test
    public void test1() {
        service.addUser("18923456789" , "宋大抟");
        service.addUser("18888888888","阮大健");
    }

    //测试二：添加新套餐
    @Test
    public void test2(){
        service.addPlan("套餐14", 15, 80,10,0,0);
        service.addPlan("套餐15", 20, 50,100,512,0);
        service.addPlan("套餐16", 25, 200,0,1024,512);
    }

    //测试三：用户购买套餐
    @Test
    public void test3(){
        service.createOrder("15161782358", 1, "套餐1");
        service.createOrder("15161782358", 5, "套餐5");
        service.createOrder("15189571321", 13, "套餐13");
        service.createOrder("15189571321", 6, "套餐6");

    }

    //测试四：用户取消套餐
    @Test
    public void test4(){
        service.cancelOrder("15161782358", "套餐6");
    }

    //测试五：用户查询订购记录
    @Test
    public void test5(){
        service.showOrders("15161782358");
        service.showOrders("13952377262");
        service.showOrders("15189585960");
    }

    //测试六：用户打电话
    @Test
    public void test6(){
        service.makeCall("15161782358", "2018-10-01 00:01:02", "2018-10-01 00:12:13");
        service.makeCall("15161782358", "2018-10-15 12:34:01", "2018-10-15 12:42:12");
        service.makeCall("15161782358", "2018-10-30 10:01:01", "2018-11-30 16:02:12");

        service.makeCall("15189571321", "2018-10-01 00:01:02", "2018-10-01 00:12:13");
        service.makeCall("15189571321", "2018-10-15 12:34:01", "2018-10-15 12:42:12");
        service.makeCall("15189571321", "2018-10-30 10:01:01", "2018-11-30 16:02:12");

        service.makeCall("13812349876", "2018-10-01 00:01:02", "2018-10-01 00:12:13");
        service.makeCall("13812349876", "2018-10-15 12:34:01", "2018-10-15 12:42:12");
        service.makeCall("13812349876", "2018-10-30 10:01:01", "2018-11-30 16:02:12");
    }

    //测试七：用户发短信
    @Test
    public void test7(){
        service.sendMessage("15161782358", "2018-10-30 10:01:01");
        service.sendMessage("15161782358", "2018-11-30 10:02:01");
        service.sendMessage("15161782358", "2018-11-30 10:03:01");

        service.sendMessage("15189571321", "2018-10-30 10:01:01");
        service.sendMessage("15189571321", "2018-11-30 10:02:01");
        service.sendMessage("15189571321", "2018-11-30 10:03:01");

        service.sendMessage("13812349876", "2018-10-30 10:01:01");
        service.sendMessage("13812349876", "2018-11-30 10:02:01");
        service.sendMessage("13812349876", "2018-11-30 10:03:01");
    }

    //八：测试用户本地上网：
    @Test
    public void test8(){
        service.useLocalData("15189571321", "2018-11-30 10:01:01", "2018-11-30 16:02:12", 20);
        service.useLocalData("15161782358", "2018-11-30 10:01:01", "2018-11-30 16:02:12", 20);
        service.useLocalData("13812349876", "2018-11-30 10:01:01", "2018-11-30 16:02:12", 20);
    }

    //测试九：用户国内上网
    @Test
    public void test9(){
        service.useDomesticData("15189571321", "2018-11-30 10:01:01", "2018-11-30 16:02:12", 1024);
        service.useDomesticData("15161782358", "2018-11-30 10:01:01", "2018-11-30 16:02:12", 1024);
        service.useDomesticData("13812349876", "2018-11-30 10:01:01", "2018-11-30 16:02:12", 1024);

    }

    //测试十：用户建立账单
    @Test
    public void test10(){
        service.showFees("15189571321", "2018-10");
        service.showFees("13952377262", "2018-11");
        service.showFees("13812349876", "2018-10");

    }
}