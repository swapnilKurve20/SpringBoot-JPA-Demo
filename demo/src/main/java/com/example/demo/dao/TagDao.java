package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Tags;

public interface TagDao {

	Tags getTag(Long tagId);

	Tags getTagByName(String tagname);

	List<Tags> getAllTags();
}
