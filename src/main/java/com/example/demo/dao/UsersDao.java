package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<Users, Integer> {
    public List<Users> findAll();

    public List<Users> findByNumber(String number);

    public List<Users> findByName(String name);


    public Users save(Users user);

    public void delete(Users user);
}