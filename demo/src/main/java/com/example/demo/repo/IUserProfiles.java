package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserProfiles;

@Repository
public interface IUserProfiles extends JpaRepository<UserProfiles, Long> {

	UserProfiles findByUserId(Long id);
}
