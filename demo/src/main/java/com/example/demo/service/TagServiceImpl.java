package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TagResponseDto;
import com.example.demo.model.Tags;

@Service
public class TagServiceImpl extends BaseService implements TagService {

	ModelMapper mapper = new ModelMapper();

	public TagResponseDto getTag(Long tagId) {
		TagResponseDto tagResponseDto=new TagResponseDto();
		try {
			Tags tag = getTagDao().getTag(tagId);
			tagResponseDto.setId(tag.getId());
			tagResponseDto.setName(tag.getName());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tagResponseDto;
	}

	public List<TagResponseDto> getAllTags() {

		List<TagResponseDto> tagList = new ArrayList<TagResponseDto>();
		try {
			 List<Tags> allTags = tagDao.getAllTags();
			
			for(Tags tag : allTags) {
				TagResponseDto dto = new TagResponseDto();
				dto.setId(tag.getId());
				dto.setName(tag.getName());
				tagList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tagList;
	}
}
