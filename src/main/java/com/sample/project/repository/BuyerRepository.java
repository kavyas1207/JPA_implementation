package com.sample.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.project.entity.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer,String> {

}
