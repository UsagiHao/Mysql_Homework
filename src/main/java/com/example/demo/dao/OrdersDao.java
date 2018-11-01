package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDao extends JpaRepository<Orders, Integer> {
    public List<Orders> findAll();

    public Optional<Orders> findById(Integer id);

    public List<Orders> findByPlanName(String planName);

    public List<Orders> findByNumber(String number);

    public List<Orders> findByNumberAndState(String number, String state);


    // @Query("select orders from orders where number=?1 and state=?2")
    // public List<Orders> findByNumberAndState(String number, String state);

    public Orders save(Orders order);

    public void delete(Orders order);

}