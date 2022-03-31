package com.company.star.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.star.db.model.ShipmentStatus;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface ShipmentStatusRepo extends JpaRepository<ShipmentStatus, Integer> {

    @Modifying
    @Transactional
    @Query("update Shipment n set n.isDelivered = :isDelivered where n.carrierId = :carrierId AND n.carrierType =:carrierType AND n.weight =:weight")
    ShipmentStatus updateShipmentIsDelivered(@Param("isDelivered") boolean isDelivered, @Param("carrierId") int carrierId,
                               @Param("carrierType") int userType, @Param("weight") BigDecimal weight);
}
