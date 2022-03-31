package com.company.star.db.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import com.company.star.db.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.star.db.model.Carrier;

@Repository
public interface CarrierRepo extends JpaRepository<Carrier, Integer> {


	@Query("select c from Carrier c where c.carrierId = :carrierId AND c.carrierType =:carrierType")
	List<Carrier> getRecordByIds(@Param("carrierId") int carrierId, @Param("carrierType") int carrierType);

	@Query("select c from Carrier c where c.carrierId = :carrierId AND c.carrierType =:carrierType AND c.weight =:weight")
    Carrier getRecordByIdsAndWeight(@Param("carrierId") int carrierId, @Param("carrierType") int carrierType,
									@Param("weight") int weight);

	@Query("select c from Carrier c where c.carrierId = :carrierId AND c.carrierType =:carrierType AND c.isConfirmed = 1")
	List<Carrier> getRecordByIdsAndConfirmed(@Param("carrierId") int carrierId, @Param("carrierType") int carrierType);

	@Query("select c from Carrier c where c.weight = :weight order by c.createdAt desc")
	List<Carrier> getRecordByWeight(@Param("weight") BigDecimal weight);

	@Query(value="select * from Carrier c where c.isConfirmed=true AND c.weight =:weight order by u.createdAt desc limit 1",nativeQuery=true)
    Carrier getCarrierForWeight(@Param("weight") int weight);

	@Query(value="select n from Carrier n WHERE n.carrierServiceID = :carrierServiceID")
	List<Carrier> findByCarrierServiceID(@Param("carrierServiceID") String carrierServiceID);
	//Pageable firstPageWithFiveHundredElements = PageRequest.of(0, 2);

	@Modifying
	@Transactional
	@Query("update Carrier c set c.carrier = :carrier where c.carrierId = :carrierId AND c.carrierType =:carrierType")
	int updateRecordCarrier(@Param("carrier") String carrier, @Param("carrierId") int carrierId, @Param("carrierType") int carrierType);

	@Modifying
	@Transactional
	@Query("update Carrier c set c.isConfirmed = :confirmed where c.carrierId = :carrierId AND c.carrierType =:carrierType AND c.weight =:weight")
	int updateOrderIsConfirmed(@Param("confirmed") boolean confirmed, @Param("carrierId") String carrierId,
							   @Param("carrierType") int userType, @Param("weight") BigDecimal weight);

	@Query("SELECT c FROM Carrier c WHERE c.carrierId = :carrierId AND c.carrierType = :carrierType and c.isDelivered = 0")
	List<Carrier> findUndeliveredByCarrierIdAndCarrierType(@Param("carrierId") int carrierId, @Param("carrierType") int carrierType);
}
