package com.example.demo.dao;

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
public class UserDaoTest {
    @Autowired
    private UsersDao usersDao;
    @Test
    public void test1() {
        Users user = new Users("15189585960" , "haorui");
        usersDao.save(user);
        Users user1 = new Users("13861597033" , "hr");
        usersDao.save(user1);
    }

    @Test
    public void test2() {
        List<Users> users = usersDao.findByNumber("13861597033");
        System.out.println(users.get(0).getName());
    }

    @Test
    public void test3() {
        List<Users> users = usersDao.findByName("haorui");
        System.out.println(users.get(0).getName());
    }


}