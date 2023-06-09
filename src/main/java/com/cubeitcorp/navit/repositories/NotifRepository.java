package com.cubeitcorp.navit.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cubeitcorp.navit.models.Notif;



public interface NotifRepository extends MongoRepository<Notif,String>{

	List<Notif> findByFilterOrderByTimestampDesc(String filter);

}
