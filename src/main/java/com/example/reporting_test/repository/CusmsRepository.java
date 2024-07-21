package com.example.reporting_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reporting_test.entity.Cusms;

public interface CusmsRepository extends JpaRepository<Cusms, Long> {

    Cusms findByPlg(String plg);

}
