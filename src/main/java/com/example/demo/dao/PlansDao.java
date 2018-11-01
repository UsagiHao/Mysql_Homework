package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Plans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansDao extends JpaRepository<Plans, Integer> {
    public List<Plans> findAll();

    public Plans findByPlanId(Integer id);

    public List<Plans> findByName(String name);


    public Plans save(Plans plan);

    public void delete(Plans plan);
}