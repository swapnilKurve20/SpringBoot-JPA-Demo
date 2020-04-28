package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TagResponseDto;

public interface TagService {

	public TagResponseDto getTag(Long tagId);
	public List<TagResponseDto> getAllTags();
	String testTrnsaction() throws Exception;
}
