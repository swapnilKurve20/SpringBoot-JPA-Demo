package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TagResponseDto;
import com.example.demo.model.Tags;
import com.example.demo.rest.TagsController;

@Service
public class TagServiceImpl extends BaseService implements TagService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TagsController.class);

	public TagResponseDto getTag(Long tagId) {
		LOGGER.info("Started get tag call.");
		TagResponseDto tagResponseDto = new TagResponseDto();
		try {
			Tags tag = getTagDao().getTag(tagId);
			tagResponseDto.setId(tag.getId());
			tagResponseDto.setName(tag.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Completed get tag call.");
		return tagResponseDto;
	}

	public List<TagResponseDto> getAllTags() {

		LOGGER.info("Started get all tags call.");
		List<TagResponseDto> tagList = new ArrayList<TagResponseDto>();
		try {
			List<Tags> allTags = getTagDao().getAllTags();

			for (Tags tag : allTags) {
				TagResponseDto dto = new TagResponseDto();
				dto.setId(tag.getId());
				dto.setName(tag.getName());
				tagList.add(dto);
			}

		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Completed get all tags call.");
		return tagList;
	}
}
