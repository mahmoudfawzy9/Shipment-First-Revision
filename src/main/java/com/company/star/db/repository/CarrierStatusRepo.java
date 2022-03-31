package com.company.star.db.repository;

import com.company.star.db.model.CarrierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierStatusRepo extends JpaRepository<CarrierStatus, Integer> {

}