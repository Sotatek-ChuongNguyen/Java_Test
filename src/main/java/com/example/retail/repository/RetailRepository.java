package com.example.retail.repository;

import com.example.retail.entity.RetailBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailRepository extends JpaRepository<RetailBalance, Integer> {
}
