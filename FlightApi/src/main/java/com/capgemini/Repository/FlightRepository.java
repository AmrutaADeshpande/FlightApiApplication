package com.capgemini.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.Entity.FlightDetails;
@Repository
public interface FlightRepository extends JpaRepository<FlightDetails, Long> {

}