package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Tags;

public interface ITagsRepo extends JpaRepository<Tags, Long> {

}
