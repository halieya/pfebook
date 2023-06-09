package com.cubeitcorp.navit.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cubeitcorp.navit.enums.ERole;
import com.cubeitcorp.navit.models.Role;



public interface RoleRepository extends MongoRepository<Role, String>{
	 Optional<Role> findByName(ERole name);
}
