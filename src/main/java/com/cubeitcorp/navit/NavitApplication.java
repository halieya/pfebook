package com.cubeitcorp.navit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cubeitcorp.navit.enums.ERole;
import com.cubeitcorp.navit.models.Role;
import com.cubeitcorp.navit.repositories.RoleRepository;

@EnableScheduling
@SpringBootApplication
public class NavitApplication implements CommandLineRunner{

	private final RoleRepository roleRepository;
	@Autowired
	public NavitApplication(RoleRepository roleRepository) {
		
		this.roleRepository=roleRepository;

	}
	public static void main(String[] args) {
		SpringApplication.run(NavitApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("###########Spring Start###############");
//		System.out.println("########### Role Store ###############");
//	  	   Role newAdminRole = new Role();
//	       newAdminRole.setName(ERole.ROLE_ADMIN);
//	       roleRepository.save(newAdminRole);
//	
//	       Role newUserRole = new Role();
//	       newUserRole.setName(ERole.ROLE_USER);
//	       roleRepository.save(newUserRole);
//	
//	       Role newModRole = new Role();
//	       newModRole.setName(ERole.ROLE_MODERATOR);
//	       roleRepository.save(newModRole);
		
		}

}
