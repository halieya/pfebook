package com.cubeitcorp.navit.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cubeitcorp.navit.dto.VehicleDTO;
import com.cubeitcorp.navit.models.Vehicle;



@Repository
public interface VehicleRepository extends MongoRepository<Vehicle,String> {

    @Query("{ 'code' : ?0 }")
    VehicleDTO findFirstByCode(String code);

    List<Vehicle> findByFilter(String filter);

}


