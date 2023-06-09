package com.cubeitcorp.navit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubeitcorp.navit.models.User;
import com.cubeitcorp.navit.repositories.UserRepository;
import com.cubeitcorp.navit.services.impl.UserDetailsImpl;

import lombok.extern.log4j.Log4j2;
@RestController
@RequestMapping("/api/test")
@Log4j2
public class TestController {

    @Autowired 
    private UserRepository repository;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> allAccess() {
        return repository.findAll();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public UserDetailsImpl profile() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      
        log.info("Filter: {}", userDetails.getFilter());
        log.info("Fullname: {}", userDetails.getFullname());
        return userDetails;
    }
}
