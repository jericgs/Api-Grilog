package com.grilog.grilogapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grilog.grilogapi.domain.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
