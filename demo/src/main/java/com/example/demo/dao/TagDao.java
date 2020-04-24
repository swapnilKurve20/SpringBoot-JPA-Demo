package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.PostRequestDto.TagsDetails;
import com.example.demo.model.Tags;

public interface TagDao {

	public Tags getTag(Long tagId);
	public Tags getTagByName(String tagname);
	public List<Tags> getAllTags();
}
