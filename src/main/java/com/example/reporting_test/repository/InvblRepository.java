package com.example.reporting_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reporting_test.entity.Invbl;

public interface InvblRepository extends JpaRepository<Invbl, Long> {

    Invbl findByPlu(String plu);

}
