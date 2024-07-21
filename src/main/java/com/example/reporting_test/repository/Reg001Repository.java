package com.example.reporting_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reporting_test.entity.Reg001;

public interface Reg001Repository extends JpaRepository<Reg001, Long>{

    List<Reg001> findByNoorAndRegister(String noor, String register);

}
