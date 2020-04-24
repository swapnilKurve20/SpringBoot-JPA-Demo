package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Posts;

@Repository
public interface IPostRepo extends JpaRepository<Posts, Long> {

	@Query("FROM Posts p where p.author.id = :userProfile")
	List<Posts> findByUserProfile(Long userProfile);
}
