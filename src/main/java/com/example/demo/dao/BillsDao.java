package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BillsDao extends JpaRepository<Bills, Integer> {
    public List<Bills> findAll();

  //  @Query(value="select bills from bills where number=?1")
  //  public List<Bills> findByNumber(String number);

    //@Query(value="select bills from bills where number=?1 and month=?2")
    //public List<Bills> findByNumberAndMonth(String number, String month);

    public Bills save(Bills bill);

    public void delete(Bills bill);
}