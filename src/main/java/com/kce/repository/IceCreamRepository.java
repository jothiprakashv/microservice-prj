package com.kce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kce.entity.Flavor;

@Repository
public interface IceCreamRepository extends JpaRepository<Flavor, Long> {
	

}
