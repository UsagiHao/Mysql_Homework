package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Bills;
import com.example.demo.entity.Frees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FreesDao extends JpaRepository<Frees, Integer> {
    public List<Frees> findAll();

    //  @Query(value="select bills from bills where number=?1")
    //  public List<Bills> findByNumber(String number);

    //@Query(value="select bills from bills where number=?1 and month=?2")
    //public List<Bills> findByNumberAndMonth(String number, String month);

    public List<Frees> findByNumberAndMonth(String number, String month);

    public Frees save(Frees free);

    public void delete(Frees free);
}